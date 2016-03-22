package org.huangt.common;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.huangt.core.Field;

public interface DbConnectTool {
	
	public Connection getConnection(boolean writeFlag) throws SQLException;
	
	/**
	 * 是否将执行的SQL打印到日志
	 * @return 
	 */
	public boolean printSql();
	
	public String formatPagerSql(String sql, int startIndex, int length);
	 
	public List<String> allTablesName()  ;
	
	/**
	 * 返回某个表的所有字段及字段类型，字段与字段类型之间用逗号分隔
	 * @param table
	 * @return
	 */
	public List<Field> allFields(String table);
	
}
