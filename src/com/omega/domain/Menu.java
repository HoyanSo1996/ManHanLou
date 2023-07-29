package com.omega.domain;

import lombok.Data;

/**
 * @author KennySo
 * @version 1.0
 */
@Data
public class Menu {

    private Integer id;
    private String name;
    private String type;
    private Double price;

    @Override
    public String toString() {
        return id + "\t\t\t" + name + "\t\t"+ type + "\t\t" + price;
    }
}
