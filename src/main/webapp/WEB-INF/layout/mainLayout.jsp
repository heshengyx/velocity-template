<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <title><sitemesh:write property="title" /></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    
    <!-- basic styles -->
	<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${ctx}/css/font-awesome.min.css" />

	<!--[if IE 7]>
	  <link rel="stylesheet" href="${ctx}/css/font-awesome-ie7.min.css" />
	<![endif]-->
	<sitemesh:write property="head" />
	<!-- ace styles -->
	<link rel="stylesheet" href="${ctx}/css/ace.min.css" />
	<%-- <link rel="stylesheet" href="${ctx}/css/ace-rtl.min.css" />
	<link rel="stylesheet" href="${ctx}/css/ace-skins.min.css" /> --%>

	<!--[if lte IE 8]>
	  <link rel="stylesheet" href="${ctx}/css/ace-ie.min.css" />
	<![endif]-->

	<!-- inline styles related to this page -->
	<!-- ace settings handler -->
	<script src="${ctx}/js/ace-extra.min.js"></script>

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="${ctx}/js/html5shiv.js"></script>
	<script src="${ctx}/js/respond.min.js"></script>
	<![endif]-->
  </head>
  
  <body>
		<div class="navbar navbar-default" id="navbar">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small><i class="icon-leaf"></i>后台管理平台</small>
					</a>
				</div>

				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<li class="grey">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-tasks"></i>
								<span class="badge badge-grey">4</span>
							</a>
						</li>

						<li class="purple">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-bell-alt icon-animated-bell"></i>
								<span class="badge badge-important">8</span>
							</a>
						</li>

						<li class="green">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-envelope icon-animated-vertical"></i>
								<span class="badge badge-success">5</span>
							</a>
						</li>

						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="${ctx}/avatars/user.jpg" alt="Jason's Photo" />
								<span class="user-info"><small>欢迎管理员,</small>Jason</span>
								<i class="icon-caret-down"></i>
							</a>

							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="#"><i class="icon-off"></i>退出</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header -->
			</div><!-- /.container -->
		</div>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<div class="sidebar" id="sidebar">
					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
					</script>

					<div class="sidebar-shortcuts" id="sidebar-shortcuts">
						<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
							<button class="btn btn-success"><i class="icon-signal"></i></button>
							<button class="btn btn-info"><i class="icon-pencil"></i></button>
							<button class="btn btn-warning"><i class="icon-group"></i></button>
							<button class="btn btn-danger"><i class="icon-cogs"></i></button>
						</div>

						<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
							<span class="btn btn-success"></span>
							<span class="btn btn-info"></span>
							<span class="btn btn-warning"></span>
							<span class="btn btn-danger"></span>
						</div>
					</div>

					<ul class="nav nav-list">
						<li class="active">
							<a href="${ctx}/manage">
								<i class="icon-dashboard"></i>
								<span class="menu-text"> 首页 </span>
							</a>
						</li>
						<li>
							<a href="${ctx}/manage/clazz">
								<i class="icon-file-alt"></i>
								<span class="menu-text"> 类文件管理 </span>
							</a>
						</li>
					</ul>

					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>

					<script type="text/javascript">
						//try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
				</div>

				<sitemesh:write property="body" />

			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->
		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${ctx}/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>
		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='${ctx}/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${ctx}/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${ctx}/js/bootstrap.min.js"></script>
		<script src="${ctx}/js/typeahead-bs2.min.js"></script>

		<!-- page specific plugin scripts -->

		<!-- ace scripts -->
		<script src="${ctx}/js/ace-elements.min.js"></script>
		<script src="${ctx}/js/ace.min.js"></script>

		<sitemesh:write property="jscript" />
	</body>
</html>