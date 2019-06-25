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
<link rel="stylesheet" href="app.css">
<title>Your Configuration</title>
</head>
<body>
<div class="container">

<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">Option</th>
      <th scope="col">Name</th>
      <th scope="col">Price</th>
    </tr>
  </thead>
  
<%
Automobile car = (Automobile) request.getSession().getAttribute("automobile");
Choice carChoice = (Choice) request.getSession().getAttribute("carChoice"); 
%>
	<tbody>
	    <tr>
	      <th scope="row">Vehicle Name</th>
	      <td><%= car.getName() %></td>
	      <td>$<%= car.getBasePrice() %></td>
	    </tr>
  	</tbody>
<% 
for(int i = 0; i < car.getOpset().size(); i++) { %>
	<tbody>
	    <tr>
	      <th scope="row"><%= car.getOpsetName(i) %></th>
	      <td><%= car.getChoiceName(i) %></td>
	      <td>$<%= car.getChoicePrice(i) %></td>
	    </tr>
  	</tbody>
<% } %>
<tbody>
    <tr>
      <th scope="col">Total Price</th>
      <td scope="row"></td>
      <td scope="row">$<%= car.getTotalPrice()%></td>
      </tr>
</tbody>
</table>
</div>
	<div class="container">
		<form class="form" action="index.jsp" method="POST">
			<button type="submit" class="btn btn-primary my-1">Restart?</button>
		</form>
	</div>


<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>