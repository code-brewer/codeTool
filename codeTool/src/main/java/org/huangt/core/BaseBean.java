package org.huangt.core;

import java.util.Date;
import java.util.List;

public class BaseBean {
	/** 类目录 */
	private String pathName;
	/** 类名称 */
	private String className;
	/** 类需要导入的包 */
	private List<String> packageList;
	/** 类包名 */
	private String packageName;
	/** 类作者*/
	private String author = "auto codeTool";
	/** 类更新时间*/
	private Date updateDate = new Date();
	
	public String getPathName() {
		return pathName;
	}
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public List<String> getPackageList() {
		return packageList;
	}
	public void setPackageList(List<String> packageList) {
		this.packageList = packageList;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
