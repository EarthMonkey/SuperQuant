var iBase = {
	SetOpacity : function(ev, v) {
		ev.filters ? ev.style.filter = 'alpha(opacity=' + v + ')'
				: ev.style.opacity = v / 100;
	}
};

var currentPage = 1; // 当前页数
var allPages; // 所有持仓

var stprofits = 0; // 策略盈利
var stockprofits = 0; // 非策略盈利
var hisprofits = 0; // 历史交易记录

// 关闭块
function closeST(elem_id) {

	var elem = document.getElementById(elem_id);
	var speed = 15;
	var opacity = 0;

	var val = 100;
	(function() {
		iBase.SetOpacity(elem, val);
		val -= 5;
		if (val >= opacity) {
			setTimeout(arguments.callee, speed);
		} else if (val < 0) {
			elem.style.display = 'none';
		}
	})();
}

// 打开块
function launchST(elem_id) {

	var elem = document.getElementById(elem_id);
	var speed = 20;
	var opacity = 100;

	elem.style.display = "block";
	iBase.SetOpacity(elem, 0);
	var val = 0;
	(function() {
		iBase.SetOpacity(elem, val);
		val += 5;
		if (val <= opacity) {
			setTimeout(arguments.callee, speed)
		}
	})();
}

function addStore() {

	var userId = document.getElementById("storage").innerHTML.trim();
	if (userId == "null") {
		slidein(2, "您还没登录");
		return;
	}

	document.getElementById("intro_img").style.display = "none";
	launchST("new_buy");
}

function closeStore() {

	document.getElementById("stockchoose").value = "";
	document.getElementById("stocknums").value = "";
	document.getElementById("new_buy").style.display = "none";
	launchST("intro_img");

	useST(false);
	var table = document.getElementById("tableST");
	var trs = table.getElementsByTagName("tr");
	for (var i = trs.length - 1; i > 0; i--) {
		table.deleteRow(i);
	}

	document.getElementById("useST_box").checked = false;
}

function setTimechart(stockname) {
	setId(stockname);
	getTimeSharingDiagram("stock");
}

function useST(checked) {

	var notST_left = document.getElementById("notST_left");
	var notST_right = document.getElementById("notST_right");
	var tablediv = document.getElementById("table_div");
	var table = document.getElementById("tableST");

	if (checked == true) {
		document.getElementsByClassName("usest_tip")[0].style.color = "#e9bd8d";

		notST_left.style.display = "none";
		notST_right.style.display = "none";

		var stnames = document.getElementById("storageST").innerHTML.trim();
		stnames = stnames.slice(1, stnames.length - 1).split(",");

		if (stnames.length == 0) {
			document.getElementById("noSTtip").style.display = "";
		} else {

			tablediv.style.display = "";
			for (var i = 0; i < stnames.length; i++) {

				var buylist = "";
				var soldlist = "";
				var stlist = "";
				var stname = stnames[i].trim();

				$.ajax({
					type : "post",
					async : false, // 同步执行
					url : "../ToStrategyPageServlet",
					data : {
						'StrategyName' : stname
					},
					dataType : "json",
					success : function(result) {
						buylist = result[0].BuyList,
								soldlist = result[0].SoldList,
								stlist = result[0].perST
					},
					error : function(errorMsg) {
						alert("不好意思，请求数据失败啦!");
					}
				});

				var buyList = buylist.toString().split(";");
				var stList = stlist.toString().split(";");
				var soldList = soldlist.toString().split(";");

				for (var k = 0; k < stList.length - 1; k++) {

					var stl = stList[k].toString().split(",");
					var buy = buyList[k].toString().split(",");
					var sold = soldList[k].toString().split(",");

					var tr = table.insertRow(1);
					tr.style.height = "35px";
					tr.align = "center";
					tr.valign = "middle";
					tr.style.fontSize = "16px";

					var checkbox = document.createElement("input");
					checkbox.type = "checkbox";
					var td = document.createElement("td");
					td.appendChild(checkbox);
					tr.appendChild(td);
					checkbox.onclick = function() {
						var boxs = table.getElementsByTagName("input");
						if (this.checked == false && boxs[0].checked == true) {
							boxs[0].checked = false;
						} else if (this.checked == true
								&& boxs[0].checked == false) {
							var selectall = 1;
							for (var i = 1; i < boxs.length; i++) {
								if (boxs[i].checked == false) {
									selectall = 0;
									break;
								}
							}
							if (selectall == 1) {
								boxs[0].checked = true;
							}
						}
					}

					var td = document.createElement("td");
					td.innerHTML = stname;
					tr.appendChild(td);
					for (var j = 0; j < stl.length; j++) {
						var td = document.createElement("td");
						if (j == stl.length - 1) {
							td.innerHTML = "每" + stl[j] + "天";
						} else {
							td.innerHTML = stl[j];
						}
						tr.appendChild(td);
					}

					// 买入策略
					var td = document.createElement("td");
					td.style.fontSize = "13px";
					td.innerHTML = stDeal(buy, 0);
					tr.appendChild(td);

					// 卖出策略
					var td = document.createElement("td");
					td.style.fontSize = "13px";
					td.innerHTML = stDeal(sold, 1);
					tr.appendChild(td);

					// 其他策略
					var td = document.createElement("td");
					td.style.fontSize = "13px";
					td.innerHTML = "敬请期待";
					tr.appendChild(td);
				}
			}
		}

	} else {
		document.getElementsByClassName("usest_tip")[0].style.color = "#90949b";

		document.getElementById("noSTtip").style.display = "none";
		tablediv.style.display = "none";
		notST_left.style.display = "";
		notST_right.style.display = "";

		var trs = table.getElementsByTagName("tr");
		for (var i = trs.length - 1; i > 0; i--) {
			table.deleteRow(i);
		}
	}
}

