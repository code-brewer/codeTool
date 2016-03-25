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
	<form method="post" id="frm" >
		<table style="width: 98%;" class="table" >
		<#assign isEnd = true>
		<#list bean.attrList as obj>
			<#if obj_index%2 == 0>
			<#assign isEnd = false>
			<tr>
				<th align="right" width="15%" nowrap>${obj.attrComments}： </th>
				<td><input type="text" name="${obj.attrName}"  class="easyui-validatebox" /></td>
			<#else>
				<th align="right" width="15%" nowrap>${obj.attrComments}：</th>
				<td><input type="text" name="${obj.attrName}"  class="easyui-validatebox" /></td>
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
<script type="text/javascript">
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			parent.$.messager.progress({
				text :  '亲，我正在努力喔....'
			});
			var url = '${r'${webRoot}'}/${bean.nameSpace}/${bean.controllerClassName?uncap_first}/insert.do';
			$.post(url, $("#frm").form("getData"), function(result) {
				parent.$.messager.progress('close');
				if (result.success) {
					$dialog.dialog('destroy');
					$grid.datagrid('reload');
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
	
	/* $(function () {

            $.extend($.fn.validatebox.defaults.rules, {
                phone: {
                    validator: function (value) {
                        return /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/i.test($.trim(value)); 
　　　　　　　　　　　　}, 
　　　　　　　　　　　　message: '联系电话格式错误.' } });


 　　　　　　　　$.extend($.fn.validatebox.defaults.rules, {     
              　　 minLength: {     
                  　　  validator: function(value, param){  
                   　　     return value.length >= param[0];     
                  　　  },     
                 　　   message: '请输入最小{0}位字符.'    
              　　  }     
           　　 });   
        })
         */
</script>