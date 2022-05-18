<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
	<div class="container mt-4">
	<hr class="border mt-3">
		<div class = "row">
		    <c:forEach items="${auctionItems}" var="auctionItem">
		    	<c:url var="loadAuctionItem" value="AuctionItem">
		    		<c:param name="itemID" value="${auctionItem.itemID}"/>
            		<c:param name="itemName" value="${auctionItem.itemName}"/>
            		<c:param name="price" value="${auctionItem.price}"/>
            		<c:param name="description" value="${auctionItem.description}"/>
            		<c:param name="startTime" value="${auctionItem.startTime}"/>
            		<c:param name="endTime" value="${auctionItem.endTime}"/>
            		<c:param name="bid" value="${auctionItem.bid}"/>
        		</c:url>
				<div class="col-3">
					<img class="border" alt="" src="images/laptop.png" width="200" height="200">
					<div>
						<a href="${loadAuctionItem}"><c:out value="${auctionItem.itemName}" /></a>			     		
						<div>
							
				     		<fmt:formatNumber value="${auctionItem.price}" type="currency" currencyCode="AUD" />
				    	</div> 	
					</div>
				</div>
		    </c:forEach>
		   
		</div>
		<hr class="border mt-3">
	</div>
</body>
</html>