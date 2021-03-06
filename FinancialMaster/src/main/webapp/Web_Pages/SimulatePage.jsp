<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="VO.UserVO"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SuperQuant--您的第一手证券信息</title>

<link href="../css/LoginAndReg.css" rel="stylesheet">

<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">

<link href="../css/agency.css" rel="stylesheet" type="text/css">

<link href="../css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<link href="../css/headNav.css" rel="stylesheet" type="text/css">

<link href="../css/mycheckbox.css" rel="stylesheet" type="text/css">

<link href="../css/simulatepage.css" rel="stylesheet" type="text/css">

<link href="../css/strategystyle.css" rel="stylesheet" type="text/css">

<link rel="stylesheet"
	href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css">

<script src="../js/echarts.min.js"></script>
<script src="../js/jquery.min.js"></script>
</head>

<style>
table td {
	border: 1px dashed #fff;
}
</style>

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
				<li><a class="page-scroll" href="../ToStockPageServlet">个股</a></li>
				<li><a class="page-scroll" href="../ToBusinessPageServlet">行业</a></li>
				<li><a class="page-scroll" href="StrategyPage.jsp">策略</a></li>
				<li><a class="page-scroll" style="color: rgb(253, 208, 72);"
					href="SimulatePage.jsp">模拟投资</a></li>
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
			<!-- /.navbar-collapse -->
		</div>
		<div id="searchHint"
			style="position: absolute; background-color: rgb(235, 235, 235); width: 150px; margin-left: 935px; margin-top: -10px;"></div>
		<!-- /.container-fluid -->
	</nav>

	<div id="parentDiv"
		style="width: 80%; margin: 30px auto; margin-top: 90px;">

		<div class="my_sim_title">
			<div style="display: inline-block;">
				资产净值：<span id="totalfund">10000元</span>

				<div class="init_fund">1W原始资本</div>

			</div>

			<div style="display: inline-block; margin-left: 50px;">
				我的收益：<span id="myprofit" style="color: #e35339;">+1000元</span>
			</div>

			<div style="display: inline-block; margin-left: 50px;">
				购买力：<span id="buyability" style="color: #f8b31d;">10000元</span>
			</div>

			<div class="reset_st_btn newST_btn"
				style="width: 84px; height: 29px; display: inline-block; margin-left: 80px;"
				onclick="addStore();">增加持仓</div>

		</div>

		<!-- 引导图/持有股表格 -->
		<div id="intro_img"
			style="width: 100%; margin-top: 30px; margin-bottom: 0px;">


			<img id="StockListImg" style="width: 100%;"
				src="../webImage/simulate-introduce.png">

			<!-- 持有股表格 -->
			<table id="strategyTable" rules="rows" style="display: none;">
				<thead>
					<tr align="center" valign="middle"
						style="background-color: rgb(230, 230, 230); font-size: 16px;">

						<td width="105" height="40">股票名称</td>
						<td width="100">投资成本</td>
						<td width="105">开始日期</td>
						<td width="105">结束日期</td>
						<td width="195">买入策略</td>
						<td width="195">卖出策略</td>
						<td width="110">其他策略</td>
						<td width="110">买卖频率</td>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<div id="paging_div"
		style="width: 80%; margin: 10px auto; height: 40px; display: none;">

		<i class="fa fa-chevron-left next_page" onclick="nextPage(1)"></i>

		<%-- 页码 --%>
		<div id="pages" style="display: inline-block;"></div>

		<div class="pages_each" style="display: none;">1</div>

		<i class="fa fa-chevron-right next_page" style="margin-left: 10px;"
			onclick="nextPage(0)"></i>

	</div>

	<div id="new_buy" class="makest_div"
		style="display: none; margin-top: 0; width: 80%; margin-bottom: 40px;">

		<i class="fa fa-times close_st" onclick="closeStore()"></i>

		<div style="display: inline-block; vertical-align: top;">
			<div class="div_title">
				新建交易
				<div class="checkbox_div">
					<input id="useST_box" class="mui-switch mui-switch-animbg"
						type="checkbox" onchange="useST(this.checked);"> <span
						class="usest_tip">使用策略</span>
				</div>

			</div>

			<div id="notST_left">
				<div
					style="margin-left: 45px; margin-top: 15px; display: inline-block;">
					<input class="st_textfileds" id="stockchoose" placeholder="股票代码/名称"
						onkeyup="showHint_st(this.value);"
						onBlur="setTimechart(this.value);">
				</div>
				<div id="stHint"></div>

				<div
					style="margin-left: 11px; margin-top: 15px; display: inline-block;">
					<input id="stocknums" class="st_textfileds" placeholder="买入股数">
				</div>

				<div class="tips_word">tips: 模拟交易与真实交易略有不同，将会直接以最新价格成交</div>
			</div>

		</div>

		<!-- 时分图 -->
		<div id="notST_right" class="st_run_div"
			style="height: 295px; display: inline-block; vertical-align: top;">
			<div class="used_money">
				已用本金：<span id="usedmoney">10000</span>
			</div>

			<div class="run_pic" style="height: 220px;">
				<div id="timeSharingDiagram" style="width: 580px; height: 200px;">

					<!-- <img src="../webImage/timechart-introduce.png"
						style="width: 100%; margin: auto auto;"> -->

				</div>
			</div>
		</div>

		<div id="noSTtip" class="noST_tip"
			style="margin-bottom: 20px; display: none;">您还没有已保存策略</div>

		<div id="table_div" style="display: none; margin-bottom: 0;">
			<table id="tableST" rules="rows"
				style="margin: 15px auto; margin-bottom: -50px; border: 1px solid #e9bd8d;">
				<thead>
					<tr align="center" valign="middle"
						style="background-color: #e9bd8d; font-size: 16px; width: 95%; color: #fff;">

						<td width="20px;"><input type="checkbox"
							onclick="selectAll();"></td>

						<td width="85" height="40">策略名称</td>
						<td width="100">股票名称</td>
						<td width="80">投资成本</td>
						<td width="100">开始日期</td>
						<td width="100">结束日期</td>
						<td width="80">买卖频率</td>
						<td width="100">买入策略</td>
						<td width="100">卖出策略</td>
						<td width="100">其他策略</td>
					</tr>
				</thead>
			</table>
		</div>

		<div class="money_left" style="margin-top: 70px;">
			剩余本金:&nbsp;&nbsp;<span id="moneyleft">10000</span>
		</div>

		<div class="add_st_btn" onclick="buyStock();">购&nbsp;&nbsp;买</div>

		<div class="reset_st_btn" onclick="closeStore();">取&nbsp;&nbsp;消</div>

	</div>

	<!-- 历史交易、累计盈亏 -->
	<div
		style="width: 80%; margin: 30px auto; margin-top: 140px; vertical-align: top">

		<div style="width: 45%; display: inline-block;">

			<blockquote class="stname_title" style="margin: 0 auto;">历史交易</blockquote>

			<div id="histrades"
				style="width: 100%; height: 420px; overflow: auto; margin-top: 18px; background-color: #fcfcfc;">

				<div class="noHis_tip" style="display: none">暂无历史交易记录</div>

				<!-- 买 -->
				<div id="his_copy" class="his_each" style="display: none;">
					<div class="syb_buy" style="display: inline-block;">买</div>

					<div class="usest_syb" style="display: none;">策略</div>

					<div
						style="width: 110px; margin-left: 105px; display: inline-block;">
						<span style="font-size: 18px; color: #4a433b;">浦发银行</span><br>
						<span style="font-size: 14px; color: #9e9a95;">sh600000</span>
					</div>

					<div style="margin-left: 60px; display: inline-block;">
						<span style="font-size: 18px; color: #f8b31d">-3000元</span><br>
						<span style="font-size: 14px; color: #9e9a95;">2016-9-10
							14:59:00</span>
					</div>
				</div>

			</div>

		</div>

		<div
			style="width: 50%; margin-left: 4%; display: inline-block; vertical-align: top">

			<blockquote class="stname_title">策略累计盈亏</blockquote>

			<div id="STprofits"
				style="width: 100%; height: 420px; overflow: auto; background-color: #fcfcfc;">

				<div class="noHis_tip">您还没登录</div>

				<div id="st_copy" class="used_sts" style="display: none">
					<div class="terminal">终止模拟</div>
					<span>MyStrategy</span> <span style="color: #c4330c; float: right; margin-right: 20px;">+5000元</span>
				</div>

				<div class="used_sts" style="display: none">
					<span>technical</span> <span style="color: #186b03; float: right;">-1000元</span>
				</div>

			</div>

		</div>

	</div>

	<!-- bottom section -->
	<div style="background-color: #766F67; height: 200px;"></div>

	<div style="background-color: #645D55; height: 50px;">
		<p style="color: white; text-align: center; line-height: 50px;">@Copyright
			SuperQuant</p>
	</div>

	<!-- 持仓股 -->
	<div id="myStockList" class="myList" style="display: none;">
		<div class="listItem">
			<div style="width: 120px; position: absolute; top: 22px; left: 24px;">
				<p class="listName">我阙持股</p>
				<p class="listNum">666666</p>
			</div>
			<div
				style="width: 200px; position: absolute; top: 22px; left: 200px;">
				<p class="buyPrice">买入: 10.00元</p>
				<p class="buyTime" style="font-size: 16px; color: #bbb;">2016-9-12</p>
			</div>
			<div class="buyNum">100股</div>

			<div
				style="padding: 6px 10px; font-size: 30px; height: 46px; position: absolute; top: 12px; right: 25px;">
				<p
					style="font-size: 14px; color: #bbb; margin-bottom: 0px; display: inline-block;">现价</p>
				<p class="nowPrice">10元</p>
				<p
					style="color: #4a433b; font-size: 30px; font-weight: lighter; display: inline-block;">/</p>
				<p
					style="font-size: 14px; color: #bbb; margin-bottom: 0px; display: inline-block;">盈利</p>
				<p class="nowBonus">+1000.00元</p>


				<div class="saleButton" onclick="sell()">卖出</div>

			</div>

		</div>

		<div class="list-extend" style="margin-bottom: 10px;">
			<div class="aChart" style="width: 970px; height: 280px;"></div>
		</div>
	</div>

	<!-- login and register -->
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

	<!-- Modal -->
	<div class="modal fade" id="modalReg" tabindex="-1" role="dialog"
		aria-labelledby="modalReg" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">加入SuperQuant</h4>
					<p>再添人生一桶金</p>
				</div>

				<div class="modal-body">
					<div class="alert alert-danger alert-dismissible fade in"
						role="alert" id='modalRegErr'>
						<span id='modalRegErrCont'></span>
					</div>

					<div class='form-group'>
						<input type="text" class="form-control" id="modal-reg-name"
							placeholder='昵称'>
					</div>

					<div class='form-group'>
						<input type="password" class="form-control" id="modal-reg-pwd"
							placeholder='密码（不少于6位）'>
					</div>


					<div class='form-group'>
						<input type="password" class="form-control" id="modal-reg-pwd2"
							placeholder='请再次确认密码'>
					</div>

				</div>
				<!-- modal-body -->

				<div class="modal-footer">
					<div id='reg-succ-tips'></div>
					<a role="button" href="javascript:reg();" class="btn btn-reg"
						data-loading-text="注册中...">注册</a>
					<div class='modal-footer-tips'>
						<a href='javascript:switchToModal("login");'>已有账号？直接登陆</a>
					</div>
				</div>

			</div>
		</div>
	</div>
	<!-- End Modal -->

	<%-- 用来存放userId --%>
	<a id="storage" style="display: none;"><%=session.getAttribute("User")%>
	</a>

	<%
		ArrayList<String> strategyList = new ArrayList<String>();
		if (session.getAttribute("User") != null) {
			UserVO userVO = (UserVO) session.getAttribute("User");
			strategyList = userVO.getStrategy();
		}
	%>

	<%-- 用来存放策略名称 --%>
	<a id="storageST" style="display: none;"><%=strategyList%> </a>

	<%-- 无阻塞提示框 --%>
	<div id="toaster_close">
		<div id="toaster">
			<div id="pic_div" class="green_pic"></div>
			<div id="remind" class="green_word">提示消息</div>
		</div>
	</div>

	<script src="../js/jquery.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="../js/classie.js"></script>
	<script src="../js/cbpAnimatedHeader.js"></script>
	<script src="../js/jqBootstrapValidation.js"></script>
	<script src='http://s1.yuehetong.com/sitemedia/js/jquery-2.2.1.min.js'></script>
	<script src="../js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../js/searchHint.js"></script>
	<script type="text/javascript" src="../jschart/TimeSharingDiagram.js"></script>
	<script src="../js/common.min.js"></script>
	<script src="../js/simulatepage.js"></script>
	<script src="../js/toaster.js"></script>
	<script type="text/javascript" src="../js/strategyhint.js"></script>
	<script src="../jschart/SimulationLineChart.js"></script>
	<script>
		initHis();
		initStocks();
		initSTprofits();
		$(".myList").on("click", ".listItem", function() {
			$(this).siblings(".list-extend").slideToggle();
		});

		setProfit();
	</script>

</body>
</html>