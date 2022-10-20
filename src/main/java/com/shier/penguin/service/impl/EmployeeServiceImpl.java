package com.shier.penguin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shier.penguin.entity.Employee;
import com.shier.penguin.mapper.EmployeeMapper;
import com.shier.penguin.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author Shier 2022/9/26
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
