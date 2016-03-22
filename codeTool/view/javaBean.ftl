package ${targetPackage};

<#list importList as package> 
import ${package};
</#list>

public class ${targetName}{
<#list qlist as obj> 
	/**
	*${obj.attrComments}
	*/
	private ${obj.attrType} ${obj.attrName};
</#list>

<#list qlist as obj> 
	
	public void set${obj.attrMethodName}(${obj.attrType} ${obj.attrName}){
		this.${obj.attrName} = ${obj.attrName};
	}
	
	public ${obj.attrType} get${(obj.attrMethodName)}(){
		return this.${obj.attrName};
	}
</#list>

}
	