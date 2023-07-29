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

        private String cd;
        private Integer val;

        TABLE_STATE(String cd, Integer val) {
            this.cd = cd;
            this.val = val;
        }
    }
}
