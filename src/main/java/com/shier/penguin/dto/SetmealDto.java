package com.shier.penguin.dto;


import com.shier.penguin.entity.Setmeal;
import com.shier.penguin.entity.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
