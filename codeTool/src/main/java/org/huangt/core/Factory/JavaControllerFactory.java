package org.huangt.core.Factory;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import org.huangt.core.BaseBean;
import org.huangt.core.BaseFactory;
import org.huangt.core.Field;
import org.huangt.core.bean.JavaControllerBean;
import org.huangt.tool.FuncFile;
import org.huangt.tool.FuncStatic;

import freemarker.template.Configuration;
import freemarker.template.Template;

/** 
 * 类说明  'controller 生产'
 * @author : huangtao
 * @version 创建时间：2016-3-13 下午6:29:33 
 */
public class JavaControllerFactory extends BaseFactory{
	
	public JavaControllerFactory(String packageName, String tableName,
			List<Field> fields, String pk, String modulePath, String outDir) {
		super(packageName, tableName, fields, pk, modulePath, outDir);
	}
	@Override
	public BaseBean createBean() {
		JavaControllerBean bean = new JavaControllerBean();
		//类,文件名解析
		bean.setPackageName(super.getPackageName());
		bean.setPk(FuncStatic.convertLowerCaseToHump(super.getPk()));
		bean.setTableName(super.getTableName());
		bean.setClassName(FuncStatic.convertUpperCaseToHump(super.getTableName())+"Controller");
		bean.setNameSpace(super.getNameSpace());
		bean.setPathName(super.getOutDir()+"\\"+super.packageToPath(super.getPackageName())+"\\"+bean.getClassName()+".java");
		bean.setViewRoot(this.getViewRoot());
		bean.setPermissionCode(this.getPermissionCode());
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
	private String viewRoot;
	private String permissionCode;
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
