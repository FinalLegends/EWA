<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="js/bootstrap.js" rel="javascript">
<title>Weather Alert</title>
</head>
<body>
	<!-- Background Image -->
	<div class="fadein">
		<img id="f2" src="img/bg.jpg"> <img id="f1"
			src="img/bg_sunset.jpg">
	</div>

	<!-- Navigation Bar -->
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Weather Alert</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="about.html">About</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<div align="center" class="jumbotron">
		<h2>Electronic Weather Alerts: Get notified when it's too hot or
			too cold outside.</h2>
	</div>

	<!-- Prompt -->
	<div class="whereLive" align="center">
		<img src="img/wherelive copy.png" onmousedown="mouseDown()" onmouseup="mouseUp()" />
	</div>

	<!-- Input field -->
	<div align="center">
		<form id="form" class="navbar-form" role="search" action="sendMessage" method="post">
		  <div class="form-group">
		    <div class="input-group">
  				<span class="input-group-addon"><span class="glyphicon glyphicon-home"></span></span>
 				<input type="text" id="zipCode" name="zipCode" class="form-control" placeholder="Enter Zip Code">
			</div><br>
		    <div class="input-group">
  				<span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
 				<input type="text" id="emailID" name="emailID" class="form-control" placeholder="Enter Email">
			</div><br>
		    <div class="input-group">
  				<span class="input-group-addon"><span class="glyphicon glyphicon-cloud-upload"></span></span>
 				<input type="text" id="highTemp" name="highTemp" class="form-control" placeholder="Enter High Temp">
			</div><br>
		    <div class="input-group">
  				<span class="input-group-addon"><span class="glyphicon glyphicon-cloud-download"></span></span>
 				<input type="text" id="lowTemp" name="lowTemp" class="form-control" placeholder="Enter Low Temp">
			</div><br><br>
			<div id="alert_submit" class="btn btn-default">Submit</div>
			<br><span class="success">${messages.success}</span>
		  <!-- <button type="submit" id="alert_submit" value="sendMessage" class="btn btn-default">Submit</button> -->
		</form>
	</div>

	<!-- Display -->
	<div id="weather" align="center"></div>

	<div id="dialog" style="color: white" align="center" class="sidePanel"></div>

	<div id="danceleft">
		<img width="100%;" src="img/dance1.gif" /><br />
		<img width="100%;" src="img/dance2.gif" />
	</div>
	<div id="danceright">
		<img width="100%;" src="img/dance3.gif" /><br />
	</div>
	
	<audio loop id="dudududu" src="audio/sand.mp3" preload="auto"></audio>



	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
	<script src="js/alert.js"></script>
	<script type="text/javascript" src="js/jquery.stylesheet.js"></script>
	<script> 
		function mouseDown() {
			document.getElementById('dudududu').play();
			document.getElementById('dialog').style.display = 'none';
			document.getElementById('danceleft').style.display = 'block';
			document.getElementById('danceright').style.display = 'block';
		}
		function mouseUp(){
			document.getElementById('dudududu').pause();
			document.getElementById('dialog').style.display = 'block';
			document.getElementById('danceleft').style.display = 'none';
			document.getElementById('danceright').style.display = 'none';
		}
	</script>
	

</body>
</html>