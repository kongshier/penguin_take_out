package com.shier.penguin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shier.penguin.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Shier 2022/9/26
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
