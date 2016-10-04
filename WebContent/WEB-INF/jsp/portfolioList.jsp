<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Portfolio List</title>
</head>
<body>
	<div>
		<form action="#">
			<h1>Hello ${sessionScope.investor.firstName }.</h1>
			<p>Please select from the list of portfolios below.</p>
			<ul>
				<c:forEach items="${sessionScope.investor.portfolios}"
					var="portfolio">
					<li><a href="${pageContext.request.contextPath}/getPortfolioDetails.htm?portfolioId=${portfolio.portfolioId}">${portfolio.portfolioName }</a></li>
				</c:forEach>
			</ul>
		</form>
	</div>

</body>
</html>