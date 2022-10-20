package com.shier.penguin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shier.penguin.entity.DishFlavor;
import com.shier.penguin.mapper.DishFlavorMapper;
import com.shier.penguin.service.DishFlavorService;
import org.springframework.stereotype.Service;

/**
 * @author Shier 2022/10/1
 */
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
