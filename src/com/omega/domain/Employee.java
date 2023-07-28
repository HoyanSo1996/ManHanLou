package com.omega.domain;

import lombok.Data;

import java.util.Date;

/**
 * Class Employee
 *
 * @author KennySu
 * @date 2023/7/28
 */
@Data
public class Employee {

    private Integer id;
    private String empId;
    private String password;
    private String name;
    private String job;
    private Date createTime;
    private Date updateTime;
}
