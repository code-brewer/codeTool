package org.huangt.core.bean;

import java.util.HashMap;
import java.util.List;

import org.huangt.core.BaseBean;


public class JspBean extends BaseBean{
	/** 类的主键属性  */
	private String pk;
	/** 处理类名称  */
	private String beanClassName;
	/** 控制器的类名  */
	private String controllerClassName;
	/** bean命名空间  */
	private String nameSpace;
	/** b类属性名称,属性类型, 属性方法 */
	private List<HashMap<String, String>> attrList;
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
	public String getControllerClassName() {
		return controllerClassName;
	}
	public void setControllerClassName(String controllerClassName) {
		this.controllerClassName = controllerClassName;
	}
	public String getNameSpace() {
		return nameSpace;
	}
	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
	public List<HashMap<String, String>> getAttrList() {
		return attrList;
	}
	public void setAttrList(List<HashMap<String, String>> attrList) {
		this.attrList = attrList;
	}
	public String getPermissionCode() {
		return permissionCode;
	}
	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
	
	

}