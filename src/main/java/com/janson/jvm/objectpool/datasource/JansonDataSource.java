package com.janson.jvm.objectpool.datasource;

import org.apache.commons.pool2.impl.GenericObjectPool;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2023/2/25 17:08
 **/
public class JansonDataSource implements DataSource {

    private GenericObjectPool<Connection> pool;

    public JansonDataSource() {
        this.pool = new GenericObjectPool<>(new ConnectionPooledObjectFactory());
    }

    @Override
    public Connection getConnection() throws SQLException {
        try {
            return this.pool.borrowObject();
        } catch (Exception e) {
            throw new SQLException("获取连接失败!", e);
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return this.getConnection();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new UnsupportedOperationException("不支持的操作!");
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new UnsupportedOperationException("不支持的操作!");
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        throw new UnsupportedOperationException("不支持的操作!");
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        throw new UnsupportedOperationException("不支持的操作!");
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        throw new UnsupportedOperationException("不支持的操作!");
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        throw new UnsupportedOperationException("不支持的操作!");
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new UnsupportedOperationException("不支持的操作!");
    }
}
