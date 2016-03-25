package org.huangt.temp;

import java.util.List;

import org.huangt.core.Field;
import org.huangt.core.Factory.JavaBeanFactory;
import org.huangt.core.Factory.JavaControllerFactory;
import org.huangt.core.Factory.JavaDbConfigFactory;
import org.huangt.core.Factory.JavaServiceFactory;
import org.huangt.core.Factory.JavaServiceImplFactory;
import org.huangt.core.Factory.JspFactory;
import org.huangt.core.bean.JavaBean;
import org.huangt.core.bean.JavaControllerBean;
import org.huangt.core.bean.JavaDbConfigBean;
import org.huangt.core.bean.JavaServiceBean;
import org.huangt.core.bean.JavaServiceImplBean;
import org.huangt.core.bean.JspBean;
import org.huangt.tool.FuncStatic;

/** 
 * 类说明  ''
 * @author : huangtao
 * @version 创建时间：2016-3-9 下午9:16:49 
 */
public class TestFtl {
	public static void main(String[] args) throws Exception {
		
		String outDir = "C:\\Users\\Administrator\\Desktop\\";
		CodeToolDbConnect db = CodeToolDbConnect.instance();
		String tableName = "APP_TEACHER";//ST_ROLE
		List<Field> fields = db.allFields(tableName);
		String pk = db.getKeyFieldName(tableName);
		
		JavaBeanFactory bf = new JavaBeanFactory("com.core.school.model", tableName, fields, pk, "/view/javaBean.ftl", outDir);
		bf.createBean();
		
		JavaDbConfigFactory df = new JavaDbConfigFactory("com.core.school.dbconfig", tableName, fields, pk, "/view/dbConfig.ftl", outDir);
		df.createBean();
		
		JavaServiceFactory sf = new JavaServiceFactory("com.core.school.service", tableName, fields, pk, "/view/service.ftl", outDir);
		sf.createBean();
		
		JavaServiceImplFactory sif = new JavaServiceImplFactory("com.core.school.service.impl",tableName, fields, pk, "/view/serviceImpl.ftl", outDir);
		sif.createBean();
		
		JavaControllerFactory cf = new JavaControllerFactory("com.core.school.controller",tableName, fields, pk, "/view/controller.ftl", outDir);
		cf.setViewRoot("app");
		cf.setPermissionCode("000020");
		cf.createBean();
		
		JspFactory jspf  = new JspFactory("school", tableName, fields, pk, "/view/jsp", outDir);
		jspf.setViewRoot(cf.getViewRoot());
		jspf.setPermissionCode(cf.getPermissionCode());
		jspf.createBean();
		
		
		JavaBean javaBean = (JavaBean) bf.createBean();
		bf.createFile(javaBean);
		
		JavaDbConfigBean dbConfigBean = (JavaDbConfigBean) df.createBean();
		dbConfigBean.setBeanClassName(javaBean.getClassName());
		dbConfigBean.setBeanPackageName(javaBean.getPackageName());
		df.createFile(dbConfigBean);
		
		JavaServiceBean serviceBean = (JavaServiceBean) sf.createBean();
		serviceBean.setBeanClassName(javaBean.getClassName());
		serviceBean.setBeanPackageName(javaBean.getPackageName());
		sf.createFile(serviceBean);
		
		JavaServiceImplBean serviceImplBean = (JavaServiceImplBean) sif.createBean();
		serviceImplBean.setBeanClassName(javaBean.getClassName());
		serviceImplBean.setBeanPackageName(javaBean.getPackageName());
		serviceImplBean.setInterfaceClassName(serviceBean.getClassName());
		serviceImplBean.setInterfacePackageName(serviceBean.getPackageName());
		sif.createFile(serviceImplBean);
		
		JavaControllerBean controllerBean = (JavaControllerBean) cf.createBean();
		controllerBean.setBeanClassName(javaBean.getClassName());
		controllerBean.setBeanPackageName(javaBean.getPackageName());
		controllerBean.setServiceClassName(serviceBean.getClassName());
		controllerBean.setServicePackageName(serviceBean.getPackageName());
		controllerBean.setServiceImplClassName(serviceImplBean.getClassName());
		controllerBean.setServiceImplPackageName(serviceImplBean.getPackageName());
		cf.createFile(controllerBean);
		
		JspBean jspBean = (JspBean) jspf.createBean();
		jspBean.setBeanClassName(javaBean.getClassName());
		jspBean.setControllerClassName(controllerBean.getClassName());
		jspf.createFile(jspBean);
		
		System.out.println(tableName+"  入口:"+jspBean.getNameSpace()+"/"+FuncStatic.convertLowerCaseFirst(controllerBean.getClassName())+"/preSelect.do");
	}

}
