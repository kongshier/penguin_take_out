package com.shier.penguin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shier.penguin.entity.Category;


/**
 * @author Shier
 */
public interface CategoryService extends IService<Category> {

    public void remove(Long id);

}
