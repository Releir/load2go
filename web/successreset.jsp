<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<%@ include file="style/header.html" %>
<body style="text-align:center">

<div class="wrapper">
<%@ include file="style/membermenu.jsp" %>
    
    <div class="container">
		<div class="row mt centered">
			<div class="col-lg-6 col-lg-offset-3">
 					<h1>Password changed</h1> 
				<form class="centerform sky-form" action="register.html" method="post">
					<header class="centered"><small>Your password has been changed click 
					<a href="homepage.jsp">here</a> to go back.</small></header>
				</form>
    		</div>
		</div>
	</div>
	<div class="push"></div>
</div> <!-- Wrapper ENd -->		
	
	<%@ include file="style/footer.html" %>
</body>
</html>