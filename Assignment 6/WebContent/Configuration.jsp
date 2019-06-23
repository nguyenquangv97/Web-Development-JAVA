<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.*" import="adapter.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="CarList.css">
<title>Select Car</title>
</head>
<body>

	<div class="jumbotron">
		<h1 class="display-4 text-light">Configure Your Car</h1>
	</div>


	<div class="container">
		<form class="form" action="SetChoice" method="POST">

			<%
				Automobile car = (Automobile) request.getSession().getAttribute("automobile");
				for (int i = 0; i < car.getOpset().size(); i++) {
			%>
					<label class="my-1 mr-2" for="opsetName"><%=car.getOpsetName(i)%></label>
					<select class="custom-select my-1 mr-sm-2" name=<%="set"+i%>>
			<%
					for (String option : car.getEachOptionProps(i)) {
			%>
						<option><%=option%></option>
				<%
					}
				%>
					</select>
			<%
				}
			%>
			<button type="submit" class="btn btn-primary my-1">Submit</button>
		</form>
	</div>


	<!-- generate this using Option Set name inside JSP tag -->





	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous" type="text/javascript"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous" type="text/javascript"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous" type="text/javascript"></script>
</body>
</html>