package com.shier.penguin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shier.penguin.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Shier 2022/10/7
 */
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
}
