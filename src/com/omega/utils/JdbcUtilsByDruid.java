package com.omega.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Class BasicDAO
 *
 * @author KennySu
 * @date 2023/7/28
 */
@SuppressWarnings("unused")
public class JdbcUtilsByDruid {


    public static Connection getConnection() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src\\druid-config.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            return dataSource.getConnection();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @Note:
     *      DBUtils-1.7 版本实现了 resultSet, statement, connection 全自动关闭, 所以不用调用下面的方法
     */
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        // 由动态绑定可知, 此处调用的close()是连接池对象的close()
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
