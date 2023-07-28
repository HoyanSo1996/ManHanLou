package com.omega.view;

import com.omega.domain.DiningTable;
import com.omega.service.DiningTableService;
import com.omega.service.EmployeeService;
import com.omega.utils.Utility;

import java.util.List;

/**
 * Class Menu
 *
 * @author KennySu
 * @date 2023/7/28
 */
public class Menu {

    private final EmployeeService employeeService = new EmployeeService();
    private final DiningTableService diningTableService = new DiningTableService();


    /**
     * 一级菜单
     */
    public void menu01() {
        while (true) {
            System.out.println("================== 满 汉 楼 ==================");
            System.out.println("================== 一级菜单 ==================");
            System.out.println("\t\t\t\t 1 登    录");
            System.out.println("\t\t\t\t 2 退    出");

            System.out.println("请输入您的选择: ");
            String choice = Utility.readString(1);
            switch (choice) {
                case "1":
                    if(login()) {
                        menu02();
                    }
                    break;

                case "2":
                    System.out.println("退出满汉楼系统~~");
                    return;

                default:
                    System.out.println("输入有误, 请重新输入.");
            }
        }
    }


    /**
     * 二级菜单
     */
    public void menu02() {
        while (true) {
            System.out.println("==================== 二级菜单 ====================");
            System.out.println("\t\t\t\t 1 显示餐桌状态");
            System.out.println("\t\t\t\t 2 预 定 餐 桌");
            System.out.println("\t\t\t\t 3 显示所有菜品");
            System.out.println("\t\t\t\t 4 点 餐 服 务");
            System.out.println("\t\t\t\t 5 查 看 账 单");
            System.out.println("\t\t\t\t 6 结      账");
            System.out.println("\t\t\t\t 9 返 回 上 级");

            System.out.println("请输入您的选择: ");
            String choice = Utility.readString(1);
            switch (choice) {
                case "1":
                    showDiningTableState();
                    break;

                case "2":
                    System.out.println("预 定 餐 桌");
                    break;

                case "3":
                    System.out.println("显示所有菜品");
                    break;

                case "4":
                    System.out.println("点 餐 服 务");
                    break;

                case "5":
                    System.out.println("查 看 账 单");
                    break;

                case "6":
                    System.out.println("结      账");
                    break;

                case "9":
                    System.out.println("退出二级菜单~~");
                    return;

                default:
                    System.out.println("输入有误, 请重新输入.");
            }
        }
    }


    /**
     * Login module
     */
    public boolean login() {
        System.out.println("================== 登录系统 ==================");
        System.out.println("请输入员工号: ");
        String empId = Utility.readString(32);
        System.out.println("请输入密  码: ");
        String password = Utility.readString(32);

        // 使用db验证员工号和密码
        if (!employeeService.validateEmployeeInfo(empId, password)) {
            System.out.println("[" + empId + " 登录失败] ");
            return false;
        } else {
            System.out.println("[" + empId + " 登录成功]");
            return true;
        }
    }


    /**
     * Showing table's state module
     */
    public void showDiningTableState() {
        List<DiningTable> diningTableList = diningTableService.getDiningTableList();
        System.out.println("\n餐桌编号\t\t餐桌转态");
        for (DiningTable diningTable : diningTableList) {
            System.out.println(diningTable);
        }
        System.out.println("==================== 显示完毕 ====================");
    }
}
