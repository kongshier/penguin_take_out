package com.shier.penguin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shier.penguin.entity.ShoppingCart;

/**
 * @author Shier 2022/9/26
 */
public interface ShoppingCartService extends IService<ShoppingCart> {

    /**
     * 购物车清空
     */
    public void clean();
}
