<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Auction Items</title>
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
	
		<div class="row">
	  		<div class="col-sm-4">
	    		<div class="card">
	      			<div class="card-body">
	        			<h5 class="card-title">${auctionItem.itemName}</h5>
	        			<img class="card-img-top" src="images/laptop.png" alt="Card image cap" width="300" height="300">
	        			 <p class="card-text">$ <%if(request.getParameter("bid").equals("0.0")){
	        				 %> ${auctionItem.price} 
	        				 <%}else{%> ${auctionItem.bid}<%} %> </p>
	        			 
	        			 <p class="card-text">${auctionItem.description}</p>
	      			</div>
	    		</div>
	  		</div>
	  		<div class="col-sm-8">
	    		<div class="card">
	      			<div class="card-body">
	        			<h5 class="card-title">Place a bid!</h5>
	        			
	        			<p>Current bid is: $${auctionItem.bid}</p>
	        			<p>The start date for this item is: ${auctionItem.startTime}</p>
	        			<p>The end date for this item is: ${auctionItem.endTime}</p>
	        			<p class="text-danger">${errorMessage}</p>
	        			
	        			<form action="<%= request.getContextPath() %>/AuctionItem" method="post">
							<div class="form-group">
								<input type="hidden" class="form-control " id="itemID" name="itemID" value="${auctionItem.itemID}">	
								<input type="hidden" class="form-control " id="customerNickName" name="customerNickName" value="${customerNickName}">						
								<input type="text" class="form-control " id="bid" name="bid" placeholder="Place Bid">
							</div>
							<input type="submit" value="Place Bid">
						</form>
	      			</div>
	    		</div>
	  		</div>
		</div>
	</div>
</body>
</html>