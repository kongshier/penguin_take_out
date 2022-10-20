package com.shier.penguin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shier.penguin.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Shier 2022/10/6
 */
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
