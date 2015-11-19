<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp"%>
<form class="form-horizontal" role="form">
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="clazzNameAdd">类名</label>
		<div class="col-sm-10">
			<input type="text" id="clazzNameAdd" placeholder="类名" class="col-xs-10 col-sm-10" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="tableNameAdd">表名</label>
		<div class="col-sm-10">
			<input type="text" id="tableNameAdd" placeholder="表名" class="col-xs-10 col-sm-10" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right" for="titleAdd">标题</label>
		<div class="col-sm-10">
			<input type="text" id="titleAdd" placeholder="标题" class="col-xs-10 col-sm-10" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right">属性</label>
		<div class="col-sm-10">
			<button class="btn btn-sm btn-info" type="button" id="btn-add-attribute"><i class="icon-plus bigger-110"></i></button>
			<table id="attributeGridTable"></table>
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-md-offset-2 col-md-10">
			<button class="btn btn-sm btn-info" type="button" id="btn-save"><i class="icon-save bigger-110"></i>保 存</button>
		</div>
	</div>
</form>
<table id="gridTable">
  <tr role="row" class="ui-widget-content jqgrow ui-row-ltr">
    <td role="gridcell"><input type="text" name="attributeName" class="attributeName" style="width:80px;" placeholder="属性名称" /></td>
    <td role="gridcell"><input type="text" name="attributeColumn" class="attributeColumn" style="width:80px;" placeholder="属性字段" /></td>
    <td role="gridcell"><input type="text" name="attributeTitle" class="attributeTitle" style="width:80px;" placeholder="属性标题" /></td>
    <td role="gridcell">
    <select name="attributeType" class="attributeType">
    	<option value="String">String</option>
    	<option value="Date">Date</option>
    	<option value="int">int</option>
    </select></td>
    <td role="gridcell"><input role="checkbox" name="attributeSearch" class="attributeSearch" type="checkbox" value="1"></td>
    <td role="gridcell"><input role="checkbox" name="attributeEdit" class="attributeEdit" type="checkbox" value="1"></td>
  </tr>
</table>
<script type="text/javascript">	
$(document).ready(function() {
	$("#gridTable").hide();
	$("#attributeGridTable").jqGrid({
		datatype: "json",
		colModel: [
			{label:'属性名称', name:'attributeName', index:'attributeName', sortable:false, width:150},
			{label:'属性字段', name:'attributeColumn', index:'attributeColumn', sortable:false, width:120},
			{label:'属性标题', name:'attributeTitle', index:'attributeTitle', sortable:false, width:120},
			{label:'属性类型', name:'attributeType', index:'attributeType', sortable:false, width:100},
			{label:'是否查询', name:'attributeSearch', index:'attributeSearch', sortable:false, width:80},
			{label:'是否编辑', name:'attributeEdit', index:'attributeEdit', sortable:false, width:80}
		],
	   	viewrecords: true,
	   	//multiselect: true,
	   	loadComplete : function() {
			/* var table = this;
			setTimeout(function(){
				styleCheckbox(table);
				updateActionIcons(table);
				updatePagerIcons(table);
				enableTooltips(table);
			}, 0); */
		}
		//autowidth: true
		/* gridComplete : function() {
	   		var html = $("#gridTable tbody").html();
	   		if ("" != html) {
	   			$("#attributeGridTable tbody").append(html);
	   			$("#gridTable tbody").html("");
	   		}
	    } */
	});
	$("#btn-add-attribute").click(function() {
		var html = $("#gridTable tbody").html();
		$("#attributeGridTable tbody").append(html);
	});
	$("#btn-save").click(function() {
		var attributeNames = getValues("#attributeGridTable .attributeName");
		var attributeColumns = getValues("#attributeGridTable .attributeColumn");
		var attributeTitles = getValues("#attributeGridTable .attributeTitle");
		var attributeTypes = getValues("#attributeGridTable .attributeType");
		var attributeSearchs = getBoxValues("#attributeGridTable .attributeSearch");
		var attributeEdits = getBoxValues("#attributeGridTable .attributeEdit");
		if (attributeNames.length > 0) {
			var url = "${ctx}/manage/clazz/save?random="+ Math.random();
			var params = {
				title: $("#titleAdd").val(),
				clazzName: $("#clazzNameAdd").val(),
				tableName: $("#tableNameAdd").val(),
				attributeName: attributeNames,
				attributeColumn: attributeColumns,
				attributeTitle: attributeTitles,
				attributeType: attributeTypes,
				attributeSearch: attributeSearchs,
				attributeEdit: attributeEdits
			};
			$.ajax({  
	            type: 'post',  
	            dataType: "json",
	            url: url,  
	            data: params,  
	            traditional: true, 
	            success: function(result){  
	            	dialog({
	    			    title: '消息',
	    			    width: 200,
	    			    content: result.message,
	    			    okValue: '确定',
	    			    ok: function () {
	    			    	if (result.code == "500") {
	    			    		return true;	
	    			    	} else {
	    			    		_myDialog.close().remove();
	    		                doSearch();	
	    			    	}
	    		    	},
	    			    cancel: false
	    			}).showModal();
	            }  
	        });
		} else {
			dialog({
			    title: '消息',
			    width: 200,
			    content: '请至少添加一个属性',
			  	okValue: '确定',
		    	ok: true,
		    	cancel: false
			}).showModal();
		}
	});
});
function getValues(className) {
	var values = [];
	$(className).each(function(){
		values.push($(this).val());
	});
	return values;
}
function getBoxValues(className) {
	var values = [];
	$(className).each(function(){
		if ($(this).is(':checked')) {
			values.push($(this).val());
		} else {
			values.push("0");
		}
	});
	return values;
}

</script>