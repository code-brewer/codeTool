<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE sqlMap
    PUBLIC "-//ibatis.apache.org//DTD SQL Map 2.0//EN"
    "http://ibatis.apache.org/dtd/sql-map-2.dtd">

<sqlMap namespace="${bean.nameSpace}">

<typeAlias alias="${bean.className}" type="${bean.beanPackageName}.${bean.beanClassName}"/>

    <resultMap id="${bean.className}Map" class="${bean.className}">
    	<#list bean.attrList as obj>
    	 	<result property="${obj.attrName}" column="${obj.columnName}"/>
    	</#list>
    </resultMap>

    <sql id="allColumns">
    	<#list bean.attrList as obj>
    		<#if obj_index == (bean.attrList?size-1)>
    			${obj.columnName}
    		</#if>
    		<#if obj_index != (bean.attrList?size-1)>
    			${obj.columnName},
    		</#if>
    	</#list>
    </sql>

    <sql id="dynamicWhere">
    	<#list bean.attrList as obj>
	    	<isNotEmpty prepend="AND" property="${obj.attrName}">
	         ${obj.columnName} = #${obj.attrName}#
	         </isNotEmpty>
    	</#list>
    </sql>

    <!-- select 普通分页查询 -->
    <select id="select" parameterClass="java.util.HashMap" resultMap="${bean.className}Map">
      SELECT <include refid="allColumns" /> 
      FROM ${bean.tableName} 
      <dynamic prepend="WHERE">
         <include refid="dynamicWhere"/>
      </dynamic>
      <dynamic prepend="ORDER BY">
         <isNotEmpty property="fieldSort"> $fieldSort$ </isNotEmpty>
      </dynamic>
    </select>

    <!-- view  普通根据主键查询-->
    <select id="view" parameterClass="java.lang.String"  resultMap="${bean.className}Map">
      SELECT <include refid="allColumns" />
      	<#list bean.attrList as obj>
	    	<#if obj.isPk == 'y'>
	    	 FROM ${bean.tableName} WHERE ${obj.columnName} = #${obj.attrName}#
	    	 <#break>
	    	</#if>
    	</#list>
    </select>

    <!-- insert 普通增加数据-->
    <insert id="insert" parameterClass="${bean.className}">
      INSERT INTO ${bean.tableName} (<include refid="allColumns" />) 
      VALUES(
      	<#list bean.attrList as obj>
	    	<#if obj_index == (bean.attrList?size-1)>
    			#${obj.attrName}#
    		</#if>
    		<#if obj_index != (bean.attrList?size-1)>
    			#${obj.attrName}#,
    		</#if>
    	</#list>
        )
    </insert>

    <!-- update  普通根据主键更新数据-->
    <update id="update" parameterClass="${bean.className}">
      UPDATE ${bean.tableName} 
      <dynamic prepend="SET">
      	<#list bean.attrList as obj>
	    	<isNotEmpty prepend="," property="${obj.attrName}">${obj.columnName} = #${obj.attrName}#</isNotEmpty>
    	</#list>
      </dynamic>
      	<#list bean.attrList as obj>
	    	<#if obj.isPk == 'y'>
	    	 	WHERE ${obj.columnName} = #${obj.attrName}#
	    	 <#break>
	    	</#if>
    	</#list>
    </update>

    <!-- delete  普通根据主键删除数据-->
    <delete id="delete" parameterClass="java.lang.String">
    	<#list bean.attrList as obj>
	    	<#if obj.isPk == 'y'>
	    		DELETE FROM ${bean.tableName} WHERE ${obj.columnName} = #${obj.attrName}#
	    	 <#break>
	    	</#if>
    	</#list>
    </delete>
 	
</sqlMap>