var tab = [ "ContinuingTrend", "Peak", "ContinuingQuantity", "Breakthrough",
		"Price" ];
var kind = [ "Up", "Down" ];

function changeTab(index) {

	var tabs = document.getElementsByClassName("tab_each");
	for (var i = 0; i < 10; i++) {
		tabs[i].style.borderBottom = "";
	}
	tabs[index].style.borderBottom = "2px solid #f8b31d";

	$.ajax({
				type : "post",
				async : false, // 同步执行
				url : "../ToStockPageServlet",
				dataType : "json",
				data : {
					"kind" : tab[Math.floor(index / 2)],
					"kidKind" : kind[index % 2],
				},
				success : function(result) {
					if (result.length > 0) {
						var list = new Array();
						for (var i = 0; i < result.length; i++) {
							list.push(result[i].value);
						}

						var th = list.splice(0, 1).toString();
						th = th.split(",");
						
						refresh(th, list);
						
					} else {
						document.getElementsByClassName("noHis_tip")[1].style.display = "";
						document.getElementsByClassName("noHis_tip")[1].innerHTML = "您还没有已创建策略";
					}
				},
				error : function(errorMsg) {
					alert("对不起，数据请求失败!");
				}
			});
}

function refresh(tablehead, data) {
	
	var table = document.getElementById("senfe");
	table.innerHTML = '';
	table.style.width = "100%";

	var thead = document.createElement("thead");
	table.appendChild(thead);

	var tr = document.createElement("tr");
	thead.appendChild(tr);
	for (var i = 0; i < tablehead.length; i++) {
		var td = document.createElement("th");
		td.innerHTML = tablehead[i];
		tr.appendChild(td);
	}
	tr.style.backgroundColor = "#ccc";

	var tbody = document.createElement("tbody");
	tbody.setAttribute('id', 'group_one');
	table.appendChild(tbody);

	for (var i = 0; i < data.length; i++) {
		var tr = tbody.insertRow(tbody.rows.length);

		var row = i + 1;
		tr.setAttribute('onmouseover', 'mouseIn(' + row + ')');
		tr.setAttribute('onmouseout', 'mouseOut(' + row + ')');
		tr.setAttribute('onclick', 'gotoDetail(this)');

		for (var j = 0; j < data[0].length; j++) {
			var td = document.createElement("td");
			td.innerHTML = data[i][j];
			tr.appendChild(td);
		}
	}

	page = new Page(perPage, 'senfe', 'group_one');
	senfe("senfe", "#fff", "rgb(239,239,239)", "#ccc", "#f00");
}

function gotoDetail(tr) {

	var stockId = tr.getElementsByTagName("td")[0].innerHTML.trim();

	$.ajax({
		type : "post",
		async : false, // 同步执行
		url : "../ToStockDetailPageServlet",
		data : {
			"Stockid" : stockId
		},
		dataType : "json"
	})
	window.location.href = "../ToStockDetailPageServlet";
}