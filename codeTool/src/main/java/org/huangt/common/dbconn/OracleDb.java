package org.huangt.common.dbconn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.huangt.common.DbConfBean;
import org.huangt.common.DbConnectTool;
import org.huangt.core.Field;
import org.huangt.tool.FuncStatic;


public abstract class OracleDb implements DbConnectTool {

	private static Logger logger = Logger.getLogger(OracleDb.class);
	protected BasicDataSource masterDataSource = null;
	protected BasicDataSource[] slaveDataSourceArray = null;
	private String getConnectUrl(DbConfBean confBean) {
		String cons = "jdbc:oracle:thin:@"
				+ confBean.getIpAndPort()
				+ ":"
				+ confBean.getDbName();
				//+ "?useOldAliasMetadataBehavior=true&autoReconnect=true&failOverReadOnly=false&characterEncoding="+getEncode();
		logger.info("db connect:" + cons);
		return cons;
	}
	private int slaveIndex = 0;
	private int slaveDataSourceLength = 0;
	protected abstract DbConfBean getMasterDb();
	protected abstract DbConfBean[] getSlaveDbArray();
	protected abstract String getEncode();
	protected OracleDb(){
		initDataSource();
	}
	protected void initDataSource() {
		try {
			masterDataSource = new BasicDataSource(); 
			//masterDataSource.setValidationQuery("select 1");
			masterDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			masterDataSource.setUrl(getConnectUrl(getMasterDb()));
			masterDataSource.setUsername(getMasterDb().getUserName());
			masterDataSource.setPassword(getMasterDb().getPassword());
			 
			HashMap<String, String> masterPoolConf = getMasterDb().getPoolConf();
			for(String key : masterPoolConf.keySet()){
				BeanUtils.setProperty(masterDataSource, key, masterPoolConf.get(key));
			}
		} catch (Exception e) {
			logger.error("数据库初始化错误",e);
		}
 	}
	@Override
	public Connection getConnection(boolean writeFlag) throws SQLException{
		if(this.slaveDataSourceArray == null){
			return this.masterDataSource.getConnection();
		}
		if(writeFlag){
			return this.masterDataSource.getConnection();
		}else{
			this.slaveIndex ++;
			if(this.slaveIndex >= slaveDataSourceLength){
				this.slaveIndex = 0;
			}
			return this.slaveDataSourceArray[this.slaveIndex].getConnection();
		}
 	}
	
	@Override
	public String formatPagerSql(String sql, int startIndex, int length) {
		return sql + " limit " + startIndex + ","+length;
	}

	public List<String> allTablesName()  {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		try {
			conn = this.getConnection(true);
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery("select * from  all_tables where owner='MALLIANCE'");
			while (resultSet.next()){
				list.add(resultSet.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(resultSet != null)
					resultSet.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(stmt != null)
					stmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 返回某个表的所有字段,字段类型，字段注释， 是否自增长,
	 * @param table
	 * @return
	 */
	public List<Field> allFields(String table){
		List<Field> list = new ArrayList<Field>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		try {
			conn = this.getConnection(true);
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery("select uac.COLUMN_NAME,uac.DATA_TYPE,uac.DATA_LENGTH,ucc.COMMENTS" +
					" from user_tab_columns uac join user_col_comments ucc" +
					" on uac.COLUMN_NAME = ucc.COLUMN_NAME where uac.TABLE_NAME = ucc.TABLE_NAME" +
					" and uac.TABLE_NAME = '"+table+"' ");
			Field field = null;
			while (resultSet.next()){
				HashMap<String, String> map = new HashMap<String, String>();
				field = new Field();
				field.setFieldName(resultSet.getString("COLUMN_NAME"));
				field.setFieldType(resultSet.getString("DATA_TYPE"));
				field.setFieldLength(resultSet.getString("DATA_LENGTH"));
				field.setFileComments(resultSet.getString("COMMENTS"));
				list.add(field);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(resultSet != null)
					resultSet.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(stmt != null)
					stmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	 
	/**
	 * 多个主键用逗号隔开
	 * @param table
	 * @return
	 */
	public String getKeyFieldName(String table)
	{ 
		String key = new String();
		
		return key;
	}
  
	
	public BasicDataSource getMasterBasicDataSource(){
		return this.masterDataSource;
	}
	
	public BasicDataSource[] getSlaveBasicDataSource(){
		return this.slaveDataSourceArray;
	}
	
}
