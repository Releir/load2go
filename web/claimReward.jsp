<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ include file="style/header.html" %>
<body style="text-align:center">
	
<div class="wrapper">
<%@ include file="style/membermenu.jsp" %>

	<s:if test="%{#session.get('currentUser')==null}">
		<s:if test="%{#session.get('user_details')!=null}">
			<c:redirect url="login.jsp"></c:redirect>
		</s:if>
	</s:if>
    
    <div class="container">
		<div class="sidecol mt">
			<div class="col-md-3">
				<h2>Payment Gateways</h2>	
				<form>
					<h4> Credit Card</h4><br>
					<a href="reload_wallet_cc.jsp" class="btn btn-primary">Reload Now</a>
					<br>
					<h4> Paypal</h4><br>
					<a href="" class="btn btn-primary">Reload Now</a>
					<br>
				</form>	
    		</div>
    		<div class="col-md-9">
    			<div class="account-box">
    				<div class="blue-bg">
    					<div class="row margin-bottom-10">
    						<div class="col-xs-3">
                        		${user_details.name}
                    		</div>
		                    <div class="col-xs-3">
		                       PHP ${user_details.balance}
		                    </div>
		                        <div class="col-xs-2">
		                          ${user_details.reward}
		                        </div>
		                    <div class="col-xs-4">
		                         ${user_details.email}
		                    </div>
    					</div><!-- row magin-bottom-8 -->
    					<div class="row margin-bottom-1">
    						<div class="col-xs-3">
                            	<small>
                            		User
                            	</small>
		                    </div>
		                    <div class="col-xs-3">
		                        <small>
		                            BALANCE
		                        </small>
		                    </div>
		                        <div class="col-xs-2">
		                            <small>
		                               REWARD POINTS
		                            </small>
		                        </div>
		                    
		                    <div class="col-xs-4">
		                        <small>
		                            EMAIL
		                        </small>
		                    </div>
    					</div><!-- row magin-bottom-1-->
    				</div><!-- blue-bg -->
    			</div><!-- account-box -->
    			<ul class="breadcrumb">
					<li><a href="homepage.jsp">My Account</a></li>
					<li class="active">Claim Reward&nbsp;</li>
				</ul>
    			<hr>
    			<div class="headline">
    				<h2>Claim Reward</h2>
    			</div>
    				<form action="process_claim" method="post">
    					<div class="centerInner">
							<s:if test="hasActionErrors()">
	   							<div class="alert alert-danger">
	      							<s:actionerror/>
	   							</div>
							</s:if>
	    					<table>
							<tr>
							<td>Amount:  PHP </td>
							<td><select class="form-control" name="amount">
								<option value="500">500 </option>
								<option value="1000">1000 </option>
								<option value="1500">1500 </option>
								<option value="2000">2000 </option>
								<option value="2500">2500 </option>
								<option value="3000">3000 </option>
								<option value="3500">3500 </option>
								<option value="4000">4000 </option>
								<option value="4500">4500 </option>
								<option value="5000">5000 </option>
							</select></td>
						</tr>
						<tr>
							<td> <input class="btn btn-primary btn-sm" type="submit" value="Confirm"> </td>
						</tr>
					
						</table>
					</div>
				</form>
    		</div><!-- col-md-9 -->
		</div><!-- sidecol -->
	</div><!-- Container -->
	<div class="push"></div>
</div> <!-- Wrapper ENd -->
	
	<%@ include file="style/footer.html" %>
	
</body>
</html>