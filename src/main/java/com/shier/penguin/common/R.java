package com.shier.penguin.common;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果，服务端响应的数据最终都会封装成此对象
 * @param <T>
 */
@Data
@ApiModel("返回结果")
public class R<T> implements Serializable {

    /**
     * 编码：1成功，0和其它数字为失败
     */
    @ApiModelProperty("编号")
    private Integer code;

    /**
     * 错误信息
     */
    @ApiModelProperty("错误信息")
    private String msg;

    /**
     * 数据
     */
    @ApiModelProperty("数据")
    private T data;

    /**
     * 动态数据
     */
    @ApiModelProperty("动态数据")
    private Map map = new HashMap();

    /**
     * 登录成功
     */
    @ApiModelProperty("登录成功")
    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    /**
     * 登录失败
     * @param msg
     * @param <T>
     * @return
     */
    @ApiModelProperty("登录失败")
    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    /**
     * @param key
     * @param value
     * @return
     */
    @ApiModelProperty("添加")
    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
