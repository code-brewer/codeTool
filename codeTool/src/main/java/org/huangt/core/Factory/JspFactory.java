package org.huangt.core.Factory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.huangt.core.BaseBean;
import org.huangt.core.BaseFactory;
import org.huangt.core.Field;
import org.huangt.core.bean.JspBean;
import org.huangt.tool.FuncFile;
import org.huangt.tool.FuncStatic;

import freemarker.template.Configuration;
import freemarker.template.Template;

/** 
 * 类说明  'serviceImpl 生产'
 * @author : huangtao
 * @version 创建时间：2016-3-13 下午6:29:33 
 */
public class JspFactory extends BaseFactory{
	
	public JspFactory(String packageName, String tableName,
			List<Field> fields,String pk, String modulePath, String outDir) {
		super(packageName, tableName, fields, pk, modulePath, outDir);
	}
	@Override
	public BaseBean createBean() {
		JspBean bean = new JspBean();
		//类,文件名解析
		List<HashMap<String,String>> attrList = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> map = null;
		for(Field field:super.getFields()){
			if(!super.getPk().equals(field.getFieldName())){
				map = new HashMap<String, String>();
				map.put("attrName",FuncStatic.convertLowerCaseToHump(field.getFieldName()));
				map.put("attrComments", field.getFileComments());
				attrList.add(map);
			}
		}
		//类,文件名解析
		bean.setAttrList(attrList);
		bean.setPk(FuncStatic.convertLowerCaseToHump(super.getPk()));
		bean.setNameSpace(super.getNameSpace());
		bean.setPermissionCode(this.getPermissionCode());
		return bean;
	}
	
	@Override
	public void createFile(BaseBean bean){
		StringWriter sw = null;
		try {
			String [] modules = new String[]{"/view/jsp/select.ftl","/view/jsp/update.ftl","/view/jsp/insert.ftl","/view/jsp/view.ftl"};
//			String [] modules = new String[]{"/view/jsp/select.ftl","/view/jsp/update.ftl","/view/jsp/view.ftl"};
			Configuration cfg = new Configuration();
			Template temp = null;
			HashMap<String, Object> value = null;
			for(String module:modules){
				temp = cfg.getTemplate(module);
				sw = new StringWriter();
				value = new HashMap<String, Object>();
				value.put("bean",bean);
				temp.process(value, sw);
				FuncFile.insertFile(super.getOutDir()+"\\"+this.getViewRoot()+"\\"+super.packageToPath(super.getPackageName())+"\\"+module.substring(module.lastIndexOf("/"), module.lastIndexOf(".ftl"))+".jsp", sw.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				sw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
