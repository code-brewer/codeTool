package org.huangt.core.Factory;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.huangt.core.BaseFactory;
import org.huangt.core.Field;
import org.huangt.tool.FuncFile;
import org.huangt.tool.FuncStatic;

import freemarker.template.Configuration;
import freemarker.template.Template;

/** 
 * 类说明  'javaBean 生产'
 * @author : huangtao
 * @version 创建时间：2016-3-13 下午6:29:33 
 */
public class JavaBeanFactory extends BaseFactory{
	
	private static HashMap<String, String> typeMaps = new HashMap<String, String>();
	private static HashMap<String, String> pagckageMaps = new HashMap<String, String>();
	private JavaBean bean;
	static{
		typeMaps.put("varchar2", "String");
		typeMaps.put("varchar", "String");
		typeMaps.put("number", "Long");
		typeMaps.put("date", "Date");
		
		pagckageMaps.put("date", "java.util.Date");
	}
	
	@Override
	public void parseData() {
		bean = new JavaBean();
		List<HashMap<String,String>> mFields = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> map = null;
		for(Field field:this.getFields()){
			map = new HashMap<String, String>();
			map.put("attrName",FuncStatic.convertLowerCaseToHump(field.getFieldName()));
			map.put("attrType", typeMaps.get(StringUtils.lowerCase(field.getFieldType())));
			map.put("attrComments", field.getFileComments());
			map.put("attrMethodName", FuncStatic.convertUpperCaseToHump(field.getFieldName()));
			
			mFields.add(map);
		}
		
	}

	@Override
	public void createFile(){
		try {
			Configuration cfg = new Configuration();
			Template temp = cfg.getTemplate(this.getModleName());
			StringWriter sw = new StringWriter();
			HashMap<String, Object> value = new HashMap<String, Object>();
			value.put("qlist", bean.getAttrList());
			value.put("targetPackage", bean.getPackageName());
			temp.process(value, sw);
			FuncFile.insertFile(this.getTargetPath()+"\\"+bean.getClassName()+".java", sw.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	class JavaBean{
		//bean属性名称,属性类型, 属性方法
		private List<HashMap<String, String>> attrList;
		//bean需要导入的包
		private Set<String> packageSet;
		//bean名称
		private String className;
		//bean包名
		private String packageName;
		public List<HashMap<String, String>> getAttrList() {
			return attrList;
		}
		public void setAttrList(List<HashMap<String, String>> attrList) {
			this.attrList = attrList;
		}
		public Set<String> getPackageSet() {
			return packageSet;
		}
		public void setPackageSet(Set<String> packageSet) {
			this.packageSet = packageSet;
		}
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}
		public String getPackageName() {
			return packageName;
		}
		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}
		
	}
}
