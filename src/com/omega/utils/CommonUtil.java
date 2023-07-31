package com.omega.utils;

import lombok.Getter;

/**
 * Class CommonUtils
 *
 * @author KennySu
 * @date 2023/7/28
 */
public class CommonUtil {

    @Getter
    public enum TABLE_STATE {
        EMPTY(" 空 ", 0),
        IN_USE("使用中", 1);

        private final String cd;
        private final Integer val;

        TABLE_STATE(String cd, Integer val) {
            this.cd = cd;
            this.val = val;
        }
    }


    @Getter
    public enum BILL_STATE {
        UNPAID("未支付"),
        BAD_DEBT("坏账"),
        CASH("现金"),
        WX_PAY("微信"),
        ALI_PAY("支付宝");

        private final String val;

        BILL_STATE(String val) {
            this.val = val;
        }
    }
}
