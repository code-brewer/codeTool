package org.huangt.temp;
 
import org.huangt.common.DbConfBean;
import org.huangt.common.dbconn.OracleDb;

class CodeToolDbConnect extends OracleDb {

	private static CodeToolDbConnect connect = null;
	private DbConfBean masterBean = null;
	private CodeToolDbConnect (){
		
	}
	public static CodeToolDbConnect instance(){
		if(connect == null)
			connect = new CodeToolDbConnect();
		return connect;
	}

	@Override
	protected String getEncode() {
		return "utf-8";
	}
	@Override
	public boolean printSql() {
 		return true;
	}
	@Override
	protected DbConfBean getMasterDb() {
		if(masterBean == null){
			masterBean = new DbConfBean();
//			masterBean.setDbName(CommonCfg.readValue(DbConnVarDefine.DB_MYSQL_NAME));
//			masterBean.setIpAndPort(CommonCfg.readValue(DbConnVarDefine.DB_MYSQL_IP));
//			masterBean.setUserName(CommonCfg.readValue(DbConnVarDefine.DB_MYSQL_USERNAME));
//			masterBean.setPassword(CommonCfg.readValue(DbConnVarDefine.DB_MYSQL_PASSWORD));
			
			masterBean.setDbName("orcl");
			masterBean.setIpAndPort("localhost:1521");
			masterBean.setUserName("malliance");
			masterBean.setPassword("malliance");
		}
		return masterBean;
	}
	@Override
	protected DbConfBean[] getSlaveDbArray() {
		// TODO Auto-generated method stub
		return null;
	}
}