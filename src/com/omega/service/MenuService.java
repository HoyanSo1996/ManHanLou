package com.omega.service;

import com.omega.dao.MenuDAO;
import com.omega.domain.Menu;

import java.util.List;

/**
 * @author KennySo
 * @version 1.0
 */
public class MenuService {

    private final MenuDAO menuDAO = new MenuDAO();


    public List<Menu> getMenuList() {
        return menuDAO.queryMany(
                "select * from menu",
                Menu.class);
    }


    public Menu getMenuById(Integer menuId) {
        return menuDAO.querySingle(
                "select * from menu where id = ?",
                Menu.class,
                menuId);
    }
}
