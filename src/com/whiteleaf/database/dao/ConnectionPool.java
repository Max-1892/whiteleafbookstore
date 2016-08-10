/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whiteleaf.database.dao;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;

/**
 *
 * @author ikilbou1
 */
public class ConnectionPool {
    private static ConnectionPool connectionPool = null;
    private static DataSource dataSource = null;

    private ConnectionPool() {
        try {
            InitialContext initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/whiteleaf");
        } catch (Exception e) {
            
        }
    }

    public static ConnectionPool getInstance() {
        if (connectionPool == null)
            connectionPool = new ConnectionPool();
        return connectionPool;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            return null;
        }
    }

    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            
        }
    }
}
