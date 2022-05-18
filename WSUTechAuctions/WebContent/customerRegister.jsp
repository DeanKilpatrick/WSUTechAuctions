<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" 
integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" 
crossorigin="anonymous">


</head>
<body>
	<div class="container bg-color">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  		<a class="navbar-brand" href="/WSUTechAuctions/Index">WSU Tech Auctions</a>
	  		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    		<span class="navbar-toggler-icon"></span>
	  		</button>
	
		  	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		    	<ul class="navbar-nav mr-auto">
		      		<li class="nav-item active">
		        		<a class="nav-link" href="/WSUTechAuctions/Index">Home <span class="sr-only">(current)</span></a>
		      		</li>
		      		<li class="nav-item">
		        		<a class="nav-link" href="/WSUTechAuctions/AddAuctionItem">Add Auction Item</a>
		      		</li>
		      		<li class="nav-item">
		        		<a class="nav-link" href="/WSUTechAuctions/DisplayAuctionItems">Auction Items</a>
		      		</li>
		    	</ul>
		    	<span>
		    	<%try{
					if(session.getAttribute("customerNickName") == null){ %>
						<a class="btn btn-sm btn-outline-secondary" 
						href="/WSUTechAuctions/Register">Register</a>
						<a class="btn btn-sm btn-outline-secondary" style="margin-left: 10px" 
						href="/WSUTechAuctions/Login">Login</a>
					<%}else{ %>
						<a>Welcome ${customerNickName}</a>
						<a class="btn btn-sm btn-outline-secondary" style="margin-left: 10px" 
						href="/WSUTechAuctions/Logout">Logout</a>
					<%}
				}catch(Exception e){
					e.printStackTrace();
				}%>
		    	</span>
			</div>
		</nav>
	</div>
	<div align="center">
		<h1>Customer Register Form</h1>
		<div class="container">
			<div class="card bg-light">
				<article class="card-body mx-auto" style="max-width: 400px">
					<form action="<%= request.getContextPath() %>/Register" method="post">
						<div class="form-group">
							<input type="text" class="form-control " id="customerNickName" name="customerNickName" placeholder="Nickname">
						</div>
						<div class="form-group">
							<input type="text" class="form-control " id="password" name="password" placeholder="Password">
						</div>
						<div class="form-group">
							<div class="form-row">
								<div class="col">
									<input type="text" class="form-control" id="firstName" name="firstName" placeholder="First name">
								</div>
								
								<div class="col">
									<input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last name">
								</div>
							</div>
						</div>
						<div class="form-group">
							<input type="text" class="form-control " id="phoneNumber" name="phoneNumber" placeholder="Phone Number">
						</div>
						<div class="form-group">
							<input type="text" class="form-control " id="email" name="email" placeholder="Email Address">
						</div>
						<div class="form-group">
							<input type="text" class="form-control " id="address" name="address" placeholder="Address">
						</div>
						<div class="form-group">
							<input type="text" class="form-control " id="postcode" name="postcode" placeholder="Post code">
						</div>
						<p class="text-danger">${errorMessage}</p>
						<input type="submit" value="Register">
					</form>
				</article>
			</div>
		</div>
	</div>
</body>
</html>