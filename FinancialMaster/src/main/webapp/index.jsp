<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    import="VO.BenchVO"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>hello</title>
	<script src="js/echarts.min.js"></script>
	<script src="js/jquery.min.js"></script>
</head>
<body>
   <h1>中文!</h1>
     <a href="UpdateBenchVO">  test </a>
     
     <div id="barChart_inflows" style="width: 900px; height: 600px;"></div>
     <div id="pieChart_inflows" style="width: 900px; height: 600px;"></div>

	<script src="jschart/barChart_inflows.js" ></script>
	<script src="jschart/PieChart_inflows.js" ></script>
</body>
</html>