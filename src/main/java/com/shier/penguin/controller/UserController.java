package com.shier.penguin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shier.penguin.common.R;
import com.shier.penguin.entity.User;
import com.shier.penguin.service.UserService;
import com.shier.penguin.utils.ValidateCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Shier 2022/10/6
 */

@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "移动端用户登录接口")
public class UserController {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    /**
     * 发送手机短信验证码
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    @ApiOperation("发送短信验证码")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        //获取手机号
        String phone = user.getPhone();

        if (StringUtils.isNotEmpty(phone)) {
            //生成随机的4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("验证码={}", code);

            //调用阿里云提供的短信服务API完成短信发送
            //SMSUtils.sendMessage("瑞吉外卖","",phone,code);

            //需要将生成的验证码保存到session
            // session.setAttribute(phone, code);

            //将生成的验证码缓存到Redis中，并且设置有效期2分钟
            redisTemplate.opsForValue().set(phone,code,2, TimeUnit.MINUTES);


            return R.success("短信发送成功");
        }
        return R.error("短信发送失败");
    }

    /**
     * 移动端用户登录
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("移动端登录")
    public R<User> login(@RequestBody Map map, HttpSession session) {
        log.info(map.toString());
        // 取出电话号。
        String phone = map.get("phone").toString();
        // 取出map中的验证码。
        // 如果相等,则登录成功。

        //从Redis中获取缓存的验证码
        redisTemplate.opsForValue().get(phone);

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhone, phone);
        User user = userService.getOne(queryWrapper);
        // 进一步判断手机号是否为新用户，
        if (user == null) {
            // 如果为新用户则自动为此新用户进行注册。
            User user1 = new User();
            user1.setPhone(phone);
            userService.save(user1);
            //把id存入session域中，方便过滤器筛选。
            session.setAttribute("user", user1.getId());
            return R.success(user1);
        }
        session.setAttribute("user", user.getId());

        //如果用户登录成功，删除Redis中的缓存验证码
        redisTemplate.delete(phone);

        // 如果不相等返回登录失败信息
        return R.success(user);
    }
    /**
     * 退出功能
     * ①在controller中创建对应的处理方法来接受前端的请求，请求方式为post；
     * ②清理session中的用户id
     * ③返回结果（前端页面会进行跳转到登录页面）
     * @return
     */
    @PostMapping("/loginout")
    public R<String> logout(HttpServletRequest request){
        //清理session中的用户id
        request.getSession().removeAttribute("user");
        return R.success("退出成功");
    }
}
