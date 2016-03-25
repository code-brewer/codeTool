package org.huangt.core.Factory;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.huangt.core.BaseBean;
import org.huangt.core.BaseFactory;
import org.huangt.core.Field;
import org.huangt.core.bean.JavaBean;
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
	
	public JavaBeanFactory(String packageName, String tableName,
			List<Field> fields,String pk, String modulePath, String outDir) {
		super(packageName, tableName, fields, pk, modulePath, outDir);
	}

	private static HashMap<String, String> typeMaps = new HashMap<String, String>();
	private static HashMap<String, String> pagckageMaps = new HashMap<String, String>();
	static{
		typeMaps.put("varchar2", "String");
		typeMaps.put("varchar", "String");
		typeMaps.put("number", "Long");
		typeMaps.put("date", "Date");
		
		pagckageMaps.put("date", "java.util.Date");
	}
	
	@Override
	public BaseBean createBean() {
		JavaBean bean = new JavaBean();
		//类属性,类需要导入的包名解析
		List<HashMap<String,String>> attrList = new ArrayList<HashMap<String,String>>();
		List<String> packageList = new ArrayList<String>();
		HashMap<String, String> map = null;
		for(Field field:super.getFields()){
			map = new HashMap<String, String>();
			map.put("attrName",FuncStatic.convertLowerCaseToHump(field.getFieldName()));
			map.put("attrType", typeMaps.get(StringUtils.lowerCase(field.getFieldType())));
			map.put("attrComments", field.getFileComments());
			map.put("attrMethodName", FuncStatic.convertUpperCaseToHump(field.getFieldName()));
			attrList.add(map);
			//类需要导入的包名解析
			packageList = FuncStatic.add(pagckageMaps.get(StringUtils.lowerCase(field.getFieldType())), packageList);
			
		}
		//类,文件名解析
		bean.setAttrList(attrList);
		bean.setPackageList(packageList);
		bean.setPackageName(super.getPackageName());
		bean.setClassName(FuncStatic.convertUpperCaseToHump(super.getTableName()));
		bean.setPathName(super.getOutDir()+"\\"+super.packageToPath(super.getPackageName())+"\\"+bean.getClassName()+".java");
		
		return bean;
	}
	@Override
	public void createFile(BaseBean baseBean){
		try {
			Configuration cfg = new Configuration();
			Template temp = cfg.getTemplate(super.getModulePath());
			StringWriter sw = new StringWriter();
			HashMap<String, Object> value = new HashMap<String, Object>();
			value.put("bean",baseBean);
			temp.process(value, sw);
			FuncFile.insertFile(baseBean.getPathName(), sw.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
