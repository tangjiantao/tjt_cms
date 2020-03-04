<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resource/css/bootstrap.css">
<script type="text/javascript" src="resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="resource/js/bootstrap.min.js"></script>
<script>
	$(function(){
		$.post("article/selectsChannel.do",function(arr){
			for(var i in arr){					
				$("[name='ch.id']").append("<option value='"+arr[i].id+"'>"+arr[i].name+"</option>");
			}
		},"json")
		$("[name='ch.id']").change(function(){
			$("[name='ca.id'] option:gt(0)").remove();
			var channel_id=this.value;
			$.post("article/selectsCategory.do",{"id":channel_id},function(arr){
				for(var i in arr){
					$("[name='ca.id']").append("<option value='"+arr[i].id+"'>"+arr[i].name+"</option>");
				}
			},"json")
		})
	})
	$("[name=myFiles]").change(function(){
		if(typeof (FileReader) !="undefined"){
			var show=$("#pic-show");
			show.html("");
			var regex=/^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp)$/;
			$($(this)[0].files).each(function(){
				var file=$(this);
				if(regex.test(file[0].name.toLowerCase())){
					var reader=new FileReader();
					reader.onload=function(e){
						show.append("<img src='"+e.target.result+"' style='height:100px;width:100px;'/>");
						show.append("<textarea name='myMessages' style='height:100px;width:100px;'></textarea>");
						show.append("<br/>");
					}
					reader.readAsDataURL(file[0]);
				}else{
					alert(file[0].name+"不是一个合法的图片");
					show.html("");
					return false;
				}
			})
		}else{
			alert("当前浏览器不支持图片预览");
		}
	})
	function add(){
		//序列化表单数据 带文件
		var form=new FormData($("#f1")[0]);
		//ajax提交
		$.ajax({
			//告诉jquery不要去处理发送的数据
			processData:false,
			//告诉jquery不要去设置Content-Type请求头
			contentType:false,
			cache:false,
			url:"article/addArticleImage",
			async:false,
			data:form,
			type:"post",
			success:function(flag){
				if(flag){
					alert("发布图片成功");
				}else{
					alert("发布图片失败");
				}
			}
		})
	}
</script>
</head>
<body>
	<form name="example" id="f1">
		<input type="hidden" name="u.id" value="${id}">
		文章标题:
		<input type="text" name="title" class="form-control"><br/>
		<div class="col-cs-10">
			<input type="file" accept="image/*" name="myFiles" multiple="multiple" class="up-file">
			<div id="pic-show"></div>
		</div>
		<font color="red">注：须同时选中多个文件一起上传</font>
		<br/>
		<div class="form-inline">
			栏目:<select name="ch.id" class="form-ccontrol">
					<option value="">---请选择栏目---</option>
				</select>&nbsp;&nbsp;
			分类:<select name="ca.id" class="forn-control">
					<option value="">---请选择分类---</option>
				</select>
		</div>
		<br/>
		标题图片:
		<input type="file" name="titleImage">
		<br>
		<button class="btn btn-primary" onclick="add()">发布</button>
	</form>
</body>
</html>