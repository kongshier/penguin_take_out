package com.shier.penguin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shier.penguin.entity.OrderDetail;
import com.shier.penguin.mapper.OrderDetailMapper;
import com.shier.penguin.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * @author Shier 2022/10/7
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
