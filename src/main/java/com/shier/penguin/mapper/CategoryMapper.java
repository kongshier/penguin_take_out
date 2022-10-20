package com.shier.penguin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shier.penguin.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Shier
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
