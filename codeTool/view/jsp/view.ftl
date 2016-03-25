<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@ include file="/inc/common.jsp"%>
<style type="text/css">
</style>
</head>
<body style="height:100%">
	<table style="width: 98%;" class="table" >
	<#assign isEnd = true>
	<#list bean.attrList as obj>
		<#if obj_index%2 == 0>
		<#assign isEnd = false>
		<tr>
			<th align="right" width="15%" nowrap>${obj.attrComments}： </th>
			<td><span type="text" class="easyui-validatebox">${r'${'} ${bean.beanClassName?uncap_first}.${obj.attrName} ${r'}'}</span></td>
		<#else>
			<th align="right" width="15%" nowrap>${obj.attrComments}： </th>
			<td><span type="text" class="easyui-validatebox">$${r'${'} ${bean.beanClassName?uncap_first}.${obj.attrName} ${r'}'}</span></td>
		</tr>
		<#assign isEnd = true>
		</#if>
	</#list>
	<#if isEnd==false>
		</tr>
	</#if>
	</table>
</body>
</html>