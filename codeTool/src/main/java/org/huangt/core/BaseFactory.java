package org.huangt.core;

import java.util.HashMap;
import java.util.List;

/** 
 * 类说明  ''
 * @author : huangtao
 * @version 创建时间：2016-3-13 下午6:30:27 
 */
public abstract class BaseFactory {
	
	private String tableName;
	private String modleName;
	private String targetPackage;
	private String targetPath;
	private List<Field> fields;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getModleName() {
		return modleName;
	}
	public void setModleName(String modleName) {
		this.modleName = modleName;
	}
	public String getTargetPackage() {
		return targetPackage;
	}
	public void setTargetPackage(String targetPackage) {
		this.targetPackage = targetPackage;
	}
	public String getTargetPath() {
		return targetPath;
	}
	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	/**
	 * 格式化表字段数据,翻译成本工厂需要的字段
	 */
	public abstract void parseData();
	/**
	 * 根据翻译的字段,生产相应文件
	 * @throws Exception 
	 */
	public abstract void createFile() throws Exception;
	
	public void execute(){
		parseData();
		try {
			createFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
