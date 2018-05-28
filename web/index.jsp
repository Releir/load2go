<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="style/header.html" %>
<%@ include file="style/indexmenu.jsp" %>
 <body id="page">
	<div id="headerwrap">
		<div class="container">
		    <ul class="cb-slideshow removebullets">
            <li><span>Image 01</span></li>
            <li><span>Image 02</span></li>
            <li><span>Image 03</span></li>
            <li><span>Image 04</span></li>
            <li><span>Image 05</span></li>
            <li><span>Image 06</span></li>
        </ul>
			<div class="row">
				<div class="col-lg-6">
					<img class="fiximagelogo" src="assets/img/L2G.png"/>
					<h1>Load anywhere <br/>wherever you go</h1>
				</div><!-- /col-lg-6 -->
				<div class="col-md-6">
				</div><!-- /col-lg-6 -->
				
			</div><!-- /row -->
		</div><!-- /container -->
	</div><!-- /headerwrap -->
	
		<div class="container">
		<div class="row mt centered">
			<div class="col-lg-6 col-lg-offset-3">
				<h1>LoadToGo Wants<br/>You To Be Happy.</h1>
				<h3>To familiarize with our services click on the pictures below to show some infos about our services</h3>
			</div>
		</div><!-- /row -->
		
		<div class="row mt centered">
			<div class="col-lg-4">
				<a href="register.jsp" target="_blank">
				<img src="assets/img/ser6.png" width="180" alt="">
				</a>
				<h4>1 - Register</h4>
				<p>Register a FREE LoadToGO Account</p>
			</div><!--/col-lg-4 -->

			<div class="col-lg-4">
				<a href="reloadcccinstruc.jsp" onclick="window.open(this.href,'targetWindow', 'toolbar=no, location=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=500, height=500'); return false;">
				<img src="assets/img/ser5.png" width="180" alt="">
				</a>
				<h4>2 - Reload</h4>
				<p>Reload your Load2Go Wallet</p>

			</div><!--/col-lg-4 -->

			<div class="col-lg-4">
				<a href="purchaseloadreward.jsp" onclick="window.open(this.href,'targetWindow', 'toolbar=no, location=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=500, height=500'); return false;">
				<img src="assets/img/ser4.png" width="180" alt="">
				</a>
				<h4>3 - Choose & Be Rewarded</h4>
				<p>Purchase Load and be rewarded!</p>

			</div><!--/col-lg-4 -->
		</div><!-- /row -->
	</div><!-- /container -->
		
	<div class="container"><hr/></div>
	
	<%@ include file="style/footer.html" %>

  </body>
</html>