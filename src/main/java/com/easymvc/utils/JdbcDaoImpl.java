package com.easymvc.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


/**
 * 使用QueryRunner 提供其具体的类型
 * 
 * @author Administrator
 *
 * @param <T>:子类需传入的泛型类型
 */
public class JdbcDaoImpl<T> implements DAOUtils<T> {

	private QueryRunner queryRunner = null;
	private Class<T> type;

	public JdbcDaoImpl() {
		queryRunner = new QueryRunner();
		type=ReflectionUtils.getSuperGenericType(getClass());
	}

	
	public void batch(Connection connection, String sql, Object[]... args) {
		
	}

	
	public <E> E getValue(Connection connection, String sql, Object... args) throws SQLException  {
		return queryRunner.query(connection, sql, new ScalarHandler<E>(),args);
	}

	
	public List<T> getList(Connection connection, String sql, Object... args) throws SQLException {
		return queryRunner.query(connection, sql,new BeanListHandler<T>(type),args);
	}
	
	
	public T get(Connection connection, String sql, Object... args) throws SQLException {
		return queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
	}

	
	public void update(Connection connection, String sql, Object... args) throws SQLException {
		queryRunner.update(connection, sql, args);
	}
	
	public void delete(Connection connection, String sql, Object... args) throws SQLException {
		queryRunner.update(connection, sql, args);
	}

}
