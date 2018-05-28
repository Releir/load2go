<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<%@ include file="style/header.html" %>
<body style="text-align:center">

<div class="wrapper">
<%@ include file="style/guestmenu.jsp" %>

    <div class="container">
		<div class="row mt centered">
			<div class="col-lg-6 col-lg-offset-3">
				
				<form class="centerform sky-form">
					
						<h1>Congratulations..</h1>
						<header class="centered">An activation message was sent to <i>${regEmail}</i><br>
						<small>Click <a href="login.jsp">here</a> to login.</small></header>		
					
				</form>
    		</div>
		</div>
	</div>
	<div class="push"></div>
</div> <!-- Wrapper ENd -->	

	<%@ include file="style/footer.html" %>
</body>
</html>