package com.easymvc.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 访问数据的DAO接口
 * 定义了访问数据表的各种方法
 * @author Administrator
 *
 * @param <T>:DAO处理的实体类的类型
 */
public interface DAOUtils<T> {

	/**
	 * 批量处理的方法
	 * @param connection
	 * @param sql
	 * @param args:填充占位符的Object[]类型的可变参数
	 */
	void batch(Connection connection,String sql,Object[]...args);
	/**
	 * 返回具体的一个值。例如:总人数,平均工资,某一个人的email等.
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException 
	 */
	<E> E getValue(Connection connection,String sql,Object ... args) throws SQLException;
	
	/**
	 * 返回T的一个集合
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException 
	 */
	List<T> getList(Connection connection,String sql,Object...args) throws SQLException;
	
	/**
	 * 返回一个T的对象
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException 
	 */
	T get(Connection connection,String sql,Object...args) throws SQLException;
	
	/**
	 * INSERT,UPDATE,DELETE
	 * @param connection:数据库连接
	 * @param sql:SQL语句
	 * @param args:填充占位符的可变参数
	 * @throws SQLException 
	 */
	void update(Connection connection, String sql, Object... args) throws SQLException;
}
