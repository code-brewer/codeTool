package org.huangt.temp;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.huangt.core.FuncFile;

import freemarker.template.Configuration;
import freemarker.template.Template;

/** 
 * 类说明  ''
 * @author : huangtao
 * @version 创建时间：2016-3-9 下午9:16:49 
 */
public class TestFtl {
	public static void main(String[] args) throws Exception {
		List<Object> list = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("host", "helloWorld1111");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("host", "helloWorld222222");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("host", "helloWorld3333333");
		list.add(map);
		
		
		Configuration cfg = new Configuration();
		Template temp = cfg.getTemplate("/view/demo.ftl");
		StringWriter sw = new StringWriter();
		HashMap<String, Object> value = new HashMap<String, Object>();
		value.put("qlist", list);
		temp.process(value, sw);
		FuncFile.insertFile("C://Users//dear-tao//Desktop//demo.txt", sw.toString());
		
	}

}
