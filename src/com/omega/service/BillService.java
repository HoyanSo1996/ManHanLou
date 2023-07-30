package com.omega.service;

import com.omega.dao.BillDAO;
import com.omega.dao.DiningTableDAO;
import com.omega.dao.MenuDAO;
import com.omega.domain.Menu;

import java.util.UUID;

import static com.omega.utils.CommonUtil.BILL_STATE;
import static com.omega.utils.CommonUtil.TABLE_STATE;

/**
 * @author KennySo
 * @version 1.0
 */
public class BillService {

    private final BillDAO billDAO = new BillDAO();
    private final MenuDAO menuDAO = new MenuDAO();
    private final DiningTableDAO diningTableDAO = new DiningTableDAO();


    /**
     * 下单模块
     * 1. 生成账单
     * 2. 更新下单餐桌状态
     * 如果成功返回 true, 如果失败返回 false
     */
    public boolean orderMenu(int diningTableId, int menuId, int nums) {
        // 使用uuid生成账单号
        String billId = UUID.randomUUID().toString();
        // 查询菜品价格
        Menu menu = menuDAO.querySingle(
                "select * from menu where id = ?",
                Menu.class,
                menuId);
        Double price = menu.getPrice();

        // 创建新订单
        int insertResult = billDAO.update(
                "insert into bill values(null, ?, ?, ?, ?, ?, now(), ?)",
                billId, diningTableId, menuId, nums, price * nums, BILL_STATE.UNPAID.getVal());

        // 更新下单餐桌状态
        if (insertResult > 0) {
            int updateResult = diningTableDAO.update(
                    "update diningTable set state = ? where id = ? and state = 0",
                    TABLE_STATE.IN_USE.getVal(), diningTableId);
        }

        return insertResult > 0;
    }

}
