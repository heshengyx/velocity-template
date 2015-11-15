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
		<div class="col-md-offset-2 col-md-10">
			<button class="btn btn-sm btn-info" type="button" id="btn-save"><i class="icon-save bigger-110"></i>保 存</button>
		</div>
	</div>
</form>
<script type="text/javascript">	
$(document).ready(function() {
	$("#btn-save").click(function() {
		var url = "${ctx}/manage/attribute/save?random="+ Math.random();
		var params = {
			title: $("#titleAdd").val(),
			clazzName: $("#clazzNameAdd").val(),
			tableName: $("#tableNameAdd").val()
		};
		$.post(url, params, function(result) {
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
		}, "json");
	});
});
</script>