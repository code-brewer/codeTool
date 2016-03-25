<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@ include file="/inc/common.jsp"%>
<style type="text/css">
</style>
<script type="text/javascript">
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			parent.$.messager.progress({
				text :  '亲，我正在努力喔....'
			});
			var url = '${r'${webRoot}'}/${bean.nameSpace}/${bean.controllerClassName?uncap_first}/update.do';
			$.post(url, $("#frm").form("getData"), function(result) {
				parent.$.messager.progress('close');
				if (result.success) {
					$grid.datagrid('reload');
					$dialog.dialog('destroy');
					$pjq.messager.show( {
						title : '操作提示',
						msg : '操作成功。',
						timeout : 2000,
						showType : 'slide',
						position:'bottomRight'
					});
				} else {
					$pjq.messager.alert('提示', result.msg, 'error');
				}
			}, 'json');
			
		}
	};
</script>
</head>
<body style="height:100%">
	<form method="post" id="frm" >
	<input type="hidden" class="easyui-validatebox" readonly="readonly" name="${bean.pk}" value="${r'${'} ${bean.beanClassName?uncap_first}.${bean.pk} ${r'}'}" />
		<table style="width: 98%;" class="table" >
		<#assign isEnd = true>
		<#list bean.attrList as obj>
			<#if obj_index%2 == 0>
			<#assign isEnd = false>
			<tr>
				<th align="right" width="15%" nowrap>${obj.attrComments}： </th>
				<td><input type="text" name="${obj.attrName}" class="easyui-validatebox" value="${r'${'} ${bean.beanClassName?uncap_first}.${obj.attrName} ${r'}'}"/></td>
			<#else>
				<th align="right" width="15%" nowrap>${obj.attrComments}：</th>
				<td><input type="text" name="${obj.attrName}" class="easyui-validatebox" value="${r'${'} ${bean.beanClassName?uncap_first}.${obj.attrName} ${r'}'}"/></td>
			</tr>
			<#assign isEnd = true>
			</#if>
		</#list>
		<#if isEnd==false>
			</tr>
		</#if>
		</table>
	</form>
</body>
</html>