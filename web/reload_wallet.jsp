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
					<li class="active">ReloadWallet(PP)&nbsp;</li>
				</ul>
    			<hr>
    			<div class="headlinecc">
    				<h2>Reload Wallet(PP)</h2>
    			</div>
    				<form action="reloadpp" method="post">
    				<div class="centerInner">
    					<table>
							<tr>
								<td>Amount:  PHP &nbsp; </td>
								<td><select class="form-control" name="amount">
									<option value="10">10 </option>
									<option value="15">15 </option>
									<option value="20">20 </option>
									<option value="25">25 </option>
									<option value="30">30 </option>
									<option value="50">50 </option>
									<option value="60">60 </option>
									<option value="100">100 </option>
									<option value="150">150 </option>
									<option value="200">200 </option>
									<option value="300">300 </option>
									<option value="500">500 </option>
									<option value="1000">1000 </option>
									<option value="1500">1500 </option>
									<option value="2000">2000</option>
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