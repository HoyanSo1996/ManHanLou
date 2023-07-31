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
        EMPTY(" 空 "),
        BOOKED("已预订"),
        IN_USE("使用中");

        private final String val;

        TABLE_STATE(String val) {
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
