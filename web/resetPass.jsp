<%@ page language="java" contentType="charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@ include file="style/header.html" %>
<body style="text-align:center">
	
	<div class="wrapper">
	<%@ include file="style/guestmenu.jsp" %>
	
	<s:if test="%{#session.get('currentUser')!=null}">
		<s:if test="%{#session.get('user_details')!=null}">
			<c:redirect url="homepage.jsp"></c:redirect>
		</s:if>
	</s:if>
	
	<div class="container">
		<div class="row mt centered">
			<div class="col-lg-6 col-lg-offset-3">
				<h1>LoadToGo is for everyone</h1>
				<h3>Join in and have fun</h3>
				
				<form class="centerform sky-form" action="resetPass" method="post">
					<header class="centered">We're here to reset your password<br/></header>
						<fieldset>
							<section>
								 <s:if test="hasActionErrors()">
   											<div class="errors">
   												<p></p>
      											<s:actionerror/>
      											<p></p>
   											</div>
								</s:if>
		                        <label class="label">
		                            Code 
		                        </label>
		                        <label class="input">
		                            <input name="user.code" type="text"/>
		                        </label>
		                    </section>
		                    <section>
		                        <label class="label">
		                            Password 
		                        </label>
		                        <label class="input">
		                            <input name="user.password" type="password"/>
		                        </label>
		                    </section>
		                    <section>
		                        <label class="label">
		                            Confirm Password 
		                        </label>
		                        <label class="input">
		                            <input name="user.confPass" type="password"/>
		                        </label>
		                    </section>
		                </fieldset>
		                <footer>
		                    <button type="submit" class="btn btn-warning btn-lg">Change it</button>
		                </footer>	
				</form>

    		</div>
    	</div>
	</div>
	<div class="push"></div>
		
	</div><!-- WrapperEnd -->
	<%@ include file="style/footer.html" %>

</body>
</html>