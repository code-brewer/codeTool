package org.huangt.core;
/** 
 * 类说明  '数据库对象'
 * @author : huangtao
 * @version 创建时间：2016-3-13 下午6:21:20 
 */
public class Field {
	/**字段名称*/
	private String fieldName;
	/**字段类型*/
	private String fieldType;
	/**字段长度*/
	private String fieldLength;
	/**字段注释*/
	private String fileComments;
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getFieldLength() {
		return fieldLength;
	}
	public void setFieldLength(String fieldLength) {
		this.fieldLength = fieldLength;
	}
	public String getFileComments() {
		return fileComments;
	}
	public void setFileComments(String fileComments) {
		this.fileComments = fileComments;
	}
	
	
	

}
