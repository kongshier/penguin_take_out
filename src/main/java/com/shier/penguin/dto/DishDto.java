package com.shier.penguin.dto;


import com.shier.penguin.entity.Dish;
import com.shier.penguin.entity.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shier
 * 数据传输对象，展示层与服务层之间的数据传输
 */
@Data
public class DishDto extends Dish {

    /**
     * 菜品对应的口味数据
     */
    private List<DishFlavor> flavors = new ArrayList<>();

    /**
     * 菜品分类名称
     */
    private String categoryName;

    private Integer copies;
}