// 策略文字化; 0买入，1卖出
function stDeal(st, sybol) {
	var str = [ "股价", "成交量", "换手率", "pe", "pb" ];
	var BorS = [ "时买入", "时卖出" ]
	var via = "落在";
	var result = "当";
	var other = [ "您未限定买入条件", "始终持有该股" ];

	var douhao = 0;
	for (var i = 0; i < 5; i++) {
		if (st[i * 2] != 0 || st[i * 2 + 1] != 0) {
			if (douhao == 1) {
				result += "、";
			}
			result = result + str[i] + via + st[i * 2] + "~" + st[i * 2 + 1];
			douhao = 1;
		}
	}

	if (douhao == 1) {
		return (result + BorS[sybol]);
	} else {
		return other[sybol];
	}
}

function selectAll() {
	var table = document.getElementById("tableST");
	var boxs = table.getElementsByTagName("input");

	if (boxs[0].checked == false) {
		for (var i = 0; i < boxs.length; i++) {
			if (boxs[i].type == "checkbox")
				boxs[i].checked = false;
		}
	} else {
		for (var i = 0; i < boxs.length; i++) {
			if (boxs[i].type == "checkbox")
				boxs[i].checked = true;
		}
	}
}

function buyStock() {

	var stockname = document.getElementById("stockchoose").value;
	var stocknums = document.getElementById("stocknums").value;

	if (document.getElementById("useST_box").checked == true) {
		// 使用策略

		var table = document.getElementById("tableST");
		var trs = table.getElementsByTagName("tr");
		var box = table.getElementsByTagName("input");

		var stname = new Array();
		var stockid = new Array();
		var tempcount = 0;

		for (var i = 0; i < box.length; i++) {
			if (box[i].checked == true) {
				stname[tempcount] = trs[i].getElementsByTagName("td")[1].innerHTML
						.trim();
				stockid[tempcount] = trs[i].getElementsByTagName("td")[2].innerHTML
						.trim();
				tempcount++;
			}
		}

		buyStock_ST(stname, stockid);

	} else {
		// 不使用策略
		$.ajax({
			type : "post",
			async : false, // 同步执行
			url : "../SimulationStock",
			data : {
				"Order" : "Buy",
				"StockID" : stockname,
				"Number" : stocknums
			},
			dataType : "json",
			success : function(result) {
				if (result[0].BuyResult > -1) {
					slidein(0, "购买成功");
					setTimeout("window.location.reload()", 1800);
				}
			},
			error : function(errorMsg) {
				alert("不好意思，请求数据失败啦!");
			}
		});
	}
}

function buyStock_ST(stname, stockid) {

	for (var i = 0; i < stname.length; i++) {
		$.ajax({
			type : "post",
			async : false, // 同步执行
			url : "../SimulationStrategyServlet",
			data : {
				"Order" : "Start",
				"StockID" : stockid[i],
				"StrategyName" : stname[i],
			},
			dataType : "json",
			success : function(result) {
				if (i == stname.length - 1) {
					slidein(0, "购买成功");
					setTimeout("window.location.reload()", 1800);
				}
			},
			error : function(errorMsg) {
				alert("不好意思，请求数据失败啦!");
			}
		});
	}

}

