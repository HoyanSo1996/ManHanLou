package com.omega.service;

import com.omega.dao.DiningTableDAO;
import com.omega.domain.DiningTable;

import java.util.List;

/**
 * @author KennySo
 * @version 1.0
 */
public class DiningTableService {

    private final DiningTableDAO diningTableDAO = new DiningTableDAO();


    /**
     * get all dining table's state
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
     * update the state of diningTable
     */
    public boolean updateDiningTableById(Integer id, String orderName, String orderTel) {
        int affectedRow = diningTableDAO.update(
                "update diningTable set state = '1', orderName = ?, orderTel = ? where id = ?",
                orderName, orderTel, id);
        return affectedRow > 0;
    }
}
