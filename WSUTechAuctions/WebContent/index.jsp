<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WSU Tech Auctions</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" 
integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" 
crossorigin="anonymous">
</head>
<body>
	<div id="container" class="container">
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
	
	
	<h1 class="text-center text-white d-none d-lg-block site-heading bg-dark">
		Welcome To Western Sydney Auctions
	</h1>
	<img src="images/auction_image.jpg" width="100%" height="500">
	
	</div>
	
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>