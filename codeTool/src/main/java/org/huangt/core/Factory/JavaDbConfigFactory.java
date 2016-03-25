package org.huangt.core.Factory;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.huangt.core.BaseBean;
import org.huangt.core.BaseFactory;
import org.huangt.core.Field;
import org.huangt.core.bean.JavaDbConfigBean;
import org.huangt.tool.FuncFile;
import org.huangt.tool.FuncStatic;

import freemarker.template.Configuration;
import freemarker.template.Template;

/** 
 * 类说明  'dbconfig 生产'
 * @author : huangtao
 * @version 创建时间：2016-3-13 下午6:29:33 
 */
public class JavaDbConfigFactory extends BaseFactory{

	public JavaDbConfigFactory(String packageName, String tableName,
			List<Field> fields, String pk, String modulePath, String outDir) {
		super(packageName, tableName, fields, pk, modulePath, outDir);
	}
	@Override
	public BaseBean createBean() {
		JavaDbConfigBean bean = new JavaDbConfigBean();
		//类属性,类需要导入的包名解析
		List<HashMap<String,String>> attrList = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> map = null;
		for(Field field:super.getFields()){
			map = new HashMap<String, String>();
			map.put("columnName", field.getFieldName());
			map.put("attrName",FuncStatic.convertLowerCaseToHump(field.getFieldName()));
			if(map.get("columnName").equals(super.getPk())){
				map.put("isPk","y");
			}else{
				map.put("isPk","n");
			}
			attrList.add(map);
		}
		//类,文件名解析
		bean.setAttrList(attrList);
		bean.setTableName(super.getTableName());
		bean.setClassName(FuncStatic.convertUpperCaseToHump(super.getTableName()));
		bean.setNameSpace(super.getNameSpace()+"."+bean.getClassName());
		bean.setPathName(super.getOutDir()+"\\"+super.packageToPath(super.getPackageName())+"\\"+bean.getClassName()+".xml");
		
		return bean;
	}

	@Override
	public void createFile(BaseBean bean){
		try {
			Configuration cfg = new Configuration();
			Template temp = cfg.getTemplate(super.getModulePath());
			StringWriter sw = new StringWriter();
			HashMap<String, Object> value = new HashMap<String, Object>();
			value.put("bean",bean);
			temp.process(value, sw);
			FuncFile.insertFile(bean.getPathName(), sw.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
