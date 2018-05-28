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
					<a href="reload_wallet.jsp" class="btn btn-primary">Reload Now</a>
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
					<li><a href="reload_wallet.jsp">ReloadWallet(PP)</a></li>
					<li class="active">Confirmation(PP)&nbsp;</li>
				</ul>
    			<hr>
    			<div class="headlineconfirm">
    				<h2>Transaction Confirmation</h2>
    			</div>
    				<table class="table table-striped">
				        <thead>
				            <tr>

				                <th>Description</th>
				                <th>Amount</th>

				            </tr>
				        </thead>
				        <tbody>
				                <tr>
				                    <td>RELOAD WALLET BALANCE</td>
				                    <td class="active">
				                        PHP ${wallet_transaction.amount}
				                    </td>
				                </tr>

							 <tr>
							 <td>
				        <form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post" target="_blank">
							<input type="hidden" name="cmd" value="_xclick">
							<input type="hidden" name="business" value="adventjava@gmail.com">
							<input type="hidden" name="lc" value="US">
							<input type="hidden" name="button_subtype" value="services">
							<input type="hidden" name="no_note" value="1">
							<input type="hidden" name="no_shipping" value="1">
							<input type="hidden" name="currency_code" value="PHP">
							<input type="hidden" name="amount" value="${wallet_transaction.amount}">
							<input type="hidden" name="item_name" value="LOAD WALLET">
							<input type="hidden" name="quantity" value="1">
							<input type="hidden" name="cancel_return" value="${CANCEL_PAYPAL}">
							<input type="hidden" name="return" value="${RETURN_PAYPAL}">
							<input type="hidden" name="bn" value="PP-BuyNowBF:btn_paynowCC_LG.gif:NonHosted">
							<input type="image" src="https://www.paypalobjects.com/en_US/i/btn/btn_paynowCC_LG.gif" name="submit" alt="PayPal - The safer, easier way to pay online!">
							<img alt="" border="0" src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1">
							</form></td>
				       </tr> 
				        </tbody>
				       
				    </table>
    			
    		</div><!-- col-md-9 -->
		</div><!-- sidecol -->
	</div><!-- Container -->
	<div class="push"></div>
</div> <!-- Wrapper ENd -->	
	
	<%@ include file="style/footer.html" %>
</body>
</html>