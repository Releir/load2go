<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
				<h1>Register a LoadToGo Account</h1>
				<h3>Join in and have fun</h3>
				
				<form class="sky-form" action="register" method="post">
					<header class="centered"><i><small>Please fill out the  fields</small></i></header>
						<fieldset>
							<section>
								 <s:if test="hasActionErrors()">
   									<div class="alert alert-danger">
      									<s:actionerror/>      									
   									</div>
								</s:if>
		                        <label class="label">
		                            First Name
		                        </label>
		                        <label class="input">
		                            <input name="reg.firstName" type="text" value="${reg.firstName}" />
		                        </label>
		                    </section>
		                    <section>
		                        <label class="label">
		                            Last Name
		                        </label>
		                        <label class="input">
		                            <input name="reg.lastName" type="text" value="${reg.lastName}"/>
		                        </label>
		                    </section>
		                    <section>
		                        <label class="label">
		                            Secret Question
		                        </label>
		                        <label class="input">
		                           <select class="form-control" name="reg.secquest"> 
										<option value="Whats your dogs name">Whats your dog's name?</option> 
				 						<option value="How many cats did you own">How many cats did you own?</option> 
								   </select>
		                        </label>
		                    </section>
		                    <section>
		                        <label class="label">
		                           Secret Answer
		                        </label>
		                        <label class="input">
		                            <input name="reg.secans" type="text" value="${reg.secans}" />
		                        </label>
		                    </section>
		                    <section>
		                        <label class="label">
		                            Email
		                        </label>
		                        <label class="input">
		                            <input name="reg.email" type="email" value="${reg.email}"  />
		                        </label>
		                    </section>
		                    <section>
		                        <label class="label">
		                           Password
		                        </label>
		                        <label class="input">
		                            <input name="reg.password" type="password" />
		                        </label>
		                    </section>
		                     <section>
		                        <label class="label">
		                           Confirm Password
		                        </label>
		                        <label class="input">
		                            <input name="reg.confPass" type="password" />
		                        </label>
		                    </section>
		                    <section>
							<label class="label">
		                            CAPTCHA VALIDATION
		                    </label>
							<img id="cptchaimg" class="LBD_CaptchaDiv" src="<c:url value="simpleCaptcha.png" />"><br><br>
							<label class="label">
		                        	Answer
		                    </label>
		                    <label class="input">
								<input type="text" name="reg.answer" />
							</label>						
							</section>
		                </fieldset>
		                <footer>
		                    <button type="submit" class="btn btn-primary">Register</button>
		                </footer>
		                <footer>
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