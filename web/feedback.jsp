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
				<h1>We'd like to hear from you!</h1>
				<h3>and we also might reward you for it</h3>
				
				<form class="centerform sky-form" action="feedback" method="post">
					<header class="centered">Send a feedback!<br/></header>

						<fieldset>
		                    <section>
		                        <label class="label">
		                            Subject
		                        </label>
		                        <label class="input">
		                            <input name="subject" type="text" value=""  required/>
		                        </label>
		                    </section>
		                    <section>
		                        <label class="label">
		                            Description
		                        </label>
		                        <label class="textarea">
		                            <textarea style="resize:none" cols="40" name="feedback" rows="30" required>
										</textarea>
		                        </label>
		                    </section>
		                </fieldset>
		                <footer>
		                    <button type="submit" class="btn btn-primary">Send FeedBack</button>
		                </footer>	
				</form>
    		</div>
		</div>
	</div>
	<div class="push"></div>
</div> <!-- Wrapper ENd -->	

	<%@ include file="style/footer.html" %>
</body>
</html>