package com.shier.penguin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shier.penguin.dto.SetmealDto;
import com.shier.penguin.entity.Setmeal;

import java.util.List;

/**
 * @author Sheri
 */


public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     * @param ids
     */
    public void removeWithDish(List<Long> ids);

    /**
     * 回显套餐数据：根据套餐id查询套餐
     * @return
     */
    SetmealDto getDate(Long id);

    /**
     * 根据套餐id修改售卖状态
     * @param status
     * @param ids
     */
    public void updateSetmealStatusById(Integer status,List<Long> ids);
}
