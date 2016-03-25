package org.huangt.core.bean;

import org.huangt.core.BaseBean;


public class JavaServiceBean extends BaseBean{
	/** 类的主键属性 */
	private String pk;
	/** 处理类名称 */
	private String beanClassName;
	/** 处理类包名 */
	private String beanPackageName;
	/** bean命名空间*/
	private String nameSpace;

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

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
}