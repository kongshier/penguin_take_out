package com.shier.penguin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shier.penguin.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Shier 2022/10/7
 */
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
}
