<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=no,minimum-scale=1,maximum-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>高层计划</title>
<link rel="stylesheet" href="${base}/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${base}/lib/font-awesome/css/font-awesome.min.css">
<link href="${base}/logo60.png" rel="apple-touch-icon">
<link href="${base}/logo76.png" rel="apple-touch-icon" sizes="76x76">
<link href="${base}/logo120.png" rel="apple-touch-icon" sizes="120x120">
<link href="${base}/logo152.png" rel="apple-touch-icon" sizes="152x152">
<link rel="stylesheet"
	href="${base}/lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="${base}/css/animation.css">
<link rel="stylesheet" href="${base}/lib/slider.css">
<link rel="stylesheet" href="${base}/css/main.css">
<script src="${base}/lib/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" href="${base}/css/advplan2.css">
</head>
<body>
	<script>
		var ajax_url_add_node = '${base}/plan/plan/addsubplan.action';
		var ajax_url_delete_node = '${base}/plan/plan/deleteplan.action';
		var ajax_url_save_node = '${base}/plan/plan/saveplan.action';
		var ajax_url_save_node_sequence = '../plan/data/advplan_save_sequence.json';
		var ajax_url_save_node_parentworkload = '../data/advplan_save_node.json';//任务计算 workload 以后 用于保存父节点的 wordload
		var ajax_url_decompose_node = '${base}/plan/plan/decomposeplan.action';
		var ajax_url_decomposedate_node = '${base}/plan/plan/decomposedateplan.action';		
	</script>

	<div id="container">
		<div class="gsop">
			<div class="opleft">
				<button id="bt_gantt" class="btn btn-primary">跟踪甘特</button>
			</div>
			<div class="opright">
				<div id="showLevel">
					<span class="t">显示：</span>
					<ul>
						<li data-level="1">1</li>
						<li data-level="2">2</li>
						<li class="active">全部</li>
					</ul>
				</div>
			</div>
		</div>
		<div id="advplan">
			<div class="header">
				<div class="tr">
					<div class="td name">名称</div>
					<div class="td">计划开始</div>
					<div class="td">计划结束</div>
					<div class="td">负责人</div>
					<div class="td">实际开始</div>
					<div class="td">实际结束</div>
					<div class="td">实际负责人</div>
					<div class="td">完成比例(%)</div>
					<div class="td">工作量</div>
					<div class="td">工作量比例(%)</div>
					<div class="td">基准工作量</div>
					<div class="td">基准开始</div>
					<div class="td">基准结束</div>
				</div>
			</div>
			<div class="root"></div>
			<div class="tree"></div>
		</div>
	</div>
	<div id="gsModal" tabindex="-1" style="display134: block;"
		class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<div class="pull-right">
						<div data-dismiss="modal" class="close">&times;</div>
					</div>
					<h4 class="modal-title name"></h4>
					<ul id="modalTabs-title">
						<li class="active">基本信息</li>
					</ul>
				</div>
				<div class="modal-body">
					<div id="modalTabs-content">
						<div class="item active">
							<div id="gop">
								<div class="fl">
									<div class="btn-group">
										<button data-toggle="dropdown"
											class="btn btn-primary dropdown-toggle">
											<i class="fa fa-plus"></i> 添加子节点 <span class="caret"></span>
										</button>
										<ul id="addNewNode" class="dropdown-menu">
											<li data-type="0" data-dismiss="modal"><i
												class="fa fa-flag"></i> 阶 段</li>
											<li data-type="1" data-dismiss="modal"><i
												class="fa fa-flag-o"></i> 任 务</li>
											<li data-type="2" data-dismiss="modal"><i
												class="fa fa-dropbox"></i> 交付物</li>
											<li data-type="3" data-dismiss="modal"><i
												class="fa fa-flag-checkered"></i> 里程碑</li>
										</ul>
									</div>
									<div class="btn-group">
										<button data-toggle="dropdown"
											class="btn btn-primary dropdown-toggle">
											<i class="fa fa-plus"></i> 按流程分解任务 <span class="caret"></span>
										</button>
										<ul id="decomposeNode" class="dropdown-menu">
											<li data-type="1" data-dismiss="modal" id="flow_dfgl_dfsj_jshz" flowid='DFGL_DFSJ_JSHZ'><i
											class="fa fa-flag-o" ></i> 党费收缴-基数核准流程</li>	
											<li data-type="1" data-dismiss="modal" id="flow_dfgl_dfsj_jhbz" flowid='DFGL_DFSJ_JHBZ'><i
											class="fa fa-flag-o" ></i> 党费收缴-计划编制流程</li>	
											<li data-type="1" data-dismiss="modal" id="flow_dfgl_dfsj_dfsj" flowid='DFGL_DFSJ_DFSJ'><i
											class="fa fa-flag-o" ></i> 党费收缴-党费收缴流程</li>
										
											<li data-type="1" data-dismiss="modal" id="flow_dfgl_dfsy_yjzq" flowid='DFGL_DFSY_YJZQ'><i
											class="fa fa-flag-o" ></i> 党费使用-意见征求流程</li>	
											<li data-type="1" data-dismiss="modal" id="flow_dfgl_dfsy_jhbz" flowid='DFGL_DFSY_JHBZ'><i
											class="fa fa-flag-o" ></i> 党费使用-计划编制流程</li>	
											<li data-type="1" data-dismiss="modal" id="flow_dfgl_dfsy_dfsy" flowid='DFGL_DFSY_DFSY'><i
											class="fa fa-flag-o" ></i> 党费使用-党费使用流程</li>
											
										</ul>
									</div>

									<div class="btn-group">
									<button data-toggle="dropdown"
										class="btn btn-primary dropdown-toggle">
										<i class="fa fa-plus"></i> 按周期分解任务 <span class="caret"></span>
									</button>
									<ul id="decomposedateNode" class="dropdown-menu">
										<li data-type="1" data-dismiss="modal" id="flow_dfgl_dfsj_jshz" dateid='QUARTER'><i
										class="fa fa-flag-o" ></i> 按季度分解</li>										
										<li data-type="1" data-dismiss="modal" id="flow_dfgl_dfsj_jshz" dateid='MONTH'><i
										class="fa fa-flag-o" ></i> 按月分解</li>	
										<li data-type="1" data-dismiss="modal" id="flow_dfgl_dfsj_jhbz" dateid='WEEK'><i
										class="fa fa-flag-o" ></i> 按周分解</li>	
									</ul>
									</div>

									<button id="deleteNode" class="btn btn-danger">
										<i class="fa fa-times"></i> 删除
									</button>
								</div>
								<div class="fr">
									<span class="t">调整顺序</span> <select id="gsIndex"
										class="form-control"></select>
									<button id="save" data-dismiss123="modal"
										class="btn btn-primary">
										<i class="fa fa-check"></i> 保存
									</button>
								</div>
							</div>
							<div id="formContainer">
							
							<input id="id" name="id" type="hidden" class="form-control"> 
							<input id="parentid" name="parentid" type="hidden" class="form-control">
							<input id="sequencekey" name="sequencekey" type="hidden" class="form-control">
							
						     <div class="form-group">
						        <label for="cname" class="col-sm-2 control-label">标题名称：<sup class="fa fa-asterisk"></sup></label>
						        <div class="col-sm-10"> 
						          <input id="cname" name="cname" value="" class="form-control">
						        </div>           
						     </div>
						     
						      <div class="form-group">
						        <label for="planheadercname" class="col-sm-2 control-label">负责人：<sup class="fa fa-asterisk"></sup></label>
						        <div class="col-sm-4">
						        	<input id="planheader" name="planheader" required="" value="" type="hidden">
						        	 <input id="planheadercname" name="planheadercname" value="" class="form-control" type="hidden">
						        	
						        	<select
									id="planheadercnametext" name="planheadercnametext" class="form-control">
						        	<#list obj.users as aobj>
						        		<option value="${aobj.loginname}">${aobj.cname}</option>
						        	</#list>
									</select> 
						        	
						        </div>
						        <label for="chargedeptname" class="col-sm-2 control-label">主管部门：<sup class="fa fa-asterisk"></sup></label>
						        <div class="col-sm-4"> 
						        	<input id="chargedeptid" name="chargedeptid" type="hidden" required="" value="">
						        	<input id="chargedeptname" name="chargedeptname" value="" class="form-control" type="hidden">
						        	<select
									id="chargedeptnametext" name="chargedeptnametext" class="form-control">
						        	<#list obj.depts as aobj>
						        		<option value="${aobj.id}">${aobj.cname}</option>
						        	</#list>
									</select> 
						          
						        </div>            
						      </div> 
						      
						      <div class="form-group">
						        <label for="masterdeptname" class="col-sm-2 control-label">主办部门：<sup class="fa fa-asterisk"></sup></label>
						        <div class="col-sm-4">
						        	<input id="masterdeptid" name="masterdeptid" required="" value="" type="hidden">
						        	<input id="masterdeptname" name="masterdeptname" value="" class="form-control" type="hidden">
						        	<select
									id="masterdeptnametext" name="masterdeptnametext" class="form-control">
						        	<#list obj.depts as aobj>
						        		<option value="${aobj.id}">${aobj.cname}</option>
						        	</#list>
									</select> 
						        </div>
						        <label for="slavedeptname" class="col-sm-2 control-label">协办部门：<sup class="fa fa-asterisk"></sup></label>
						        <div class="col-sm-4"> 
						        	<input id="slavedeptid" name="slavedeptid" type="hidden" value="">
						        	<input id="slavedeptname" name="slavedeptname" value="" class="form-control" type="hidden">
						        	<select
									id="slavedeptnametext" name="slavedeptnametext" class="form-control">
						        	<#list obj.depts as aobj>
						        		<option value="${aobj.id}">${aobj.cname}</option>
						        	</#list>
									</select> 
						          
						        </div>            
						      </div>
						      
						      <div class="form-group">
						        <label for="phasestatus" class="col-sm-2 control-label">状态：<sup class="fa fa-asterisk"></sup></label>
						        <div class="col-sm-4">
						        <select id=""
								name="phasestatus" class="form-control">
									<option value="计划">计划</option>
									<option value="执行">执行</option>
									<option value="完成">完成</option>
									</select> 
						        </div>
						        <label for="ismantory" class="col-sm-2 control-label">是否可裁剪：<sup class="fa fa-asterisk"></sup></label>
						        <div class="col-sm-4"> 
						        <select
								name="ismantory" class="form-control">
									<option value="0">不能</option>
									<option value="1">可以</option>
								</select>
						        </div>            
						      </div>
						      
							     <div class="form-group">
							        <label for="remark" class="col-sm-2 control-label">描述：<sup class="fa fa-asterisk"></sup></label>
							        <div class="col-sm-10"> 
							          <input id="remark" name="remark" value="" class="form-control">
							        </div>           
							     </div>
							     
							     <div class="form-group">
							        <label for="delayreason" class="col-sm-2 control-label">延迟原因：<sup class="fa fa-asterisk"></sup></label>
							        <div class="col-sm-10"> 
							          <input id="delayreason" name="delayreason" value="" class="form-control">
							        </div>           
							     </div>
						      
							     <div class="form-group">
							        <label for="strategy" class="col-sm-2 control-label">应对措施：<sup class="fa fa-asterisk"></sup></label>
							        <div class="col-sm-10"> 
							          <input id="strategy" name="strategy" value="" class="form-control">
							        </div>           
							     </div>
							     
							     <div class="form-group">
							        <label for="memo" class="col-sm-2 control-label">备注：<sup class="fa fa-asterisk"></sup></label>
							        <div class="col-sm-10"> 
							          <input id="memo" name="memo" value="" class="form-control">
							        </div>           
							     </div>
							     
								<div id="dateBox">
									<table class="table-condensed table">
										<thead>
											<tr>
												<th></th>
												<th>开始</th>
												<th>结束</th>
												<th>工期</th>
												<th>工作量(人日)</th>
												<th>工作量(%)</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<th>初次基准</th>
												<td class="datetd"></td>
												<td class="datetd"></td>
												<td class="numtd"></td>
												<td class="numtd"></td>
												<td class="percenttd"></td>
											</tr>
											<tr>
												<th>前次基准</th>
												<td class="datetd"></td>
												<td class="datetd"></td>
												<td class="numtd"></td>
												<td class="numtd"></td>
												<td class="percenttd"></td>
											</tr>
											<tr>
												<th>基准</th>
												<td class="datetd"></td>
												<td class="datetd"></td>
												<td class="numtd"></td>
												<td class="numtd"></td>
												<td class="percenttd"></td>
											</tr>
											<tr class="plan">
												<th>执行计划</th>
												<td><input name="planstartdate"
													class="form-control datepicker"></td>
												<td><input name="planenddate"
													class="form-control datepicker"></td>
												<td><input type="number" class="form-control">
												</td>
												<td><input type="number" name="planworkload"
													class="form-control"></td>
												<td class="percenttd">0</td>
											</tr>
											<tr class="actual">
												<th>实际</th>
												<td><input name="actualstartdate"
													class="form-control datepicker"></td>
												<td><input name="actualenddate"
													class="form-control datepicker"></td>
												<td><input type="number" class="form-control">
												</td>
												<td><input type="number" name="actualworkload"
													class="form-control"></td>
												<td class="percenttd">0</td>
											</tr>
										</tbody>
									</table>
								</div>
								<label class="gsRange"><span class="t">完成百分比 <input
										class="num form-control" type="number" min="0" max="100">
										% <input name="completepercent" type="range" value="" max="1"
										min="0" step="0.01"></span></label>
							</div>
						</div>
						<div class="item">
							<iframe frameborder="0" src=""></iframe>
						</div>
						<div class="item">
							<iframe frameborder="0" src=""></iframe>
						</div>


					</div>
				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
	<script src="${base}/lib/jquery-ui.min.js"></script>
	<script src="${base}/lib/bootstrap/js/bootstrap.min.js"></script>
	<script src="${base}/lib/moment.min.js"></script>
	<script src="${base}/lib/moment.zh-cn.js"></script>
	<script
		src="${base}/lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script
		src="${base}/lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="${base}/lib/lodash.min.js"></script>
	<script src="${base}/lib/bootstrap-slider.js"></script>
	<script src="${base}/lib/bootstrap-switch/js/bootstrap-switch.min.js"></script>
	<script src="${base}/js/main.js"></script>
	<script>
		var params = parseUrl(window.location.href);
		var planid = params["id"];

		//calculate time
		var now = new Date(); //性能测试

		//all data
		var data = []; //传中的 全局变量

		var PHASEORSTEP_cn = [ '阶段', '任务', '交付物', '里程碑' ];

		var thtml = '';
		
		$("#bt_gantt").click(function() {page_gantt()});
		
		function page_gantt()
		{
			var url = "${base}/page/plan/plan/gantt.html?planid="+planid;
			window.open(url);
		}
		

		jQuery(function($) {
			
			//
			var modalTabsTitle = $('#modalTabs-title');
			var modalTabsContent = $('#modalTabs-content');

			modalTabsTitle.on('click', 'li', function() {
				var oindex = $(this).index();
				$(this).addClass('active').siblings().removeClass('active');
				modalTabsContent.find('>.item:eq(' + oindex + ')').addClass(
						'active').siblings().removeClass('active');
			})

			//获得数据
			$.ajax({
				url : '${base}/plan/plan/listsubplanjson.action',
				dataType : 'json',
				data : {
					id : planid
				}, // planid 为当前记录标识
				/*
				注意：直接 beforeSend:showAjaxDiv 的话
				event 会被传递到 showAjaxDiv 里面去，导致 错误
				 */
				beforeSend : function() {
					showAjaxDiv();
				},
				complete : function() {
					hideAjaxDiv();
				},
				async : false, //同步，以逃避一个巨大的 括号
				success : function(d) {
					data = d;
				}
			});

			ADVPLAN = $('#advplan');

			//root 部门 处理 ------------------
			var rootDiv = ADVPLAN.find('.root');

			//planstartdate 中最小值
			var jihuakaishi = moment(_.min(data.items, function(x) {
				if (!$.trim(x.planstartdate) == '') {
					return moment(x.planstartdate)._d.getTime();
				}
			}).planstartdate).format('YYYY-MM-DD');

			//proj_phase表中 planenddate 中最大值
			var jihuajieshu = moment(_.max(data.items, function(x) {
				if (!$.trim(x.planenddate) == '') {
					return moment(x.planenddate)._d.getTime();
				}
			}).planenddate).format('YYYY-MM-DD');

			//基准开始时间：proj_phase表中basestartdate中最小值
			var jizhunkaishi = moment(_.min(data.items, function(x) {
				if (!$.trim(x.BASESTARTDATE) == '') {
					return moment(x.BASESTARTDATE)._d.getTime();
				}
			}).BASESTARTDATE).format('YYYY-MM-DD');

			//基准结束时间：proj_phase表中 baseenddate 中最大值
			var jizhunjieshu = moment(_.max(data.items, function(x) {
				if (!$.trim(x.BASEENDDATE) == '') {
					return moment(x.BASEENDDATE)._d.getTime();
				}
			}).BASEENDDATE).format('YYYY-MM-DD');

			//实际开始时间：proj_phase表中 actualstartdate 中最小值
			var shijikaishi = moment(_.min(data.items, function(x) {
				if (!$.trim(x.actualstartdate) == '') {
					return moment(x.actualstartdate)._d.getTime();
				}
			}).actualstartdate).format('YYYY-MM-DD');

			//实际结束时间：proj_phase表中 actualenddate 中最大值
			var shijijieshu = moment(_.max(data.items, function(x) {
				if (!$.trim(x.actualenddate) == '') {
					return moment(x.actualenddate)._d.getTime();
				}
			}).actualenddate).format('YYYY-MM-DD');

			var rootHtml = '';
			rootHtml += '<div class="tr" data-type="-1">';
			rootHtml += '<div class="td name">' + data.projname + '</div>';
			rootHtml += '<div class="td" data-name="planstartdate">' + jihuakaishi + '</div>';
			rootHtml += '<div class="td" data-name="planenddate">' + jihuajieshu + '</div>';
			rootHtml += '<div class="td" data-name="planheadercname">' + data.planheadercname + '</div>';
			rootHtml += '<div class="td" data-name="actualstartdate">' + shijikaishi + '</div>';
			rootHtml += '<div class="td" data-name="actualenddate">' + shijijieshu + '</div>';
			rootHtml += '<div class="td" data-name="actualheadercname"></div>';
			rootHtml += '<div class="td">' + data.completepercent * 100 + '</div>';
			rootHtml += '<div class="td gongzuoliang">' + data.planworkload + '</div>';
			rootHtml += '<div class="td">' + 100 + '</div>';
			rootHtml += '<div class="td">' + data.baseplanworkload + '</div>';
			rootHtml += '<div class="td">' + shijikaishi + '</div>';
			rootHtml += '<div class="td">' + shijijieshu + '</div></div>';

			rootDiv.html(rootHtml);

			//tree 部门 处理 ------------------

			data.items = _.sortBy(data.items, 'sequencekey');

			_.levelRecursive(data.items, planid);

			console.log(data.items);

			var treeDiv = ADVPLAN.find('.tree');
			treeDiv.html(thtml);

			console.log(thtml);

			resizeWidth2MaxName();

			// 事件 -------------------------------------

			//toggle
			ADVPLAN.on('click', '.toggle', function(e) {
				e.stopPropagation();
				$(this).closest('li').toggleClass('closed');
			})

			//#gsModal
			var formContainer = $('#formContainer');

			//编辑窗口   －－－－－－start
			ADVPLAN.on('click', '.tr .name', function() {
				showname($(this));

			});

			/*
			- 实际结束 有日期 则  并且被 disable
			- 实际结束 blur 如果有数值 则 完成比例 100% 
			- 日期被删除 完成比例 enabled
			- 完成比例 100 时  alert('必须有结束日期，才能100%') 然后 99
			 */
			var actualInput = formContainer.find('[name=actualenddate]');
			var gsRangeInputs = formContainer.find('.gsRange').find('input');

			actualInput.on('change blur', function() {
				if ($.trim($(this).val()) != '') {
					/*- 实际结束 有日期 则  并且被 disable
					- 实际结束 blur 如果有数值 则 完成比例 100% */
					gsRangeInputs.filter(':eq(0)').val(100).attr({
						disabled : 'disabled'
					});
					gsRangeInputs.filter(':eq(1)').val(1).attr({
						disabled : 'disabled'
					});
				} else {//日期被删除 完成比例 enabled
					gsRangeInputs.removeAttr('disabled');
				} 
			})

			gsRangeInputs.filter(':eq(0)').on('blur', function() {
				if ($(this).val() == '100') {
					alert('必须有结束日期，才能100%');
					$(this).val('99');
					actualInput.trigger('focus');
				}
			})

			gsRangeInputs.filter(':eq(1)').on('blur', function() {
				if ($(this).val() == '1') {
					alert('必须有结束日期，才能100%');
					$(this).val('.99');
					actualInput.trigger('focus');
				}
			})

			function showname(_this) 
			{

                console.log(_this);
                console.log(_this.text());

                //判断类型
                // console.log(_this.attr('data-type'))
                //-1 root 0-3 '阶段', '任务', '交付物', '里程碑'

                //只能在 .name 点击
                var otr = _this.closest('.tr');
                console.log(otr);

                //modal 加类型 控制表单 内容
                $('#gsModal').attr('data-type', otr.attr('data-type'));

                //标题 类型
                // console.log(otr.attr('data-type'),$.trim(_this.text()))
                var title = '';
                if (otr.attr('data-type') == '-1') {
                    title = '<i class="fa fa-calendar"></i> 项目'
                } else if (otr.attr('data-type') == '0') {
                    title = '<i class="fa fa-flag"></i> 阶段'
                } else if (otr.attr('data-type') == '1') {
                    title = '<i class="fa fa-flag-o"></i> 任务'
                } else if (otr.attr('data-type') == '2') {
                    title = '<i class="fa fa-dropbox"></i> 交付物'
                } else if (otr.attr('data-type') == '3') {
                    title = '<i class="fa fa-flag-checkered"></i> 里程碑'
                }
                $('#gsModal').find('.modal-title').html(title);

                /*  if (k.PHASEORSTEP == 0) {
                 iconstr = '<i class="otype fa fa-flag" title="' + PHASEORSTEP_cn[k.PHASEORSTEP] + '"></i>';
                 } else if (k.PHASEORSTEP == 1) {
                 iconstr = '<i class="otype fa fa-flag-o" title="' + PHASEORSTEP_cn[k.PHASEORSTEP] + '"></i>';
                 } else if (k.PHASEORSTEP == 2) {
                 iconstr = '<i class="otype fa fa-dropbox" title="' + PHASEORSTEP_cn[k.PHASEORSTEP] + '"></i>';
                 } else {
                 iconstr = '<i class="otype fa fa-flag-checkered" title="' + PHASEORSTEP_cn[k.PHASEORSTEP] + '"></i>';
                 }
                 */

                if (otr.attr('data-type') == '-1') {
                    //项目属性
                    $('#gsModal').modal('show');
                    return false;
                }

                var li = otr.closest('li');
                var sid = li.attr('data-id');
                var found = _.find(data.items, {
                    id : sid
                });

                var siblings = li.parent().find('>li');

                var html = '';
                siblings.each(function(j, k) {
                    var selected = '';
                    if (j == li.index()) {
                        selected = 'selected'
                    }
                    html += '<option ' + selected + ' value="' + (j + 1) + '">'
                            + (j + 1) + '</option>'

                })
                $('#gsIndex').html(html);

                //回填数据到 modal
                formContainer.find(':input').each(function(j, k) {
          // console.log("name:" + this.name);
          // console.log("value:" + found[this.name]);
          
                    if (this.name) {
                        $(this).val(found[this.name])
                    }
                })

                //add number to num input
                $('.gsRange input[type=range]').trigger('change');


                // 下拉选择赋值 临时 蒲剑
                if($('#planheader').val()!=="")
                {
                	$("#planheadercnametext option[value='"+$('#planheader').val()+"']").attr("selected",true);
                }
                
                if($('#chargedeptid').val()!=="")
                {
                	$("#chargedeptnametext option[value='"+$('#chargedeptid').val()+"']").attr("selected",true);
                }
                
                if($('#masterdeptid').val()!=="")
                {
                	$("#masterdeptnametext option[value='"+$('#masterdeptid').val()+"']").attr("selected",true);
                }
                
                if($('#slavedeptid').val()!=="")
                {
                	$("#slavedeptnametext option[value='"+$('#slavedeptid').val()+"']").attr("selected",true);
                }
                
                //dateBox 初始化

                //初次基准
                // firstenddate
                var thistr = $('#dateBox').find('tbody tr:eq(0)');
                var start = $.trim(found['firststartdate']);
                var end = $.trim(found['firstenddate']);
                if (start != '') {
                    thistr.find('td:eq(0)').text(
                            moment(start).format('YYYY-MM-DD'));
                } else {
                    thistr.find('td:eq(0)').text('');
                }
                if (end != '') {
                    thistr.find('td:eq(1)').text(
                            moment(end).format('YYYY-MM-DD'));
                } else {
                    thistr.find('td:eq(1)').text('');
                }
                thistr.find('td:eq(2)').text(''); //reset 以免留给别人
                if (start && end) {
                    //都有,才计算
                    var period = parseInt(
                            (moment(end)._d.getTime() - moment(start)._d
                                    .getTime())
                                    / (1000 * 60 * 60 * 24), 10);
                    period += 1;
                    thistr.find('td:eq(2)').text(period);
                }
                thistr.find('td:eq(3)').text(found['firstplanworkload']);

                //前次基准
                // previousstartdate
                var thistr = $('#dateBox').find('tbody tr:eq(1)');
                var start = $.trim(found['previousstartdate']);
                var end = $.trim(found['previousenddate']);
                if (start != '') {
                    thistr.find('td:eq(0)').text(
                            moment(start).format('YYYY-MM-DD'));
                } else {
                    thistr.find('td:eq(0)').text('');
                }
                if (end != '') {
                    thistr.find('td:eq(1)').text(
                            moment(end).format('YYYY-MM-DD'));
                } else {
                    thistr.find('td:eq(1)').text('');
                }
                thistr.find('td:eq(2)').text(''); //reset 以免留给别人
                if (start && end) {
                    //都有,才计算
                    var period = parseInt(
                            (moment(end)._d.getTime() - moment(start)._d
                                    .getTime())
                                    / (1000 * 60 * 60 * 24), 10);
                    period += 1;
                    thistr.find('td:eq(2)').text(period);
                }
                thistr.find('td:eq(3)').text(found['previosplanworkload']);

                //基准
                // BASESTARTDATE
                var thistr = $('#dateBox').find('tbody tr:eq(2)');
                var start = $.trim(found['basestartdate']);
                var end = $.trim(found['baseenddate']);
                if (start != '') {
                    thistr.find('td:eq(0)').text(
                            moment(start).format('YYYY-MM-DD'));
                } else {
                    thistr.find('td:eq(0)').text('');
                }
                if (end != '') {
                    thistr.find('td:eq(1)').text(
                            moment(end).format('YYYY-MM-DD'));
                } else {
                    thistr.find('td:eq(1)').text('');
                }
                thistr.find('td:eq(2)').text(''); //reset 以免留给别人
                if (start && end) {
                    //都有,才计算
                    var period = parseInt(
                            (moment(end)._d.getTime() - moment(start)._d
                                    .getTime())
                                    / (1000 * 60 * 60 * 24), 10);
                    period += 1;
                    thistr.find('td:eq(2)').text(period);
                }
                thistr.find('td:eq(3)').text(found['baseplanworkload']);

                //计划
                var planTr = $('#dateBox').find('.plan');
                var planinput0 = planTr.find('input:eq(0)');
                var planinput1 = planTr.find('input:eq(1)');
                var planinput2 = planTr.find('input:eq(2)');
                var planinput3 = planTr.find('input:eq(3)');

                planinput2.val(''); //reset 否则会 留给 后面的家伙

                planinput0.val($.trim(found['planstartdate']) == '' ? ''
                        : moment(found['planstartdate']).format('YYYY-MM-DD'));
                planinput1.val($.trim(found['planenddate']) == '' ? ''
                        : moment(found['planenddate']).format('YYYY-MM-DD'));

                if (planinput0.val() != '' && planinput1.val() != '') {
                    var period = moment(planinput1.val())._d.getTime()
                            - moment(planinput0.val())._d.getTime();
                    if (period < 0) {
                        alert('开始 应该在 结束 之前！');
                    } else {
                        period = parseInt(period / (1000 * 60 * 60 * 24), 10);
                        period += 1;
                        planinput2.val(period);
                    }
                }
                // planinput3.val(found['PLANWORKLOAD']);

                //PLANINCOMEPERCENT

                //实际
                var actualTr = $('#dateBox').find('.actual');
                var actualinput0 = actualTr.find('input:eq(0)');
                var actualinput1 = actualTr.find('input:eq(1)');
                var actualinput2 = actualTr.find('input:eq(2)');
                var actualinput3 = actualTr.find('input:eq(3)');

                actualinput2.val(''); //reset 否则会 留给 后面的家伙

                actualinput0
                        .val($.trim(found['actualstartdate']) == '' ? ''
                                : moment(found['actualstartdate']).format(
                                        'YYYY-MM-DD'));
                actualinput1.val($.trim(found['actualenddate']) == '' ? ''
                        : moment(found['actualenddate']).format('YYYY-MM-DD')); //传说中的 拼写错误在此！ atcual 应该是 actual

                if (actualinput0.val() != '' && actualinput1.val() != '') {
                    var period = moment(actualinput1.val())._d.getTime()
                            - moment(actualinput0.val())._d.getTime();

                    if (period < 0) {
                        alert('开始 应该在 结束 之前！');
                    } else {
                        period = parseInt(period / (1000 * 60 * 60 * 24), 10);
                        period += 1;
                        actualinput2.val(period);
                    }
                }
                // actualinput3.val(found['ACTUALWORKLOAD']);

                //记住原来的数值
                $('#dateBox').find('input').each(function() {
                    _this.attr('data-origin', _this.val());
                })

                //实际结束 和 完成比例
                gsRangeInputs.removeAttr('disabled');

                //实际结束 有日期 则  并且被 disable
                if ($.trim(actualInput.val()) != '') {
                    gsRangeInputs.attr({
                        disabled : 'disabled'
                    });
                }

                //如果有子任务，则工作量不可编辑
                if (li.find('>ul .tr[data-type=1]').length != 0) {
                    formContainer.find('[name=planworkload]').attr({
                        readonly : 'readonly'
                    });
                    formContainer.find('[name=actualworkload]').attr({
                        readonly : 'readonly'
                    });
                } else {
                    formContainer.find('[name=planworkload]').removeAttr(
                            'readonly');
                    formContainer.find('[name=actualworkload]').removeAttr(
                            'readonly');
                }

                $('#gsModal').modal('show');
            }

      
      //#addNewNode
	$('#addNewNode').on('click', 'li', function() {
		add_node($(this));
	})

	//保存按钮
	$('#save').on('click', function() {
		save_node($(this));
	})
	
	//删除
    $('#deleteNode').on('click', function() {
    	delete_node($(this));
    })

	//分解计划
	$('#decomposeNode').on('click', 'li', function() {
    	decompose_node($(this));
    }) 
    
    	//分解计划
	$('#decomposedateNode').on('click', 'li', function() {
    	decomposedate_node($(this));
    }) 
    
    
/*     	//分解计划
    $('#flow_dfgl_jhbz').on('click', function() {
    	decompose_node($(this));
    }) */

    function add_node(_this)
	{
		var otype = _this.attr('data-type');
    	var sname = $.trim(window.prompt('给个名字吧', ''));
    	if (sname != '') 
    	{
      		if (!ajax_url_add_node) 
      		{
        		alert('页面中要指定 ajax_url_add_node ！');
        		return false;
      		}
      		var cid = formContainer.find(':input[name=id]').val();

      		if (otype == '0') 
      		{ 
      			//如果点击  项目 root
        		cid = data.projid;
      		}
			// var pid=formContainer.find(':input[name=parentid]').val();
      		$.ajax({
        		url: ajax_url_add_node,
		        dataType: 'json',
		        method: 'post',
		        data: 
		        {
		          type: otype,
		          sname: sname,
		          pid: cid,
		        },
		        success:function(d)
		        {
		        	ui_addsubplan(cid, d.item, otype);
		        }
			})
		}
	}

	function save_node(_this)
	{
        var sid = formContainer.find(':input[name=id]').val();

        //dom
        var li = treeDiv.find('[data-id=' + sid + ']');

        if (li.length == 0) {
            alert('没找到这个 li');
            return false;
        }
        
        // 赋值
        $('#planheader').val($('#planheadercnametext').val());
        $('#planheadercname').val($('#planheadercnametext').find("option:selected").text()); 
        
        $('#chargedeptid').val($('#chargedeptnametext').val());
        $('#chargedeptname').val($('#chargedeptnametext').find("option:selected").text());        

        $('#masterdeptid').val($('#masterdeptnametext').val());
        $('#masterdeptname').val($('#masterdeptnametext').find("option:selected").text());  
        
        $('#slavedeptid').val($('#slavedeptnametext').val());
        $('#slavedeptname').val($('#slavedeptnametext').find("option:selected").text());          
        
        var litr = li.find('>.tr');
        var otype = litr.attr('data-type');

        if (otype == '0' || otype == '1') { //如果 阶段和任务
            litr.find('[data-name]').each(function(j, k) {

                var dataname = $(k).attr('data-name');
                var v = formContainer.find('[name=' + $(k).attr('data-name') + ']').val();
                if (dataname == 'completepercent') {
                    v*=100;
                }
                $(k).text(v);
            })
        } else { //里程碑和交付物 没有 百分比 工作量
            litr.find('[data-name]').each(function(j, k) {

                var dataname = $(k).attr('data-name');
                if (dataname == 'completepercent' || dataname == 'planworkload') {
                    return;
                }
                var v = formContainer.find('[name=' + $(k).attr('data-name') + ']').val();
                $(k).text(v);
            })
        }

        //data
        var found = _.find(data.items, {
            id: sid
        });
        formContainer.find(':input').each(function(j, k) {
            if (k.name) {
                found[k.name] = $(this).val();
            }
        })
        // _.find(data.items, {id: '2740' });//4 test in console

        //diguiTask
        diguiTask(li);

        //如果优化，可以看一下 $('[name=PLANWORKLOAD]') 的 value 和 data-origin="8888" shifou 一致来判断 是否有过修改！！
        calcWordload();

        //修改 root 的 min max －－－－－start

        //planstartdate 中最小值
        var jihuakaishi = moment(_.min(data.items, function(x) {
            if (!$.trim(x.planstartdate) == '') {
                return moment(x.planstartdate)._d.getTime();
            }
        }).planstartdate).format('YYYY-MM-DD');

        //proj_phase表中 planenddate 中最大值
        var jihuajieshu = moment(_.max(data.items, function(x) {
            if (!$.trim(x.planenddate) == '') {
                return moment(x.planenddate)._d.getTime();
            }
        }).planenddate).format('YYYY-MM-DD');

        //实际开始时间：proj_phase表中 actualstartdate 中最小值
        var shijikaishi = moment(_.min(data.items, function(x) {
            if (!$.trim(x.actualstartdate) == '') {
                return moment(x.actualstartdate)._d.getTime();
            }
        }).actualstartdate).format('YYYY-MM-DD');

        //实际结束时间：proj_phase表中 actualenddate 中最大值
        var shijijieshu = moment(_.max(data.items, function(x) {
            if (!$.trim(x.actualenddate) == '') {
                return moment(x.actualenddate)._d.getTime();
            }
        }).actualenddate).format('YYYY-MM-DD');

        // console.log(rootDiv)
        rootDiv.find('[data-name=planstartdate]').text(jihuakaishi);
        rootDiv.find('[data-name=planenddate]').text(jihuajieshu);
        rootDiv.find('[data-name=actualstartdate]').text(shijikaishi);
        rootDiv.find('[data-name=actualenddate]').text(shijijieshu);

        //修改 root 的 min max end

        //console.log(found);
        
        //ajax
        $.ajax({
            url: ajax_url_save_node,
            type: 'post',
            data: found,
            success: function(d) {
                if (d.status == 'success') {
                    $('#gsModal').modal('hide');
                    // console.log('saved');
                } else {
                    alert('发生错误！');
                }
            }
        })
	}

    function delete_node(_this)
    {
    	if (!window.confirm('注意：该操作会删除所有子节点！你确定吗？')) 
    	{
            return false;
        }
        var sid = formContainer.find(':input[name=id]').val();
        $('#gsModal').modal('hide');

        var li = $('#advplan .tree').find('li[data-id=' + sid + ']');
        var delArr = [];
        delArr.push(sid);
        li.find('li').each(function() {
            delArr.push($(this).attr('data-id'));
        })

        console.log(delArr.join(','));

        //ajax
        $.ajax({
            url: ajax_url_delete_node,
            data: {
                ids: delArr.join(',')
            },
            success: function() {
                //先删除  data
                _.remove(data.items, function(x) {
                    // console.log( $.inArray(x.id,delArr))
                    if ($.inArray(x.id, delArr) != -1) {
                        return true;
                    }
                })

                //然后删除 dom
                li.animate({
                    height: 0
                }, 300, 'swing', function() {
                    var pul = li.closest('ul');
                    var pli = pul.closest('li');
                    $(this).remove();

                    //重新计算 dom 和 data.items 中的 SEQUENCEKEY
                    calculateSeq($('#advplan .tree>ul'));
                    //重新计算 .name 宽度
                    resizeWidth2MaxName();

                    //如果是 独子 挂了，则需要删除 ul 以及 toggle
                    if (pul.find('>li').length == 0) {
                        pul.remove();
                        pli.find('.toggle').replaceWith('<i class="spacer"></i>');
                    }
                });

            }
        })
    }

	// 分解计划
	function decompose_node(_this)
	{
		console.log(_this);
		console.log("flowid:" + _this.attr('flowid'));
		var otype = _this.attr('data-type');
		var sname = "";
		// if (sname != '') 
		// {
		// if (!ajax_url_decompose_node) 
		// {
		// 	alert('页面中要指定 ajax_url_add_node ！');
		// 	return false;
		// }
		var cid = formContainer.find(':input[name=id]').val();

		if (otype == '0') 
		{ 
			cid = data.projid;
		}

		console.log("pujian decompose_node 1.");

		// 用户选择流程开始 后期增加

		// 用户选择流程结束
		var flowid = _this.attr('flowid');

		$.ajax({
	        url: ajax_url_decompose_node,
	        dataType: 'json',
	        method: 'post',
	        data: {
	          flowid: flowid,
	          planid: cid
	        },
	        success: function(d) {

	        	console.log(d);

	        	$.each(d, function(j, item) {

	        		console.log(j + ":" + item);

	        		ui_addsubplan(cid, item, otype);

            	});

	        }
	    });
	}
	
	
	// 分解计划 按周期
	function decomposedate_node(_this)
	{
		console.log(_this);
		console.log("dateid:" + _this.attr('dateid'));
		var otype = _this.attr('data-type');
		var sname = "";
		// if (sname != '') 
		// {
		// if (!ajax_url_decompose_node) 
		// {
		// 	alert('页面中要指定 ajax_url_add_node ！');
		// 	return false;
		// }
		var cid = formContainer.find(':input[name=id]').val();

		if (otype == '0') 
		{ 
			cid = data.projid;
		}

		var dateid = _this.attr('dateid');

		$.ajax({
	        url: ajax_url_decomposedate_node,
	        dataType: 'json',
	        method: 'post',
	        data: {
	          dateid: dateid,
	          planid: cid
	        },
	        success: function(d) {

	        	console.log(d);

	        	$.each(d, function(j, item) {

	        		console.log(j + ":" + item);

	        		ui_addsubplan(cid, item, otype);

            	});

	        }
	    });
	}

});


		//迭代在此！输出 tree tr;顺便 调整 sequence 为正确数值
		_.levelRecursive = function(data, parentid, depth, seq) {

			console.log("data:" + data);
			console.log("parentid:" + parentid);
			console.log("depth:" + depth);
			console.log("seq:" + seq);

			if (typeof depth == 'number') { //depth
				depth++;
			} else {
				depth = 1;
			}
			if (!seq) {
				seq = '0001';
			}
			var found = _.filter(data, {
				"parentid" : parentid
			})
			if (found.length > 0) {
				found = _.sortBy(found, 'sequencekey'); //根据 sequencekey 排序！

				thtml += '<ul>';

				$
						.each(
								found,
								function(j, k) {

									console.log(j + ":" + k);

									var child = _.filter(data, {
										"parentid" : k.id
									});
									var togglestr = '<i class="spacer"></i>';
									if (child.length > 0) {
										togglestr = '<i class="toggle"></i>';
									}
									var iconstr = '';
									if (k.phaseorstep == 0) {
										iconstr = '<i class="otype fa fa-flag" title="' + PHASEORSTEP_cn[k.phaseorstep] + '"></i>';
									} else if (k.phaseorstep == 1) {
										iconstr = '<i class="otype fa fa-flag-o" title="' + PHASEORSTEP_cn[k.phaseorstep] + '"></i>';
									} else if (k.phaseorstep == 2) {
										iconstr = '<i class="otype fa fa-dropbox" title="' + PHASEORSTEP_cn[k.phaseorstep] + '"></i>';
									} else {
										iconstr = '<i class="otype fa fa-flag-checkered" title="' + PHASEORSTEP_cn[k.phaseorstep] + '"></i>';
									}

									//sequencekey
									// var gsSequence = seq + padZero(j + 1);

									var gsSequence = k.sequencekey;

									thtml += '<li data-seq="' + gsSequence + '" data-pid="' + k.parentid + '" data-id="' + k.id + '" data-depth="' + depth + '">';

									var jihuakaishi = '';
									if ($.trim(k.planstartdate) != '') {
										jihuakaishi = moment(k.planstartdate)
												.format('YYYY-MM-DD');
									}

									var jihuajieshu = '';
									if ($.trim(k.planenddate) != '') {
										jihuajieshu = moment(k.planenddate)
												.format('YYYY-MM-DD');
									}
									//完成比例：proj_phase表中(sum(planworkload*completepercent)/ sum(planworkload))*100  ?????
									var wanchengbili = '';
									if (k.phaseorstep == 0
											|| k.phaseorstep == 1) { //如果是 阶段和任务 完成比例 才有意义！
										wanchengbili = k.completepercent * 100;
									}

									//工作量
									var gongzuoliang = '';
									if (k.phaseorstep == 0
											|| k.phaseorstep == 1) { //如果是 阶段和任务 完成比例 才有意义！
										gongzuoliang = k.planworkload;
									}

									//基准工作量  k.BASEPLANWORKLOAD
									var jizhungongzuoliang = '';
									if (k.phaseorstep == 0
											|| k.phaseorstep == 1) { //如果是 阶段和任务 完成比例 才有意义！
										jizhungongzuoliang = k.baseplanworkload;
									}

									// console.log(k.PLANWORKLOAD, k.COMPLETEPERCENT, k.PLANWORKLOAD)

									var jizhunkaishi = '';
									if ($.trim(k.basestartdate) != '') {
										jizhunkaishi = moment(k.basestartdate)
												.format('YYYY-MM-DD');
									}

									var jizhunjieshu = '';
									if ($.trim(k.baseenddate) != '') {
										jizhunjieshu = moment(k.baseenddate)
												.format('YYYY-MM-DD');
									}

									var shijikaishi = '';
									if ($.trim(k.actualstartdate) != '') {
										shijikaishi = moment(k.actualstartdate)
												.format('YYYY-MM-DD');
									}

									var shijijieshu = '';
									if ($.trim(k.actualenddate) != '') {
										shijijieshu = moment(k.actualenddate)
												.format('YYYY-MM-DD');
									}

									thtml += '<div class="tr type' + k.phaseorstep + '" data-type="' + k.phaseorstep + '">';
									thtml += '<div class="td name depth' + depth + '">'
											+ togglestr
											+ iconstr
											+ '<span class="t" data-name="cname">'
											+ k.cname + '</span></div>';
									thtml += '<div class="td" data-name="planstartdate">'
											+ jihuakaishi + '</div>';
									thtml += '<div class="td" data-name="planenddate">'
											+ jihuajieshu + '</div>';
									thtml += '<div class="td" data-name="planheadername">'+k.planheadercname+'</div>';
									thtml += '<div class="td" data-name="actualstartdate">' + shijikaishi + '</div>';
									thtml += '<div class="td" data-name="actualenddate">' + shijijieshu + '</div>';
									thtml += '<div class="td">' + k.actualheadercname + '</div>';										
									
									thtml += '<div class="td" data-name="completepercent">'
											+ wanchengbili + '</div>';
									thtml += '<div class="td gongzuoliang" data-name="planworkload">'
											+ gongzuoliang + '</div>';
									thtml += '<div class="td gongzuoliangbili"></div>';
									// thtml += '<div class="td">' + k.REMARK + '</div>';

	

									thtml += '<div class="td">'
											+ jizhungongzuoliang + '</div>';

									thtml += '<div class="td">' + jizhunkaishi
											+ '</div>';
									thtml += '<div class="td">' + jizhunjieshu
											+ '</div>';

									thtml += '</div>';

									//纠正为正确的 gsSequence
									k.sequencekey = gsSequence;

									if (child.length > 0) {
										_.levelRecursive(data, k.id,
												depth, gsSequence);
									}

									thtml += '</li>';

								})
				thtml += '</ul>';

				console.log("thtml:" + thtml);
			}
		}

		function diguiTask(li) {
			//dom
			var tr = li.find('>.tr');

			if (tr.attr('data-type') != '1') {//只有任务 才递归
				return;
			}

			var num = 0;
			var actualnum = 0;
			var parentLi = li.parent().parent();

			li.closest('ul').find('>li>.tr').each(function(j, k) {
				if ($(k).attr('data-type') != '1') {//只有任务 才递归
					return;
				}
				num += $(k).find('.gongzuoliang').text() * 1;

				//找到 li
				var actualItem = _.find(data.items, {
					id : $(k).parent().attr('data-id')
				});
				actualnum += actualItem.actualworkload * 1;
			})

			parentLi.find('>.tr').find('.gongzuoliang').text(num);

			//data.items
			var found = _.find(data.items, {
				id : parentLi.attr('data-id')
			});
			if (found) {
				found.planworkload = num;
				found.actualworkload = actualnum;

				//actualworkload 没有dom 有  data.items

				//ajax 保存父节点 被计算出来的 workload
				$.ajax({
					url : ajax_url_save_node_parentworkload,
					// type: 'post',
					// data: found,
					data : {
						sid : found.id,
						planworkload : found.planworkload,
						actualworkload : found.actualworkload
					},
					success : function(d) {
						if (d.status == 'success') {
							// $('#gsModal').modal('hide');
							// console.log('saved');
						} else {
							alert('发生错误！');
						}
					}
				})

			}

			//递归父节点
			if (parentLi.attr('data-depth') != '1') {//还没有到达阶段
				diguiTask(parentLi);
			}

		}

		//递归 重新计算 seq
		function calculateSeq(ul, seq) {
			var lis = ul.find('>li');
			if (!seq) {
				seq = '1000'; //怪异的 1000，为何不 0001 反正也是 字符排序
			}
			lis.each(function(j, k) {
				var gsSequence = seq + padZero(j + 1);
				$(this).attr('data-seq', gsSequence);
				var ul = $(this).find('>ul');
				if (ul.length != 0) {
					calculateSeq(ul, gsSequence)
				}

				//同步 data
				_.find(data.items, {
					id : $(this).attr('data-id')
				}).sequencekey = gsSequence;
			})
		}

		//补0
		function padZero(s) {
			var str = '' + s;
			var pad = "0000"; //0000 的数量（位数）
			return pad.substring(0, pad.length - str.length) + str;
		}

		//初始化 name 宽度到 最大的那个！
		function resizeWidth2MaxName() {
			var allNameCol = ADVPLAN.find('.name');

			//reset 2 minimum 否则会继承 刚才的 宽度！！！！
			allNameCol.css({
				width : 0
			});

			var maxNameWidth = _.max(allNameCol, function(a) {
				return $(a)[0].scrollWidth;
			}).scrollWidth + 10 + 'px';
			allNameCol.css({
				width : maxNameWidth
			})
		}

		//计算---------

	function calcWordload(){

					//root 部门 处理 ------------------
		var rootDiv = ADVPLAN.find('.root');
		var treeDiv = ADVPLAN.find('.tree');

		var totalWordload = 0;
		$.each(data.items, function(j, k) {
			if(k.PHASEORSTEP=='0'){
				totalWordload += k.PLANWORKLOAD * 1;
			}
		})
		rootDiv.find('.gongzuoliang').text(totalWordload);

		//all .tr 处理一遍  工作量比例
		treeDiv.find('.tr').each(function(j,k){
			var wordload=$(this).find('.gongzuoliang').text()*1;
			var bilitd=$(this).find('.gongzuoliangbili');
			bilitd.text((wordload/totalWordload*100).toFixed(2));
			// console.log(wordload);
		})

	}

        // 蒲剑抽象
        function ui_addsubplan(cid, item, otype)
        {
          var sid = item.id;
          var treeDiv = ADVPLAN.find('.tree');
          //在 dom 中添加

          if (otype == '0') { //如果点击  项目 root
            var li = $('');
            var childUl = treeDiv.find('>ul');


            var depth = 1;

          } else {
            var li = treeDiv.find('li[data-id=' + cid + ']');
            var childUl = li.find('>ul');
            //seq
            var seq = li.attr('data-seq') + padZero(1);
            //depth
            var depth = li.attr('data-depth') * 1 + 1;
          }


          console.log("pujian test 2.");


          //PHASEORSTEP_cn
          var iconstr = '';
          if (otype == 0) {
            iconstr = '<i class="otype fa fa-flag" title="' + PHASEORSTEP_cn[otype] + '"></i>';
          } else if (otype == 1) {
            iconstr = '<i class="otype fa fa-flag-o" title="' + PHASEORSTEP_cn[otype] + '"></i>';
          } else if (otype == 2) {
            iconstr = '<i class="otype fa fa-dropbox" title="' + PHASEORSTEP_cn[otype] + '"></i>';
          } else {
            iconstr = '<i class="otype fa fa-flag-checkered" title="' + PHASEORSTEP_cn[otype] + '"></i>';
          }
          
          console.log("pujian test 3.");

          //新建的时候  如果父容器是 关闭的 那就打开！否则 没看见，还以为没有呢
          if (li) {
            li.removeClass('closed');
          }

          if (childUl.length == 0) {
            li.append('<ul class="animateIn"><li data-seq="' + seq + '" data-pid="' + cid + '" data-id="' + sid + '" data-depth="' + depth + '"><div class="tr type' + otype + '" data-type="' + otype + '"><div class="td name depth' + depth + '"><i class="spacer"></i>' + iconstr + '<span class="t">' + item.cname + '</span></div><div class="td" data-name="planstartdate"></div> <div class="td" data-name="planenddate"></div> <div class="td" data-name="completepercent"></div> <div class="td gongzuoliang" data-name="planworkload"></div> <div class="td gongzuoliangbili"></div> <div class="td" data-name="actualstartdate"></div> <div class="td" data-name="actualenddate"></div> <div class="td"></div> <div class="td"></div> <div class="td"></div> </div> </li></ul>');

            //生 孩子 之后 要被冠以“妈妈”称号 spacer 2 toggle
            li.find('>.tr>.name>i.spacer').replaceWith('<i class="toggle"></i>');

          } else {
            //seq  排在 众兄弟 之后！
            var seq = li.attr('data-seq') + padZero(childUl.find('>li').length + 1);

            if (otype == '0') { //新建 阶段
              var seq = '1000' + padZero(childUl.find('>li').length + 1);
            }

            childUl.append('<li class="animateIn" data-seq="' + seq + '" data-pid="' + cid + '" data-id="' + sid + '" data-depth="' + depth + '"><div class="tr type' + otype + '" data-type="' + otype + '"><div class="td name depth' + depth + '"><i class="spacer"></i>' + iconstr + '<span class="t">' + item.cname + '</span></div><div class="td" data-name="planstartdate"></div> <div class="td" data-name="planenddate"></div> <div class="td" data-name="COMPLETEPERCENT"></div> <div class="td gongzuoliang" data-name="PLANWORKLOAD"></div> <div class="td gongzuoliangbili"></div> <div class="td" data-name="actualstartdate"></div> <div class="td" data-name="actualenddate"></div> <div class="td"></div> <div class="td"></div> <div class="td"></div> </div> </li>');
          }
          
          console.log("pujian test 4.");

          //翻滚 以至于 被看见！
          if (li[0]) {
            li[0].scrollIntoView();
          }

          //重新计算 .name 宽度
          resizeWidth2MaxName();

          //然后在 data 中添加
          var oindex = _.indexOf(data.items, _.find(data.items, {
            id: cid
          })); //这是 data 中 前面一个 的 index

          if (otype == '0') {
            var oindex = data.items.length;
          }

          //如果 新建儿子，就是这个 oindex;如果已经有 儿子，则需要排排 顺序
          if (childUl.find('>li').length > 1) {
            oindex += childUl.find('>li').length - 1;
          }

          data.items.splice(oindex + 1, 0, {
            "id": sid,
            "parentid": cid,
            "cname": item.cname,
            "contactid": "",
            "ismantory": "",
            "sequencekey": seq,
            "completepercent": "0",
            "approvalstatus": "",
            "description": "",
            "remark": "",
            "modifytime": "",
            "modifier": "",
            "planstartdate": item.planstartdate,
            "planenddate": item.planenddate,
            "forecastdate": "",
            "forecastenddate": "",
            "actualstartdate": item.actualstartdate,
            "actualenddate": item.actualenddate,
            "projectid": "",
            "phaseorstep": otype,
            "delflag": "",
            "draftflag": "",
            "fixworkflow": "",
            "methodtemplateid": "",
            "firststartdate": "",
            "firstenddate": "",
            "previousstartdate": "",
            "previousenddate": "",
            "basestartdate": "",
            "baseenddate": "",
            "methodid": "",
            "planworkload": "",
            "baseplanworkload": "",
            "firstplanworkload": "",
            "previosplanworkload": "",
            "actualworkload": "",
            "planincomepercent": "",
            "actualincomepercent": "",
            "phasestatus": "",
            "planworkloadrate": "",
            "actualworkloadrate": "",
            "delayreason": "",
            "strategy": "",
            "remark2": "",
            "actuaincomedate": "",
            "actuacontractpercent": "",
            "phasebudget": "",
            "phaseplanpercent": "",
            "phasecontractfee": "",
            "actualincomefee": "",
            "incomeremark": "",
            "allworkload": ""
          })
          
          console.log("pujian test 5.");
        }


	</script>

</body>
</html>