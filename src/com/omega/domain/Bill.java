package com.omega.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author KennySo
 * @version 1.0
 */
@Data
public class Bill {

    private Integer id;
    private String billId;
    private Integer diningTableId;
    private Integer menuId;
    private Integer nums;
    private Double money;
    private Date billDate;
    private String state;
}
