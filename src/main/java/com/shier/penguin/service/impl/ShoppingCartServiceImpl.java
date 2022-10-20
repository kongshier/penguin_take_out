package com.shier.penguin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shier.penguin.common.BaseContext;
import com.shier.penguin.entity.ShoppingCart;
import com.shier.penguin.mapper.ShoppingCartMapper;
import com.shier.penguin.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * @author Shier 2022/10/19
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

    @Override
    public void clean() {
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
        remove(queryWrapper);
    }
}
