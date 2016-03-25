package org.huangt.core.bean;

import java.util.HashMap;
import java.util.List;

import org.huangt.core.BaseBean;

public class JavaBean extends BaseBean{
		//b类属性名称,属性类型, 属性方法
		private List<HashMap<String, String>> attrList;
		
		public List<HashMap<String, String>> getAttrList() {
			return attrList;
		}
		public void setAttrList(List<HashMap<String, String>> attrList) {
			this.attrList = attrList;
		}
	}