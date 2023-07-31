package com.omega.view;

import com.omega.domain.Bill;
import com.omega.domain.DiningTable;
import com.omega.domain.Menu;
import com.omega.service.BillService;
import com.omega.service.DiningTableService;
import com.omega.service.EmployeeService;
import com.omega.service.MenuService;
import com.omega.utils.Utility;

import java.util.List;

import static com.omega.utils.CommonUtil.BILL_STATE;
import static com.omega.utils.CommonUtil.TABLE_STATE;

/**
 * Class Menu
 *
 * @author KennySu
 * @date 2023/7/28
 */
public class MHLMenu {

    private final EmployeeService employeeService = new EmployeeService();
    private final DiningTableService diningTableService = new DiningTableService();
    private final MenuService menuService = new MenuService();
    private final BillService billService = new BillService();


    /**
     * 一级菜单
     */
    public void menu01() {
        while (true) {
            System.out.println("================== 满 汉 楼 ==================");
            System.out.println("================== 一级菜单 ==================");
            System.out.println("\t\t\t\t 1 登    录");
            System.out.println("\t\t\t\t 2 退    出");

            System.out.print("请输入您的选择: ");
            String choice = Utility.readString(1);
            switch (choice) {
                case "1":
                    if (login()) {
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
            System.out.println("\t\t\t\t 6 结 账 服 务");
            System.out.println("\t\t\t\t 9 返 回 上 级");

            System.out.print("请输入您的选择: ");
            String choice = Utility.readString(1);
            switch (choice) {
                case "1":
                    displayDiningTableList();
                    break;
                case "2":
                    orderDiningTable();
                    break;
                case "3":
                    displayMenu();
                    break;
                case "4":
                    orderMenu();
                    break;
                case "5":
                    displayBillList();
                    break;
                case "6":
                    checkout();
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
        System.out.print("请输入 员工号: ");
        String empId = Utility.readString(32);
        System.out.print("请输入 密  码: ");
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
     * Show the state of diningTable module
     */
    public void displayDiningTableList() {
        List<DiningTable> diningTableList = diningTableService.getDiningTableList();
        System.out.println("==================== 餐桌列表 ====================");
        System.out.println("\n餐桌编号\t\t餐桌状态");
        for (DiningTable diningTable : diningTableList) {
            System.out.println(diningTable);
        }
        System.out.println("==================== 显示完毕 ====================");
    }


    /**
     * Order diningTable module
     */
    public void orderDiningTable() {
        System.out.println("==================== 预定餐桌 ====================");

        // 1.判断是否取消预定
        System.out.print("请选择要预定的餐桌编号(-1退出): ");
        int id = Utility.readInt();
        if (id == -1) {
            System.out.println("==================== 退出预定 ====================");
            return;
        }

        // 2.判断餐桌是否是空置的
        DiningTable diningTable = diningTableService.getDiningTableById(id);
        if (diningTable.getState().equals(TABLE_STATE.IN_USE.getVal())) {
            System.out.println("==================== 餐桌已被预定或使用 ====================");
            return;
        }

        // 3.确认预定
        System.out.print("确认是否预定(Y/N): ");
        char c = Utility.readConfirmSelection();
        if (c == 'N') {
            System.out.println("==================== 取消预定 ====================");
            return;
        }
        System.out.print("预订人名字: ");
        String orderName = Utility.readString(32);
        System.out.print("预订人电话: ");
        String orderTel = Utility.readString(11);
        boolean orderResult = diningTableService.updateDiningTableById(id, orderName, orderTel);
        if (orderResult) {
            System.out.println("==================== 预定成功 ====================");
        } else {
            System.out.println("==================== 预定失败 ====================");
        }
    }


    /**
     * display menu module
     */
    public void displayMenu() {
        List<Menu> menuList = menuService.getMenuList();
        System.out.println("==================== 菜品清单 ====================");
        System.out.println("菜品标号\t\t菜品名\t\t类别\t\t\t价格");
        for (Menu menu : menuList) {
            System.out.println(menu);
        }
        System.out.println("==================== 显示完毕 ====================");
    }


    /**
     * order menu
     */
    public void orderMenu() {
        System.out.println("==================== 点餐服务 ====================");

        while (true) {
            System.out.print("请选择点餐的桌号(-1退出): ");
            int diningTableId = Utility.readInt();
            if (diningTableId == -1) {
                System.out.println("==================== 退出点餐 ====================");
                return;
            }
            if (diningTableService.getDiningTableById(diningTableId) == null) {
                System.out.println("餐桌不存在~");
                continue;
            }

            System.out.print("请选择菜品的编号(-1退出): ");
            int menuId = Utility.readInt();
            if (menuId == -1) {
                System.out.println("==================== 退出点餐 ====================");
                return;
            }
            if (menuService.getMenuById(menuId) == null) {
                System.out.println("菜品不存在~");
                continue;
            }

            System.out.print("请选择菜品的数量(-1退出): ");
            int nums = Utility.readInt();
            if (nums == -1) {
                System.out.println("==================== 退出点餐 ====================");
                return;
            }

            System.out.print("确认是否点这个菜(Y/N): ");
            char result = Utility.readConfirmSelection();
            if (result == 'Y') {
                boolean b = billService.orderMenu(diningTableId, menuId, nums);
                if (b) {
                    System.out.println("==================== 点餐成功 ====================");
                } else {
                    System.out.println("==================== 点餐失败 ====================");
                }
            } else {
                System.out.println("==================== 退出点餐 ====================");
            }
            return;
        }
    }


    /**
     * display the bill
     */
    public void displayBillList() {
        List<Bill> billList = billService.getBillList();
        System.out.println("==================== 账单列表 ====================");
        System.out.println("编号\t\t桌号\t\t菜品号\t\t菜品量\t\t金额\t\t\t日期\t\t\t\t\t\t\t状态");
        for (Bill bill : billList) {
            System.out.println(bill);
        }
        System.out.println("==================== 显示完毕 ====================");
    }


    /**
     * checkout
     */
    public void checkout() {
        System.out.println("==================== 结账服务 ====================");

        while (true) {
            System.out.print("请选择要结账的餐桌编号(-1退出): ");
            int diningTableId = Utility.readInt();
            if (diningTableId == -1) {
                return;
            }
            if (diningTableService.getDiningTableById(diningTableId) == null) {
                System.out.println("餐桌不存在~");
                continue;
            }

            // check the diningTable for unpaid bill
            List<Bill> billList = billService.getBillListByDiningTableId(diningTableId);
            if (billList.size() == 0) {
                System.out.println("该餐桌不存在订单~");
                continue;
            }
            double amount = 0;
            for (Bill bill : billList) {
                amount += bill.getMoney();
            }
            System.out.println("应付金额: ￥" + amount);

            System.out.print("结账的方式(现金/支付宝/微信): ");
            String payMode = Utility.readString(10);
            if (!(payMode.equals(BILL_STATE.CASH.getVal()) ||
                    payMode.equals(BILL_STATE.ALI_PAY.getVal()) ||
                    payMode.equals(BILL_STATE.WX_PAY.getVal()))) {
                System.out.println("支付方式错误~");
                continue;
            }

            System.out.print("确认是否结账(Y/N): ");
            char c = Utility.readConfirmSelection();
            if (c == 'Y') {
                boolean result = billService.payTheBill(diningTableId, payMode);
                System.out.println(result ? "结账成功~~" : "结账失败~~");
                System.out.println("==================== 结账完成 ====================");
            } else {
                System.out.println("==================== 取消结账 ====================");
            }
            return;
        }
    }
}