// 历史交易记录
function initHis() {

	var userId = document.getElementById("storage").innerHTML.trim();
	if (userId != "null") {
		document.getElementsByClassName("noHis_tip")[0].style.display = "none";

		$
				.ajax({
					type : "get",
					async : false, // 同步执行
					url : "../SimulationRecord",
					dataType : "json",
					success : function(result) {

						if (result.length > 0) {

							for (var i = result.length - 1; i >= 0; i--) {

								var div = document.createElement("div");
								div.innerHTML = document
										.getElementById("his_copy").innerHTML;
								div.setAttribute("class", "his_each");

								var BorS = "-";

								if (result[i].deal == "Sell"
										|| result[i].deal == "ST_Sell") {
									div.getElementsByClassName("syb_buy")[0].innerHTML = "卖";
									BorS = "+";

									hisprofits += result[i].money;
								} else {
									hisprofits -= result[i].money;
								}

								if (result[i].deal == "ST_Buy"
										|| result[i].deal == "ST_Sell") {
									div.getElementsByClassName("usest_syb")[0].style.display = "";
									div.getElementsByTagName("div")[2].style.marginLeft = "85px";
									div.style.backgroundColor = "#fff8ea";
								}

								var spans = div.getElementsByTagName("span");
								spans[0].innerHTML = result[i].stockName;
								spans[1].innerHTML = result[i].stockID;
								spans[2].innerHTML = BorS + result[i].money;
								if (BorS == "-") {
									spans[2].style.color = "#4A433B";
								}
								spans[3].innerHTML = result[i].time;

								document.getElementById("histrades")
										.appendChild(div);

							}
						} else {
							document.getElementsByClassName("noHis_tip")[0].style.display = "";
						}
					},
					error : function(errorMsg) {
						alert("不好意思，请求数据失败啦!");
					}
				});
	} else {
		document.getElementsByClassName("noHis_tip")[0].style.display = "";
		document.getElementsByClassName("noHis_tip")[0].innerHTML = "您还没登录";
	}
}

function initStocks() {

	var userId = document.getElementById("storage").innerHTML.trim();
	if (userId != "null") {

		$.ajax({
			type : "get",
			async : false, // 同步执行
			url : "../SimulationStock",
			dataType : "json",
			success : function(result) {
				if (result.length > 0) {
					$("#StockListImg").hide();

					allPages = result;

					if (result.length > 5) {
						$("#paging_div").show();
						var pages = Math.ceil(result.length / 5);
						for (var j = 0; j < pages; j++) {
							var div = document.createElement("div");
							div.innerHTML = j + 1;

							if (j == 0) {
								div.setAttribute("class", "pages_each active");
							} else {
								div.setAttribute("class", "pages_each");
							}

							div.onclick = function() {
								gotoPage_node(this);
							};

							document.getElementById("pages").appendChild(div);

							gotoPage(1);
						}
					} else {
						for (var i = 0; i < result.length; i++) {
							setStocks(result[i]);
						}
					}

					// 累计盈利
					for (var i = 0; i < result.length; i++) {
						stockprofits += result[i].sum;
					}
				}
			},
			error : function(errorMsg) {
				alert("不好意思，请求数据失败啦!");
			}
		});
	}
}

function setStocks(result) {

	var parent = document.getElementById("intro_img");

	var div = document.createElement("div");
	div.setAttribute("class", "myList");
	div.innerHTML = document.getElementById("myStockList").innerHTML;
	div.getElementsByClassName("listName")[0].innerHTML = result.stockName;
	div.getElementsByClassName("listNum")[0].innerHTML = result.stockID;
	div.getElementsByClassName("buyPrice")[0].innerHTML = "买入：" + result.price;
	div.getElementsByClassName("buyTime")[0].innerHTML = result.time;
	div.getElementsByClassName("buyNum")[0].innerHTML = result.number + "股";
	div.getElementsByClassName("nowPrice")[0].innerHTML = result.now;
	div.getElementsByClassName("nowBonus")[0].innerHTML = result.profitability
			.toFixed(2);

	var sale = div.getElementsByClassName("saleButton")[0];
	sale.onclick = function() {
		sell(result.id);
	};

	parent.appendChild(div);

	getLinechart_sim(result.id, div.getElementsByClassName("aChart")[0]);

}

