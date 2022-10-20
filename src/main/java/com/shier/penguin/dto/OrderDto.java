package com.shier.penguin.dto;

import com.shier.penguin.entity.OrderDetail;
import com.shier.penguin.entity.Orders;
import lombok.Data;

import java.util.List;

/**
 * @author Shier 2022/10/19
 */
@Data
public class OrderDto extends Orders {

    /**
     * 显示用户订单
     */
    private List<OrderDetail> orderDetails;
}
