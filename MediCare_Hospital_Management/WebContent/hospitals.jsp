<%@page import="com.hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/hospital.js"></script>

</head>
<body>

<div class="container">
	<div class="row" >
		<div class="col-6">
			<h1>Hospital Management V10.1</h1>
			
			<form id="formHospital" name="formHospital" method="post" action="hospitals.jsp">
								 
				Hospital name:
				<input id="hospitalName" name="hospitalName" type="text" class="form-control form-control-sm">
				<br>
				
				Hospital address:
				<input id="hospitalAddress" name="hospitalAddress" type="text" class="form-control form-control-sm">
				<br>
				 
				<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
				<input type="hidden" id="hidHospitalIDSave" name="hidHospitalIDSave" value="">
			</form>
			
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			<br>

			<div id="divHospitalGrid">
				<%
					hospital hosObj = new hospital();
					out.print(hosObj.viewHospital());
				%>
			</div>
		</div>
	</div>
</div>

</body>
</html>