/* 分页 */
function gotoPage_node(node) {
	var index = node.innerHTML.trim();
	gotoPage(index);
}

// 0，下一页；1，上一页
function nextPage(symbol) {
	var nums = document.getElementById("paging_div").getElementsByClassName(
			"pages_each").length;
	if (symbol == 0) {
		if (currentPage < nums) {
			gotoPage(currentPage + 1);
		}
	} else {
		if (currentPage - 1 > 0) {
			gotoPage(currentPage - 1);
		}
	}
}

function gotoPage(index) {

	var pagelbls = document.getElementById("pages").getElementsByClassName(
			"pages_each");
	pagelbls[currentPage - 1].setAttribute("class", "pages_each");
	pagelbls[index - 1].setAttribute("class", "pages_each active");

	currentPage = parseInt(index);

	var parent = document.getElementById("intro_img");
	var list = parent.getElementsByClassName("myList");
	var len = list.length;

	for (var n = len - 1; n >= 0; n--) {
		parent.removeChild(list[n]);
	}

	// (index-1) * 5 , index * 5 <= result.length
	if (index * 5 <= allPages.length) {

		for (var i = (index - 1) * 5; i < index * 5; i++) {
			setStocks(allPages[i]);
		}

	} else {

		for (var i = (index - 1) * 5; i < allPages.length; i++) {
			setStocks(allPages[i]);
		}

	}

}

function sell(id) {
	$.ajax({
		type : "post",
		async : false, // 同步执行
		url : "../SimulationStock",
		data : {
			"Order" : "Sell",
			"id" : id,
		},
		dataType : "json",
		success : function(result) {
			slidein(0, "卖出成功");
			setTimeout("window.location.reload()", 1800);
		},
		error : function(errorMsg) {
			alert("卖出失败，请稍后再试");
		}
	});
}

function initSTprofits() {

	var userId = document.getElementById("storage").innerHTML.trim();
	if (userId != "null") {
		document.getElementsByClassName("noHis_tip")[1].style.display = "none";

		$.ajax({
					type : "get",
					async : false, // 同步执行
					url : "../SimulationStrategy",
					dataType : "json",
					success : function(result) {
						if (result.length > 0) {

							var parent = document.getElementById("STprofits");
							var copy = document.getElementById("st_copy");

							for (var i = 0; i < result.length; i++) {
								var div = document.createElement("div");
								div.setAttribute("class", "used_sts");
								div.innerHTML = copy.innerHTML;

								var spans = div.getElementsByTagName("span");
								spans[0].innerHTML = result[i].stockName;
								if (result[i].profitability >= 0) {
									spans[1].innerHTML = "+"
											+ result[i].profitability + "元";
								} else {
									spans[1].innerHTML = "-"
											+ result[i].profitability + "元";
									spans[1].style.color = "#186b03";
								}

								var a = document.createElement("a");
								a.innerHTML = result[i].id;
								a.style.display = "none";
								div.appendChild(a);

								div.getElementsByClassName("terminal")[0].onclick = function() {
									Terminal(this);
								}

								parent.appendChild(div);

								stprofits += result[i].profitability;
							}

						} else {
							document.getElementsByClassName("noHis_tip")[1].style.display = "";
							document.getElementsByClassName("noHis_tip")[1].innerHTML = "您还没有已创建策略";
						}
					},
					error : function(errorMsg) {
						alert("策略累计盈亏数据请求失败");
					}
				});
	}
}

function Terminal(node) {

	var id = node.parentNode.getElementsByTagName("a")[0].innerHTML;

	$.ajax({
		type : "post",
		async : false, // 同步执行
		url : "../SimulationStrategyServlet",
		data : {
			"Order" : "End",
			"id" : id,
		},
		dataType : "json",
		success : function(result) {
			slidein(0, "终止成功");
			setTimeout("window.location.reload()", 1800);
		},
		error : function(errorMsg) {
			alert("终止失败");
		}
	});
}

function setProfit() {
	var profits = stprofits + stockprofits + hisprofits;
	document.getElementById("myprofit").innerHTML = profits;
	document.getElementById("buyability").innerHTML = 10000 + hisprofits;
	document.getElementById("totalfund").innerHTML = 10000 + profits;
}
