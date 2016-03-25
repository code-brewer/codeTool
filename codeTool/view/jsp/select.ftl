<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>医院信息管理</title>
<%@ include file="/inc/common.jsp"%>
<script type="text/javascript">
	var grid;
	$(function() {
			grid = $('#dg').datagrid({
			fit:true,//自动大小   
			fitColumns: false,
			striped: true,
			singleSelect:true,//只允许选中一行
			multiSort:true,
			url:'${r'${webRoot}'}/${bean.nameSpace}/${bean.controllerClassName?uncap_first}/select.do',
		    idField:'${bean.pk}',
		    sortName:'updateTime',
		    sortOrder:'desc',
		  	columns : [ [ {
				field : 'ck',
				checkbox:true
			},{
				field : 'easyui_action',
				title : '操作',
				formatter:function(value,row,index){
					var str = '';
					if(row.${bean.pk}){
						if('${r'${func:checkCode("'}${bean.permissionCode}04${r'")}'}' == '1'){
							str += $.string.format('<a href="javascript:void(0);" onclick="showFun(\'{0}\');">查看</a>', row.${bean.pk})+" ";
						}
						if('${r'${func:checkCode("'}${bean.permissionCode}03${r'")}'}' == '1'){
							str += $.string.format('<a href="javascript:void(0);" onclick="editFun(\'{0}\');">修改</a>', row.${bean.pk})+" ";
						}
						if('${r'${func:checkCode("'}${bean.permissionCode}02${r'")}'}' == '1'){
							str += $.string.format('<a href="javascript:void(0);" onclick="removeFun(\'{0}\');">删除</a>', row.${bean.pk})+" ";
						}
					}
				return str;
				}
			},
			<#list bean.attrList as obj> 
			{
				field : '${obj.attrName}',
				title : '${obj.attrComments}',
				width : 100
			}<#if obj_index != (bean.attrList?size-1)>,</#if>
			</#list>
			] ],
			pagination:true,
			rownumbers:true,
			showFooter:true,
			toolbar:'#tb',
			selectOnCheck:false,
			enableHeaderClickMenu: false //此属性开启表头列名称右侧那个箭头形状的鼠标左键点击菜单
		});
		
		$("#query").click(function(){
			grid.datagrid('load', $("#frm").form("getData"));  
		});
	});
	var addFun = function() {
		var dialog = jsutil.modalDialog({
			title : '添加',
			iconCls:'icon-add',
			url : '${r'${webRoot}'}/${bean.nameSpace}/${bean.controllerClassName?uncap_first}/preInsert.do',
			 width: 550,    
			 height: 300,  
			 buttons : [ {
				text : '保存',
				iconCls:'icon-save',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var editFun = function(id) {
		var dialog = jsutil.modalDialog({
			title : '编辑',
			url : '${r'${webRoot}'}/${bean.nameSpace}/${bean.controllerClassName?uncap_first}/preUpdate.do?${bean.pk}=' + id,
			 width: 550,    
			 height: 300,  
			buttons : [ {
				text : '保存',
				iconCls:'icon-save',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var showFun = function(id) {
		// 在所有窗口的最上部  
		var dialog = jsutil.modalDialog({
			title : '查看详情',
			 width: 550,    
			 height: 300,  
			url : '${r'${webRoot}'}/${bean.nameSpace}/${bean.controllerClassName?uncap_first}/view.do?${bean.pk}=' + id
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post('${r'${webRoot}'}/${bean.nameSpace}/${bean.controllerClassName?uncap_first}/del.do', {
					${bean.pk} : id
				}, function(data) {
					if (data.success) {
						$.messager.show({
							title:'操作提示',
							msg:'操作成功。',
							timeout:2000,
							showType:'slide',
							position:'bottomRight'
						});
						grid.datagrid('reload');
					} else {
						$.messager.alert('提示', result.msg, 'error');
					}
				}, 'json');
			}
		});
	};

	var removeFunS = function(){
		var rows = $('#dg').datagrid('getChecked');
		if(rows.length > 0){
			var ids = [];
			for(var i=0;i< rows.length;i++){
				ids.push(rows[i].${bean.pk});
			}
			parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
				if (r) {
					$.post('${r'${webRoot}'}/${bean.nameSpace}/${bean.controllerClassName?uncap_first}/del.do', {
						${bean.pk} : ids.join(',')
					}, function(data) {
						if (data.success) {
							$.messager.show({
								title:'操作提示',
								msg:'操作成功。',
								timeout:2000,
								showType:'slide',
								position:'bottomRight'
							});
							grid.datagrid('reload');
							grid.datagrid('clearChecked');
						} else {
							$.messager.alert('提示', result.msg, 'error');
						}
					}, 'json');
				}
			});
		} else{
			parent.$.messager.alert('警告', '您选择需要删除的记录！');
		}
	};
	
</script>
</head>
<body>
	 <div id="tb" >
			<div style="padding-top:3px;">
				<form id="frm" >
					<!-- &nbsp;&nbsp;机构代码：<input name=hospitalCode type="text" class="easyui-validatebox"/> -->
					&nbsp;&nbsp;查询条件1：<input name="hospitalName" type="text" class="easyui-validatebox"/>
					&nbsp;&nbsp;查询条件2：<input name="hospitalId" type="text" class="easyui-validatebox"/>
					<a id="query" href="#" class="easyui-linkbutton" iconCls="icon-search" plain="false">查询</a>
				</form>
			</div>
			<table>
			<tr>
				<c:if test="${r"${func:checkCode('"}${bean.permissionCode}01${r"')"} eq '1'}">
					<td><a class="easyui-linkbutton" href="javascript:void(0);" iconCls="icon-add" plain="true" data-options="" onclick="addFun();">增加</a></td>
					<td><div class="datagrid-btn-separator"></div></td>
				</c:if>
				<c:if test="${r"${func:checkCode('"}${bean.permissionCode}02${r"')"} eq '1'}">
					<td><a class="easyui-linkbutton" href="javascript:void(0);" iconCls="icon-remove" plain="true" onclick="removeFunS();">删除</a></td>
				</c:if>
			</tr>
		</table>
	</div>
	
    <table id="dg"></table>
    


</body>
</html>