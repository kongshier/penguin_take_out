package com.shier.penguin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shier.penguin.common.R;
import com.shier.penguin.entity.Employee;
import com.shier.penguin.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Shier 2022/9/26
 */
@Slf4j
@RestController
@RequestMapping("/employee")
@Api(tags = "员工信息接口")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    /**
     * 员工登录
     * @param request 请求
     * @param employee 员工信息
     * @return 结果集
     */
    @RequestMapping("/login")
    @ApiOperation("员工登录")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {

        //1. 对密码md5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2. 根绝页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        //3. 没有查询到则返回登录失败
        if (emp == null) {
            return R.error("用户不存在，登录失败~");
        }
        //4. 密码比对，如果不一致则返回登录失败结果
        if (!emp.getPassword().equals(password)) {
            return R.error("密码不正确,请重新输入");
        }

        //5. 查看员工状态，0：禁用，1：使用
        if (emp.getStatus() == 0) {
            return R.error("账号已禁用用");
        }
        //6.登录成功 将id 注入session，并返回登录成功
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }


    /**
     * 员工退出
     * @param request
     * @return 结果集
     */
    @PostMapping("/logout")
    @ApiOperation("员工后台退出")
    public R<String> logout(HttpServletRequest request) {
        //清理Session中保存登录员工的id
        request.getSession().removeAttribute("employee");
        return R.success("成功退出~");
    }

    /**
     * 新增员工
     * @param employee
     * @return
     */
    @PostMapping
    @ApiOperation("新增员工")
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("新增员工，员工信息：{}", employee.toString());

        //设置初始密码123456，需要进行md5加密处理
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        //员工创建时间
        // employee.setCreateTime(LocalDateTime.now());

        //员工更新时间
        // employee.setUpdateTime(LocalDateTime.now());

        //获得当前登录用户的id
        // Long empId = (Long) request.getSession().getAttribute("employee");

        // employee.setCreateUser(empId);
        // employee.setUpdateUser(empId);

        employeeService.save(employee);
        return R.success("新增员工成功");
    }


    /**
     * 员工信息查询
     * Page 具有record等信息
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "员工分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true),
            @ApiImplicitParam(name = "name", value = "套餐名称", required = false)
    })
    public R<Page> page(int page, int pageSize, String name) {
        log.info("page={} pageSize={},name={}", page, pageSize, name);

        //分页构造器
        Page pageInfo = new Page(page, pageSize);

        //条件构造器
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper();

        //添加过滤器
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);

        //添加排序条件
        lambdaQueryWrapper.orderByDesc(Employee::getUpdateTime);

        //查询
        employeeService.page(pageInfo, lambdaQueryWrapper);

        return R.success(pageInfo);
    }


    /**
     * 根据id 修改员工信息
     * @param employee
     * @return
     */
    @PutMapping
    @ApiOperation("修改员工id")
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {
        //获得请求
        // Long empId = (Long) request.getSession().getAttribute("employee");
        //设置修改时间
        // employee.setUpdateTime(LocalDateTime.now());
        //设置修改的用户
        // employee.setUpdateUser(empId);

        long id = Thread.currentThread().getId();
        log.info("线程id为：{}", id);

        employeeService.updateById(employee);

        return R.success("员工信息修改成功！");
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("查询员工信息")
    public R<Employee> getById(@PathVariable Long id) {
        log.info("根据id查询员工信息...");
        Employee employee = employeeService.getById(id);
        if (employee != null) {
            return R.success(employee);
        }
        return R.error("没有查询到对应员工信息");
    }
}
