package com.omega.domain;

import lombok.Data;

/**
 * @author KennySo
 * @version 1.0
 */
@Data
public class DiningTable {

    private Integer id;
    private String state;
    private String orderName;
    private String orderTel;


    @Override
    public String toString() {
        return "   " + id + "\t\t " + state;
    }
}
