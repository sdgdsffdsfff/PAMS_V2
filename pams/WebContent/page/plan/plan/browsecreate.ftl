
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=no,minimum-scale=1,maximum-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>高层计划</title>
<link rel="stylesheet" href="/pams/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/pams/lib/font-awesome/css/font-awesome.min.css">
<link href="../logo60.png" rel="apple-touch-icon">
<link href="../logo76.png" rel="apple-touch-icon" sizes="76x76">
<link href="../logo120.png" rel="apple-touch-icon" sizes="120x120">
<link href="../logo152.png" rel="apple-touch-icon" sizes="152x152">
<link rel="stylesheet"
	href="/pams/lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="/pams/css/animation.css">
<link rel="stylesheet" href="/pams/lib/slider.css">
<link rel="stylesheet" href="/pams/css/main.css">
<script src="/pams/lib/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" href="/pams/css/advplan2.css">
</head>
<body>

<div id="container">
<div class="gsop">
	<div class="opleft">
		<button id="bt_insert" class="btn btn-primary">新增</button>
		<button id="bt_delete" class="btn btn-primary">删除</button>		
	</div>
</div>

<table id="allContactTb" class="table personListTable hover">
<thead>
    <tr>
      <th class="check"></th>   
      <th>名称</th>
      <th>计划开始</th>
      <th>计划结束</th>
      <th>负责人</th>
      <th>完成比例(%)</th>      
      <th>工作量</th>
      <th>工作量比例(%)</th>      
      <th>实际开始</th>
      <th>实际结束</th>    
    </tr>
  </thead>
  <tbody>
  </tbody>
</table>

<script src="/pams/lib/jquery-ui.min.js"></script>
<script src="/pams/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="/pams/lib/moment.min.js"></script>
<script src="/pams/lib/moment.zh-cn.js"></script>
<script
	src="/pams/lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="/pams/lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="/pams/lib/lodash.min.js"></script>
<script src="/pams/lib/bootstrap-slider.js"></script>
<script src="/pams/lib/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="/pams/js/main.js"></script>
<script>


jQuery(function($) {
	//获得数据
	$.ajax({
		url: '/pams/plan/plan/browsecreate/json.action',
		dataType: 'json',
		success: function(d)
		{
			// console.log(d);
			data = d;
			
			$.each(data,function(i,n)
			{
				var trHtml = "";
				trHtml += '<tr data-id="'+n.id+'">';
				trHtml += '<td class="check"></td>';
				trHtml += '<td><a href="/pams/plan/plan/locate.action?id='+n.id+'">'+n.cname+'</a></td>';				
				trHtml += '<td>'+n.planstartdate+'</td>';
				trHtml += '<td>'+n.planenddate+'</td>';
				trHtml += '<td>'+n.planheadercname+'</td>';
				trHtml += '<td></td>';
				trHtml += '<td>'+n.baseplanworkload+'</td>';
				trHtml += '<td></td>';
				trHtml += '<td>'+n.actualstartdate +'</td>';
				trHtml += '<td>'+n.actualenddate +'</td>';

				trHtml += '</tr>';
				
				$('#allContactTb tbody').append(trHtml);
			});
		},
		error:function(req, status, e)
		{
			console.log(e);
		}
	
	});
});

function page_locate(id)
{
	console.log("locate.begin.");
	window.location = "/pams/plan/plan/locate.action?id=" + id;
}

		
</script>
</body>
</html>