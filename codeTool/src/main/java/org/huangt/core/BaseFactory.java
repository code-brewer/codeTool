package org.huangt.core;

import java.util.List;

/** 
 * 类说明  ''
 * @author : huangtao
 * @version 创建时间：2016-3-13 下午6:30:27 
 */
public abstract class BaseFactory {
	/** 表名 */
	private String tableName;
	/** 包名 格式：目录1.目录2.目录3.目录4.目录5  则倒数第二的目录4为 命名空间  */
	private String packageName;
	/** 输出目录  */
	private String outDir;
	/** 模板路径  */
	private String modulePath;
	/** 主键  */
	private String pk;
	/** 表字段集合  */
	private List<Field> fields;
	/** 命名空间  */
	private String nameSpace;
	
	public BaseFactory(String packageName,String tableName,List<Field> fields, String pk, String modulePath, String outDir) {
		this.packageName = packageName;
		this.tableName = tableName;
		this.fields = fields;
		this.pk = pk;
		this.modulePath = modulePath;
		this.outDir = outDir;
		subNameSpace(packageName);
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public String getOutDir() {
		return outDir;
	}
	public void setOutDir(String outDir) {
		this.outDir = outDir;
	}
	public String getModulePath() {
		return modulePath;
	}
	public void setModulePath(String modulePath) {
		this.modulePath = modulePath;
	}
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	public String getNameSpace() {
		return nameSpace;
	}
	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
	
	/**
	 * 格式化表字段数据,翻译成本工厂需要bean
	 */
	public abstract BaseBean createBean();
	
	/**
	 * 根据翻译的bean生产相应文件
	 * @throws Exception 
	 */
	public abstract void createFile(BaseBean baseBean) throws Exception;
	
	/**
	 * 根据包名 获取命名空间
	 * @param packageName
	 */
	private void subNameSpace(String packageName){
		if(packageName.indexOf(".")>=0){
			packageName = packageName.substring(0,packageName.lastIndexOf("."));
			if(packageName.indexOf("service") >= 0){
				packageName = packageName.substring(0,packageName.lastIndexOf("."));
			}
			nameSpace = packageName.substring(packageName.lastIndexOf(".")+1,packageName.length());
		}else{
			nameSpace = packageName;
		}
	}
	
	protected String packageToPath(String packageName){
		return packageName.replaceAll("\\.", "\\\\");
	}
	
}
