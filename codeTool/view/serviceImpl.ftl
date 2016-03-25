package ${bean.packageName};

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.core.common.dao.SqlDao;
import com.core.common.page.PageInfo;
import com.core.common.page.PageList;
import com.core.common.page.PageService;
import com.core.common.util.SerialNo;

import ${bean.beanPackageName}.${bean.beanClassName};
import ${bean.interfacePackageName}.${bean.interfaceClassName};

/**
 * 
 * @author ${bean.author}
 * @date ${bean.updateDate?string("yyyy-MM-dd HH:mm:ss")}  
 */
@Service
public class ${bean.className} implements ${bean.interfaceClassName} {

	private final Logger log = Logger.getLogger(${bean.className}.class);

	@Resource
	private SqlDao sqlDao;

	@Resource
	private PageService pageService;


	/**
	 * 分页查询
	 */
	@Override
	public PageList selectPage(PageInfo pageInfo, Map<String, Object> params) {
		log.info("执行${bean.className}.selectPage： pageInfo=>" + pageInfo +",params=>" + params);
		pageService.setRecordSql("${bean.nameSpace}.select");
		return pageService.pageQuery(pageInfo, params);
	}
	
	/**
	 * 新增
	 */
	@Override
	public ${bean.beanClassName} insert(${bean.beanClassName} object) {
		log.info("${bean.className}.insert： object=>" + object);
		object.set${bean.pk?cap_first}(SerialNo.getUNID());
		sqlDao.create("${bean.nameSpace}.insert", object);
		return object;
	}
	
	/**
	 * 修改
	 */
	@Override
	public ${bean.beanClassName} update(${bean.beanClassName} object) {
		log.info("执行${bean.className}.update： object=>" + object);
		sqlDao.update("${bean.nameSpace}.update", object);
		return object;
	}
	
	/**
	 * 查看
	 */
	@Override
	public ${bean.beanClassName} view(String ${bean.pk}) {
		log.info("执行${bean.className}.view： ${bean.pk}=>" + ${bean.pk});
		return (${bean.beanClassName}) sqlDao.view("${bean.nameSpace}.view", ${bean.pk});
	}

	/**
	 * 批量删除
	 */
	@Override
	public int delete(String[] ${bean.pk}_array) {
		log.info("执行${bean.className}.delete： ${bean.pk}_array=>" + ${bean.pk}_array);
		for (String ${bean.pk} : ${bean.pk}_array) {
			sqlDao.delete("${bean.nameSpace}.delete", ${bean.pk});
		}
		return ${bean.pk}_array.length;
	}
	
	/**
	 * 
	 * 导出
	 */
	public void export(${bean.beanClassName} stUser, HttpServletResponse response) {
	
	}
	

	

	
}
