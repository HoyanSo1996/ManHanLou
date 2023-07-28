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
        return diningTableDAO.queryMany("select * from diningTable", DiningTable.class);
    }
}
