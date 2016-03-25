package org.huangt.core.Factory;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import org.huangt.core.BaseBean;
import org.huangt.core.BaseFactory;
import org.huangt.core.Field;
import org.huangt.core.bean.JavaServiceBean;
import org.huangt.tool.FuncFile;
import org.huangt.tool.FuncStatic;

import freemarker.template.Configuration;
import freemarker.template.Template;

/** 
 * 类说明  'service 生产'
 * @author : huangtao
 * @version 创建时间：2016-3-13 下午6:29:33 
 */
public class JavaServiceFactory extends BaseFactory{
	
	public JavaServiceFactory(String packageName, String tableName,
			List<Field> fields,String pk, String modulePath, String outDir) {
		super(packageName, tableName, fields, pk, modulePath, outDir);
	}
	@Override
	public BaseBean createBean() {
		JavaServiceBean bean = new JavaServiceBean();
		//类,文件名解析
		bean.setPackageName(super.getPackageName());
		bean.setPk(FuncStatic.convertLowerCaseToHump(super.getPk()));
		bean.setClassName(FuncStatic.convertUpperCaseToHump(super.getTableName())+"Service");
		bean.setNameSpace(super.getNameSpace()+"."+bean.getClassName());
		bean.setPathName(super.getOutDir()+"\\"+super.packageToPath(super.getPackageName())+"\\"+bean.getClassName()+".java");
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
