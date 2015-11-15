<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>类属性管理</title>
		<link rel="stylesheet" href="${ctx}/css/ui-dialog.css" />
	</head>

	<body>
		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
				</script>

				<ul class="breadcrumb">
					<li>
						<i class="icon-home home-icon"></i>
						<a href="${ctx}/manage">首页</a>
					</li>
					<li class="active">类属性管理</li>
				</ul>
			</div>

			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label class="col-sm-1 control-label no-padding-right" for="buildingNameQuery">名称</label>
								<div class="col-sm-11">
									<input type="text" id="buildingNameQuery" placeholder="名称" class="col-xs-10 col-sm-5" />
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-md-offset-1 col-md-11">
									<button class="btn btn-sm btn-info" type="button" id="btn-search"><i class="icon-zoom-in bigger-110"></i>查 询</button>
									&nbsp; &nbsp; &nbsp;
									<button class="btn btn-sm" type="reset"><i class="icon-undo bigger-110"></i>重 置</button>
									&nbsp; &nbsp; &nbsp;
									<button class="btn btn-sm btn-info" type="button" id="btn-add"><i class="icon-plus bigger-110"></i>新 增</button>
								</div>
							</div>
						</form>
						
						<table id="dataGridTable"></table>
						<div id="dataGridPager"></div>
						
						<!-- PAGE CONTENT ENDS -->
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.page-content -->
		</div><!-- /.main-content -->
		<jscript>
		<script src="${ctx}/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="${ctx}/js/jqGrid/jquery.jqGrid.min.js"></script>
		<script src="${ctx}/js/jqGrid/i18n/grid.locale-cn.js"></script>
		<script src="${ctx}/js/format-util.js"></script>
		<script src="${ctx}/js/dialog-min.js"></script>
		<script src="${ctx}/js/dialog-util.js"></script>
		<script type="text/javascript">	
		$(document).ready(function() {
			$("#dataGridTable").jqGrid({
				url: "${ctx}/manage/attribute/query",
				datatype: "json",
				colModel: [
					{label:'id', name:'id', key:true, index:'id', hidden:true},
					{label:'创建时间', name:'createTime', index:'createTime', width:100, formatter:to_date_hms},
					{label:'操作', name:'opts', index:'opts', width:50, align:'center', formatter: function(cellval, options, row) {
						var content = "";
						content += "<div class=\"visible-md visible-lg hidden-sm hidden-xs action-buttons\">";
						content += "<a class=\"blue\" href=\"#\"><i class=\"icon-list-alt bigger-130\"></i></a>";
						content += "<a class=\"green\" href=\"javascript:void(0);\" onclick=\"doModify('" + row.id + "')\"><i class=\"icon-pencil bigger-130\"></i></a>";
						content += "<a class=\"red\" href=\"javascript:void(0);\" onclick=\"doTrash('" + row.id + "');\"><i class=\"icon-trash bigger-130\"></i></a>";
						content += "</div>";
						return content;
					}}
				],
				rowNum: 30,
			   	rowList: [10, 20, 30],
			   	rownumbers: true,
			   	pager: "#dataGridPager",
			   	jsonReader : {
					root : 'data',
					page : 'index',
					total : 'total',
					records : 'totalRecord',
					repeatitems : false
				},
			   	viewrecords: true,
			   	//multiselect: true,
			   	//altRows: true,
			   	//multiboxonly: true,
			   	loadComplete : function() {
					/* var table = this;
					setTimeout(function(){
						styleCheckbox(table);
						updateActionIcons(table);
						updatePagerIcons(table);
						enableTooltips(table);
					}, 0); */
				},
				caption: "类属性列表",
				autowidth: true
			});
			
			$('#btn-search').click(function() {
				doSearch();
			});
			$("#btn-add").click(function() {
				var url = "${ctx}/manage/attribute/add?random=" + Math.random();
				var options = {
					title: '新增',
					width: 500
				};
				showDialog(url, options);
			});
		});
		function styleCheckbox(table) {
		/**
			$(table).find('input:checkbox').addClass('ace')
			.wrap('<label />')
			.after('<span class="lbl align-top" />')
	
	
			$('.ui-jqgrid-labels th[id*="_cb"]:first-child')
			.find('input.cbox[type=checkbox]').addClass('ace')
			.wrap('<label />').after('<span class="lbl align-top" />');
		*/
		}
		function updateActionIcons(table) {
			/**
			var replacement = 
			{
				'ui-icon-pencil' : 'icon-pencil blue',
				'ui-icon-trash' : 'icon-trash red',
				'ui-icon-disk' : 'icon-ok green',
				'ui-icon-cancel' : 'icon-remove red'
			};
			$(table).find('.ui-pg-div span.ui-icon').each(function(){
				var icon = $(this);
				var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
				if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
			})
			*/
		}
		function updatePagerIcons(table) {
			var replacement = 
			{
				'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
				'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
				'ui-icon-seek-next' : 'icon-angle-right bigger-140',
				'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
			};
			$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
				var icon = $(this);
				var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
				
				if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
			})
		}
		function enableTooltips(table) {
			$('.navtable .ui-pg-button').tooltip({container:'body'});
			$(table).find('.ui-pg-div').tooltip({container:'body'});
		}
		function doSearch() {
			var page = $("#dataGridTable").jqGrid("getGridParam", "page");
			$("#dataGridTable").clearGridData();
			$("#dataGridTable").jqGrid("setGridParam", {
				url : "${ctx}/manage/attribute/query?random="+ Math.random(),
				page : page,
				postData : {
					//buildingName : $.trim($("#buildingName").val())
				},
				datatype: "json"
			}).trigger("reloadGrid");
		}
		function doModify(id) {
			var url = "${ctx}/manage/attribute/edit?id=" + id + "&random=" + Math.random();
			var options = {
				title: '编辑',
				width: 500
			};
			showDialog(url, options);
		}
		function doTrash(id) {
			dialog({
			    title: '消息',
			    width: 200,
			    content: '确定要删除吗?',
			    okValue: '确定',
			    ok: function () {
			        this.title('删除中…');
			        var url = "${ctx}/manage/attribute/delete?random="+ Math.random();
					var params = {
						id: id
					};
					$.post(url, params, function(result) {
						if ("500" == result.code) {
			  				dialog({
			  				    title: '消息',
			  				    width: 200,
			  				    content: result.message,
			  				    okValue: '确定',
			  				    ok: true,
			  				    cancel: false
			  				}).showModal();
			  			} else {
			  				dialog({
			  				    title: '消息',
			  				    width: 200,
			  				    content: '删除成功',
			  				  	okValue: '确定',
			  			    	ok: function () {
			  			    		doSearch();			  			    	
			  			    	},
			  			    	cancel: false
			  				}).showModal();
			  			}
					}, "json");
			        //return false;
			    },
			    cancelValue: '取消',
			    cancel: true
			}).showModal();
		}
		</script>
		</jscript>
	</body>
</html>