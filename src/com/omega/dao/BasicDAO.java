package com.omega.dao;

import com.omega.utils.JdbcUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Class BasicDAO
 *
 * @author KennySu
 * @date 2023/7/28
 */
public class BasicDAO<T> {

    private final QueryRunner qr = new QueryRunner();

    public int update(String sql, Object... parameters) {
        try {
            Connection connection = JdbcUtilsByDruid.getConnection();
            return qr.update(connection, sql, parameters);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 查询多行数据
     */
    public List<T> queryMany(String sql, Class<T> clazz, Object... parameters) {
        try {
            Connection connection = JdbcUtilsByDruid.getConnection();
            return qr.query(connection, sql, new BeanListHandler<>(clazz), parameters);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 查询单行数据
     */
    public T querySingle(String sql, Class<T> clazz, Object... parameters) {
        try {
            Connection connection = JdbcUtilsByDruid.getConnection();
            return qr.query(connection, sql, new BeanHandler<>(clazz), parameters);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 查询单行单列数据
     */
    public Object queryScalar(String sql, Object... parameters) {
        try {
            Connection connection = JdbcUtilsByDruid.getConnection();
            return qr.query(connection, sql, new ScalarHandler<>(), parameters);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
