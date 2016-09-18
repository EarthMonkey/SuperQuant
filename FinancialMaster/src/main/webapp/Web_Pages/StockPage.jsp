<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="VO.StockListVO" import="PO.RiseStockPO"
	import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SuperQuant--您的第一手证券信息</title>

<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../css/agency.css" rel="stylesheet" type="text/css">
<link href="../css/LoginAndReg.css" rel="stylesheet">
<link href="../css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<link href="../css/headNav.css" rel="stylesheet" type="text/css">
<link href="../css/stockpage.css" rel="stylesheet" type="text/css">

</head>

<body id="page-top" class="index">

	<nav class="navbar navbar-default navbar-fixed-top"
		style="background-color: #4A433B;">

	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header page-scroll floatRight">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!--  <a class="navbar-brand page-scroll" href="#page-top">Super Quant</a>  -->
		</div>

		<a href="HomePage.jsp" class="floatLeft"> <img
			src="../webImage/logo.png" title="返回首页" id="logo">
		</a>

		<%
			if (session.getAttribute("User") != null) {
		%>
		<a class="profile floatRight" href="../Web_Pages/PersonalPage.jsp">
			<img alt="" src="../webImage/man.svg" class="headImage">
		</a>
		<%
			} else {
		%>
		<input type="button" value="登录" name="login"
			class="bottons loginbtn floatRight" data-toggle="modal"
			data-target="#modalLogin"
			style="margin-top: 8px; margin-right: -20px;" />
		<%
			}
		%>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse "
			id="bs-example-navbar-collapse-1" style="height: 216px;">
			<ul class="nav navbar-nav navbar-left ">
				<li class="hidden"><a href="#page-top"></a></li>

				<li><a class="page-scroll" href="HomePage.jsp">首页</a></li>
				<li><a class="page-scroll" href="../ToMarketPageServlet">大盘</a></li>
				<li><a class="page-scroll" style="color: rgb(253, 208, 72);"
					href="../ToStockPageServlet">个股</a></li>
				<li><a class="page-scroll" href="../ToBusinessPageServlet">行业</a></li>
				<li><a class="page-scroll" href="StrategyPage.jsp">策略</a></li>
				<li><a class="page-scroll" href="SimulatePage.jsp">模拟投资</a></li>
			</ul>
		</div>

		<div class="style_5 hidden-sm hidden-xs floatRight">
			<fieldset id="searchform">
				<input type="text" placeholder="搜索" class="text_input"
					onblur="this.placeholder='搜索';"
					onfocus="this.placeholder='输入股票代码搜索';"
					onmouseover="this.placeholder='输入股票代码搜索';"
					onmouseout="this.placeholder='搜索';" onkeyup="showHint(this.value)" />
				<input name="submit" type="submit" value='' />
			</fieldset>
		</div>
		<div id="searchHint"></div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<div>
		<div style="width: 850px; margin: 20px auto;">

			<div style="margin-top: 90px; margin-bottom: 15px; font-size: 22px;">热门股票推荐</div>

			<div class="tab_title">
				<div class="tab_each" style="border-bottom: 2px solid #f8b31d;"
					onclick="changeTab(0)">连续上涨</div>
				<div class="tab_each" onclick="changeTab(1)">连续下跌</div>
				<div class="tab_each" onclick="changeTab(2)">创新高</div>
				<div class="tab_each" onclick="changeTab(3)">创新低</div>
				<div class="tab_each" onclick="changeTab(4)">持续放量</div>
				<div class="tab_each" onclick="changeTab(5)">持续缩量</div>
				<div class="tab_each" onclick="changeTab(6)">向上突破</div>
				<div class="tab_each" onclick="changeTab(7)">向下突破</div>
				<div class="tab_each" onclick="changeTab(8)">量价齐升</div>
				<div class="tab_each" onclick="changeTab(9)">量价齐跌</div>
			</div>

			<!-- 股票列表 -->
			<div style="margin-top: 15px;">

				<%
					StockListVO stockListVO = (StockListVO) session.getAttribute("StockList");
					ArrayList<RiseStockPO> stockpolist = stockListVO.getStockList();
				%>

				<table id="senfe">
					<thead>
						<tr>
							<th width="200" bgcolor="#ccc">股票代码</th>
							<th width="130" bgcolor="#ccc">股票名称</th>
							<th width="130" bgcolor="#ccc">最新价</th>
							<th width="130" bgcolor="#ccc">最高价</th>
							<th width="130" bgcolor="#ccc">最低价</th>
							<th width="130" bgcolor="#ccc">连涨天数</th>
							<th width="140" bgcolor="#ccc">连续涨跌幅</th>
							<th width="140" bgcolor="#ccc">累计换手率</th>
							<th width="160" bgcolor="#ccc">所属行业</th>
						</tr>
					</thead>

					<tbody id="group_one">

						<%
							int i = 0;
							for (RiseStockPO stockPO : stockpolist) {
						%>

						<tr onmouseover="mouseIn(<%=i + 1%>);"
							onmouseout="mouseOut(<%=i + 1%>);"
							onclick="mouseClick(<%=i + 1%>,'../ToStockDetailPageServlet')">

							<td><%=stockPO.getStockId()%></td>
							<td><%=stockPO.getStockName()%></td>
							<td><%=stockPO.getNow()%></td>
							<td><%=stockPO.getHigh()%></td>
							<td><%=stockPO.getLow()%></td>
							<td><%=stockPO.getRise_days()%></td>
							<td><%=stockPO.getRise_fall()%></td>
							<td><%=stockPO.getTotal_turnover()%></td>
							<td><%=stockPO.getIndustry()%></td>

						</tr>

						<%
							i++;
							}
						%>

					</tbody>
				</table>

			</div>

			<div style="margin-top: 20px; margin-left: 30%">
				<a onclick="page.firstPage();">首 页</a>/<a onclick="page.nextPage();">下一页</a>/<a
					onclick="page.prePage();">上一页</a>/<a onclick="page.lastPage();">末
					页</a><i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i><span
					id="divFood"> </span>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第 <input id="pageno"
					value="1" style="width: 30px" />页<a>&nbsp;&nbsp;</a><a
					onclick="page.aimPage();">跳转</a>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="modalLogin" tabindex="-1" role="dialog"
		aria-labelledby="modalLogin" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">登录SuperQuant</h4>
					<p>再添人生一桶金</p>
				</div>

				<div class="modal-body">
					<div class="alert alert-danger alert-dismissible fade in"
						role="alert" id='modalLoginErr'>
						<span id='modalLoginErrCont'></span>
					</div>
					<div class='form-group'>
						<input type="text" class="form-control" id="modal-login-name"
							placeholder='昵称'>
					</div>
					<div class='form-group'>
						<input type="password" class="form-control" id="modal-login-pwd"
							placeholder='密码'>
					</div>
				</div>
				<!-- modal-body -->

				<div class="modal-footer">
					<a role="button" href="javascript:login();" class="btn btn-login"
						data-loading-text="验证中...">登录</a>
					<div class='modal-footer-tips'>
						<label style='float: left;'><input type="checkbox"
							id="chk-rmb" value="rmb" checked='checked'> 下次自动登陆</label> <a
							href='javascript:switchToModal("reg");'>没有账号？</a>
					</div>
				</div>

			</div>
		</div>
	</div>
	<!-- End Modal -->

	<!-- bottom section -->
	<div
		style="background-color: #766F67; height: 200px; margin-top: 50px;"></div>

	<div style="background-color: #645D55; height: 50px;">
		<p style="color: white; text-align: center; line-height: 50px;">@Copyright
			SuperQuant</p>
	</div>

	<!-- Plugin JavaScript -->
	<script src="../js/classie.js"></script>
	<script src="../js/cbpAnimatedHeader.js"></script>
	<script src="../js/table_pages.js"></script>
	<script src="../js/common.min.js"></script>
	<script src="../js/jquery.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../js/searchHint.js"></script>
	<script src="../js/stockpage.js"></script>
	<script type="text/javascript">
		setPerPage(15);
	</script>
</body>

</html>