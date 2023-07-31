package com.omega.service;

import com.omega.dao.DiningTableDAO;
import com.omega.domain.DiningTable;
import com.omega.utils.CommonUtil;

import java.util.List;

import static com.omega.utils.CommonUtil.*;

/**
 * @author KennySo
 * @version 1.0
 */
public class DiningTableService {

    private final DiningTableDAO diningTableDAO = new DiningTableDAO();


    /**
     * get dining table's state List
     */
    public List<DiningTable> getDiningTableList() {
        // Tips: 可变参数的实参可以不填
        return diningTableDAO.queryMany(
                "select * from diningTable",
                DiningTable.class);
    }


    /**
     * get diningTable by id
     */
    public DiningTable getDiningTableById(int id) {
        return diningTableDAO.querySingle(
                "select * from diningTable where id = ?",
                DiningTable.class,
                id);
    }


    /**
     * 预定餐桌
     */
    public boolean updateDiningTableStateToBooked(Integer diningTableId, String orderName, String orderTel) {
        int affectedRows = diningTableDAO.update(
                "update diningTable set state = ?, orderName = ?, orderTel = ? where id = ?",
                TABLE_STATE.BOOKED.getVal(), orderName, orderTel, diningTableId);
        return affectedRows > 0;
    }


    /**
     * 点餐时更新餐桌状态
     */
    public boolean updateDiningTableStateToUsed(Integer diningTableId) {
        int affectedRows = diningTableDAO.update(
                "update diningTable set state = ? where id = ? and state = ?",
                TABLE_STATE.IN_USE.getVal(), diningTableId, TABLE_STATE.BOOKED.getVal());
        return affectedRows > 0;
    }


    /**
     * clear dining table
     */
    public boolean clearDiningTableById(int diningTableId) {
        int affectRow = diningTableDAO.update(
                "update diningTable set state = ?, orderName = '', orderTel = '' where id = ?",
                TABLE_STATE.EMPTY.getVal(), diningTableId);
        return affectRow > 0;
    }
}
