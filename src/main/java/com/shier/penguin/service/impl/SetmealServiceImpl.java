package com.shier.penguin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shier.penguin.common.CustomException;
import com.shier.penguin.dto.SetmealDto;
import com.shier.penguin.entity.Setmeal;
import com.shier.penguin.entity.SetmealDish;
import com.shier.penguin.mapper.SetmealMapper;
import com.shier.penguin.service.SetmealDishService;
import com.shier.penguin.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Shier 2022/10/1
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;

    /**
     * 新增套餐，同时要保持与菜品的关联关系
     * @param setmealDto
     */
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        //保存套餐基本信息，操作setmeal，执行insert操作
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();

        setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        //保存套餐和菜品的关联信息，操作setmeal_dish，执行insert操作
        setmealDishService.saveBatch(setmealDishes);
    }


    /**
     * 根据id删除套餐和关联的菜品
     * @param ids
     */
    @Override
    @Transactional
    public void removeWithDish(List<Long> ids) {
        // select count(*) from setmeal where id in () and status=1;
        // 查询套餐状态是否可以删除
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.in(Setmeal::getId, ids);
        //1 ： 起售状态
        setmealLambdaQueryWrapper.eq(Setmeal::getStatus, 1);
        //count为ServiceImpl的方法
        int count = this.count(setmealLambdaQueryWrapper);
        if (count > 0) {
            // 不能删除（起售时）。
            throw new CustomException("套餐为起售状态，无法删除");
        }
        // 如果可以删除，则先删除套餐表数据
        this.removeByIds(ids);

        //已知setmeal中的id，删除setmeal_dish中的记录
        // delete from setmeal_dish where setmeal_id in (ids)
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SetmealDish::getSetmealId, ids);
        // 删除套餐关联表数据
        setmealDishService.remove(queryWrapper);
    }


    /**
     * 回显套餐数据：根据套餐id查询套餐
     * @return
     */
    @Override
    public SetmealDto getDate(Long id) {
        Setmeal setmeal = this.getById(id);
        SetmealDto setmealDto = new SetmealDto();
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper();
        //在关联表中查询，setmealdish
        queryWrapper.eq(id != null, SetmealDish::getSetmealId, id);

        if (setmeal != null) {
            BeanUtils.copyProperties(setmeal, setmealDto);
            List<SetmealDish> list = setmealDishService.list(queryWrapper);
            setmealDto.setSetmealDishes(list);
            return setmealDto;
        }
        return null;
    }

    /**
     * 根据套餐id修改售卖状态
     * @param status
     * @param ids
     */
    @Override
    public void updateSetmealStatusById(Integer status, List<Long> ids) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ids != null, Setmeal::getId, ids);
        List<Setmeal> list = this.list(queryWrapper);

        for (Setmeal setmeal : list) {
            if (setmeal != null) {
                setmeal.setStatus(status);
                this.updateById(setmeal);
            }
        }
    }


}
