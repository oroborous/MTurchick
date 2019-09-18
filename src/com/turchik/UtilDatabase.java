package com.turchik;

import java.sql.*;

@SuppressWarnings("WeakerAccess")
public class UtilDatabase {
    public static final String DRIVER_NAME = "jdbc:derby:";
    public static final String USERNAME = "mark";
    public static final String PASSWORD = "admin";

    public static void closeAll(Connection conn, Statement stmt, ResultSet rset) {
        if (rset != null) {
            try {
                rset.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
