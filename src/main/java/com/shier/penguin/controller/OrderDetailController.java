package com.shier.penguin.controller;

import com.shier.penguin.service.OrderDetailService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shier 2022/10/7
 */
@RestController
@RequestMapping("/")
@Slf4j
@Api(tags = "订单详情")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;
}
