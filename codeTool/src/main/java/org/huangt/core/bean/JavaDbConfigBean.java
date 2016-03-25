package org.huangt.core.bean;

import java.util.HashMap;
import java.util.List;

import org.huangt.core.BaseBean;

public class JavaDbConfigBean extends BaseBean{
		/** b类属性名称,属性类型, 属性方法 */
		private List<HashMap<String, String>> attrList;
		/** 命名空间 */
		private String nameSpace;
		/** 对应bean的java类 */
		private String beanClassName;
		/** 对应bean的包名 */
		private String beanPackageName;
		/** 表名 */
		private String tableName;
		public List<HashMap<String, String>> getAttrList() {
			return attrList;
		}
		public void setAttrList(List<HashMap<String, String>> attrList) {
			this.attrList = attrList;
		}
		public String getNameSpace() {
			return nameSpace;
		}
		public void setNameSpace(String nameSpace) {
			this.nameSpace = nameSpace;
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
		public String getTableName() {
			return tableName;
		}
		public void setTableName(String tableName) {
			this.tableName = tableName;
		}
		
		
	}