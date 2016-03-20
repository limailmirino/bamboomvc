package it.bamboolab.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Enrico on 30/07/2015.
 */

public class JdbcConnection {

    static Connection connection = null;



    public static Connection getInstance() {

        Connection connection = null;

        try {
                String mySqlConnectionUrl = "jdbc:mysql://10.10.10.234:3306/user_management2";
                String jdbcUser = "um_user";
                String jdbcPassword = "DawnlightSm!les";

                connection = DriverManager.getConnection(mySqlConnectionUrl, jdbcUser, jdbcPassword);

            } catch (SQLException e) {
                e.printStackTrace();
            }


        return connection;
    }
}
