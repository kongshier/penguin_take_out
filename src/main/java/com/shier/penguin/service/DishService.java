package com.shier.penguin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shier.penguin.dto.DishDto;
import com.shier.penguin.entity.Dish;

import java.util.List;

/**
 * @author Shier
 */

public interface DishService extends IService<Dish> {

    /**
     * 新增菜品，同时插入菜品对应的口味数据，需要操作两张表：dish、dish_flavor
     * @param dishDto
     */
    public void saveWithFlavor(DishDto dishDto);

    /**
     * 根据id查询菜品信息和对应的口味信息
     * @param id
     * @return
     */
    public DishDto getByIdWithFlavor(Long id);

    /**
     * 更新菜品信息，同时更新对应的口味信息
     * @param dishDto
     */
    public void updateWithFlavor(DishDto dishDto);

    /**
     * 根据id修改菜品状态
     * @param status
     * @param ids
     */
    public void updateDishStatusById(Integer status, List<Long> ids);

    //根据传过来的id批量或者是单个的删除菜品
    void deleteByIds(List<Long> ids);
}
