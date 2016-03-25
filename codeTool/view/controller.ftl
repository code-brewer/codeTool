package ${bean.packageName};

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.common.Constant;
import com.core.common.OperateConstant.ModuleCode;
import com.core.common.base.BaseController;
import com.core.common.page.PageInfo;
import com.core.common.page.PageList;
import com.core.common.util.BtnDesUtil;
import com.core.common.util.ContextHolderUtils;
import com.core.common.vo.PrivaligeAnnotation;
import com.core.common.vo.ReturnData;
import com.core.log.vo.LogAnnotation;
import com.core.system.model.StUser;

import ${bean.beanPackageName}.${bean.beanClassName};
import ${bean.servicePackageName}.${bean.serviceClassName};
import ${bean.serviceImplPackageName}.${bean.serviceImplClassName};

/**
 * 
 * @author ${bean.author}
 * @date ${bean.updateDate?string("yyyy-MM-dd HH:mm:ss")}  
 */

@Controller
@RequestMapping("/${bean.nameSpace}/${bean.className?uncap_first}")
public class ${bean.className} extends BaseController {

	private static final Logger LOGGER = Logger.getLogger(${bean.className}.class);

	@Resource
	private ${bean.serviceClassName} ${bean.serviceClassName?uncap_first};

	/** 自定义权限编码 为:000001 */
	private static final String CODE = "${bean.permissionCode}";

	/**
	 * 预查询
	 * @return
	 */
	@PrivaligeAnnotation(code = CODE + SELECT, des = "预查询列表")
	@RequestMapping("/preSelect")
	public String preSelect(String btn, HttpServletRequest request) {
		try {
			if (!StringUtils.isBlank(btn)) {
				String[] btns = StringUtils.split(BtnDesUtil.decrypt(btn),
						Constant.ID_SPLIT);
				StUser stUser = ContextHolderUtils.getSessionUser();
				if (btns.length > 0
						&& !stUser.getPkid().equals(btns[btns.length - 1])) {
					LOGGER.info("非法请求，当前登录用户为:" + stUser.getPkid() + ",权限URL为："
							+ ArrayUtils.toString(btns));
					request.setAttribute("msg", "非法请求");
					return NO_AUTH_PAGE;
				}
				for (String strBtn : btns) {
					request.setAttribute(strBtn, strBtn);
				}
			}
		} catch (Exception e) {
			LOGGER.error("预查询异常。", e);
			return NO_AUTH_PAGE;
		}
		return "${bean.viewRoot}/${bean.nameSpace}/select";
	}

	@SuppressWarnings("unchecked")
	@PrivaligeAnnotation(code = CODE + SELECT, des = "查询列表")
	@RequestMapping("/select")
	@ResponseBody
	public Object select(${bean.beanClassName} ${bean.beanClassName?uncap_first}, HttpServletResponse response) {
		PageInfo pageInfo = super.wrapPageInfo(${bean.beanClassName?uncap_first}, null);
		Map<String, Object> map = null;
		try {
			map = new BeanUtilsBean().describe(${bean.beanClassName?uncap_first});
		} catch (Exception e) {
			LOGGER.error("根据 ${bean.beanClassName} 查询失败,", e);
		}
		PageList pageList = ${bean.serviceClassName?uncap_first}.selectPage(pageInfo, map);
		return pageList;
	}

	/** 根据主键查询详情*/
	@PrivaligeAnnotation(code = CODE + VIEW, des = "查询详情")
	@RequestMapping("/view")
	public String view(String ${bean.pk}, HttpServletRequest request) {
		${bean.beanClassName} ${bean.beanClassName?uncap_first} = ${bean.serviceClassName?uncap_first}.view(${bean.pk});
		request.setAttribute("${bean.beanClassName?uncap_first}", ${bean.beanClassName?uncap_first});
		return "${bean.viewRoot}/${bean.nameSpace}/view";
	}
	
	/** 增加*/
	@PrivaligeAnnotation(code = CODE + INSERT, des = "预增加")
	@RequestMapping("/preInsert")
	public String preInsert(String ${bean.pk}, HttpServletRequest request) {
		return "${bean.viewRoot}/${bean.nameSpace}/insert";
	}

	@PrivaligeAnnotation(code = CODE + INSERT, des = "增加", operateType = Constant.OpType.INSERT)
	@RequestMapping("/insert")
	@ResponseBody
	@LogAnnotation(operateDescribe = "增加角色", operateType = Constant.LogType.INSERT)
	public ReturnData insert(${bean.beanClassName} ${bean.beanClassName?uncap_first}, HttpServletRequest request) {
		${bean.serviceClassName?uncap_first}.insert(${bean.beanClassName?uncap_first});
		ReturnData returnData = new ReturnData();
		returnData.setObj(${bean.beanClassName?uncap_first});
		return returnData;
	}
	
	/** 删除*/
	@PrivaligeAnnotation(code = CODE + DEL, des = "删除", operateType = Constant.OpType.DELETE)
	@RequestMapping("/del")
	@ResponseBody
	@LogAnnotation(operateDescribe = "删除", operateType = Constant.LogType.DELETE)
	public ReturnData del(String ${bean.pk}, HttpServletRequest request) {
		${bean.serviceClassName?uncap_first}.delete(StringUtils.split(${bean.pk}, Constant.ID_SPLIT));
		ReturnData returnData = new ReturnData();
		return returnData;
	}
	
	/** 修改*/
	@PrivaligeAnnotation(code = CODE + UPDATE, des = "预修改")
	@RequestMapping("/preUpdate")
	public String preUpdate(String ${bean.pk}, HttpServletRequest request) {
		${bean.beanClassName} ${bean.beanClassName?uncap_first} = ${bean.serviceClassName?uncap_first}.view(${bean.pk});
		request.setAttribute("${bean.beanClassName?uncap_first}", ${bean.beanClassName?uncap_first});
		return "${bean.viewRoot}/${bean.nameSpace}/update";
	}

	@PrivaligeAnnotation(code = CODE + UPDATE, des = "修改", operateType = Constant.OpType.UPDATE)
	@RequestMapping("/update")
	@ResponseBody
	@LogAnnotation(operateDescribe = "修改", operateType = Constant.LogType.UPDATE)
	public ReturnData update(${bean.beanClassName} ${bean.beanClassName?uncap_first}, HttpServletRequest request) {
		${bean.serviceClassName?uncap_first}.update(${bean.beanClassName?uncap_first});
		ReturnData returnData = new ReturnData();
		returnData.setObj(${bean.beanClassName?uncap_first});
		return returnData;
	}

	
	
}
