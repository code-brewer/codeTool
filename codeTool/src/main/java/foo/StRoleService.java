package foo;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import foo.StRole;

/**
 * 
 * @date 2013-12-16 03:48:17
 */
public interface StRoleService {


	/**
	 * 新增
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public StRole insert(StRole object);

	/**
	 * 修改
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public StRole update(StRole object);

	/**
	 * 查看
	 * @param pkid
	 * @return
	 * @throws Exception
	 */
	public StRole view(String pkid);

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
	public void export(StRole stUser, HttpServletResponse response);
}
