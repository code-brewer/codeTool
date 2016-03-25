package ${bean.packageName};

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.core.common.page.PageInfo;
import com.core.common.page.PageList;
import ${bean.beanPackageName}.${bean.beanClassName};

/**
 * 
 * @author ${bean.author}
 * @date ${bean.updateDate?string("yyyy-MM-dd HH:mm:ss")}  
 */
public interface ${bean.className} {

	/**
	 * 分页查询
	 * @param pageInfo
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public PageList selectPage(PageInfo pageInfo, Map<String, Object> params);

	/**
	 * 新增
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public ${bean.beanClassName} insert(${bean.beanClassName} object);

	/**
	 * 修改
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public ${bean.beanClassName} update(${bean.beanClassName} object);

	/**
	 * 查看
	 * @param ${bean.pk}
	 * @return
	 * @throws Exception
	 */
	public ${bean.beanClassName} view(String ${bean.pk});

	/**
	 * 批量删除
	 * @param pk_array
	 * @return
	 * @throws Exception
	 */
	public int delete(String[] pk_array);

	/**
	 * 
	 * 导出
	 */
	public void export(${bean.beanClassName} stUser, HttpServletResponse response);
}
