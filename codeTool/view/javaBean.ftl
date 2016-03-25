package ${bean.packageName};

<#list bean.packageList as package> 
import ${package};
</#list>

import com.core.common.base.BaseVO;

/**
 * 
 * @author ${bean.author}
 * @date ${bean.updateDate?string("yyyy-MM-dd HH:mm:ss")}  
 */
public class ${bean.className} extends BaseVO {

	// Fields
	
<#list bean.attrList as obj> 
	/** ${obj.attrComments} */
	private ${obj.attrType} ${obj.attrName};
</#list>

	// Constructors
	
	/** default constructor */
	public	${bean.className}() {
	}

	// Property accessors
	
<#list bean.attrList as obj> 
	/**
	 * 设置${obj.attrName} 属性值
	 */
	public void set${obj.attrMethodName}(${obj.attrType} ${obj.attrName}) {
		this.${obj.attrName} = ${obj.attrName};
	}
	
	/**
	 * 获取 ${obj.attrName} 属性值
	 */
	public ${obj.attrType} get${(obj.attrMethodName)}() {
		return this.${obj.attrName};
	}
	
</#list>

}
	