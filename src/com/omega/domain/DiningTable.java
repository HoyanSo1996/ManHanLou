package com.omega.domain;

import lombok.Data;

/**
 * @author KennySo
 * @version 1.0
 */
@Data
public class DiningTable {

    private Integer id;
    private Integer state;  // 0：空闲状态; 1：占用状态
    private String orderName;
    private String orderTel;


    @Override
    public String toString() {
        return "   " + id + "\t\t " + (state == 0 ? " 空 " : "使用中");
    }
}
