package com.shier.penguin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shier.penguin.entity.AddressBook;
import com.shier.penguin.mapper.AddressBookMapper;
import com.shier.penguin.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
 * @author Shier 2022/10/6
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

}
