package com.omega.service;

import com.omega.dao.EmployeeDAO;
import com.omega.domain.Employee;
import com.omega.utils.MD5Util;

/**
 * Class EmployeeService
 *
 * @author KennySu
 * @date 2023/7/28
 */
public class EmployeeService {

    private final EmployeeDAO employeeDAO = new EmployeeDAO();


    /**
     * Validate employee's info
     * Todo: 设置密码最多错三次，且可以循环三次输入密码
     */
    public boolean validateEmployeeInfo(String empId, String password) {
        Employee employee = employeeDAO.querySingle(
                "select * from employee where empId = ?",
                Employee.class,
                empId);

        if (employee == null) {
            System.out.println("员工号不存在.");
            return false;
        }
        if (!employee.getPassword().equals(MD5Util.getMD5(password))) {
            System.out.println("密码不正确.");
            return false;
        }

        // 员工号和密码验证成功
        return true;
    }
}
