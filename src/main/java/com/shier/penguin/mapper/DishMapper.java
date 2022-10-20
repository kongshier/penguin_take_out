package com.shier.penguin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shier.penguin.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Shier 2022/10/1
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
