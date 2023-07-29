package com.omega.domain;

import com.omega.utils.CommonUtil.TABLE_STATE;
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
        return "   " + id + "\t\t " + (state == 0 ? TABLE_STATE.EMPTY.getCd() : TABLE_STATE.IN_USE.getCd());
    }
}
