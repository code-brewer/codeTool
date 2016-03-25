package org.huangt.core.bean;

import org.huangt.core.BaseBean;


public class JavaControllerBean extends BaseBean{
	/** 类的主键属性  */
	private String pk;
	/** 处理类名称  */
	private String beanClassName;
	/** 处理类包名  */
	private String beanPackageName;
	/** service的类名  */
	private String serviceClassName;
	/** service的类包名  */
	private String servicePackageName;
	/** serviceImpl的类名  */
	private String serviceImplClassName;
	/** serviceImpl的类 包名  */
	private String serviceImplPackageName;
	
	/** bean命名空间  */
	private String nameSpace;
	/** 表名称 */
	private String tableName;
	/** 页面根目录 */
	private String viewRoot;
	/** 权限编码*/
	private String permissionCode;

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getBeanClassName() {
		return beanClassName;
	}

	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;
	}

	public String getBeanPackageName() {
		return beanPackageName;
	}

	public void setBeanPackageName(String beanPackageName) {
		this.beanPackageName = beanPackageName;
	}

	public String getServiceClassName() {
		return serviceClassName;
	}

	public void setServiceClassName(String serviceClassName) {
		this.serviceClassName = serviceClassName;
	}

	public String getServicePackageName() {
		return servicePackageName;
	}

	public void setServicePackageName(String servicePackageName) {
		this.servicePackageName = servicePackageName;
	}

	public String getServiceImplClassName() {
		return serviceImplClassName;
	}

	public void setServiceImplClassName(String serviceImplClassName) {
		this.serviceImplClassName = serviceImplClassName;
	}

	public String getServiceImplPackageName() {
		return serviceImplPackageName;
	}

	public void setServiceImplPackageName(String serviceImplPackageName) {
		this.serviceImplPackageName = serviceImplPackageName;
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getViewRoot() {
		return viewRoot;
	}

	public void setViewRoot(String viewRoot) {
		this.viewRoot = viewRoot;
	}

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
	
}