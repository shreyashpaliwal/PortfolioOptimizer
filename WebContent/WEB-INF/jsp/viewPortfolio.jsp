<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>

div.container {
	width: 100%;
	border: 1px solid gray;
}

header, footer {
	padding: 1em;
	color: white;
	clear: left;
	text-align: center;
}

nav {
	float: left;
	max-width: 160px;
	margin: 0;
	padding: 1em;
}

nav ul {
	list-style-type: none;
	padding: 0;
}

nav ul a {
	text-decoration: none;
}

article {
	margin-left: 200px;
	border-left: 1px solid gray;
	padding: 5em;
	overflow: hidden;
}
</style>
<meta name="generator" content="Google Web Designer 1.6.2.0916">

<style type="text/css" id="gwd-text-style">
p {
	margin: 0px;
}

h1 {
	margin: 0px;
}

h2 {
	margin: 0px;
}

h3 {
	margin: 0px;
}

table, th, td {
	border: 1px solid black;
	border-spacing: 5px;
	border-collapse: collapse;
	text-align: left;
}


tr:nth-child(even){background-color: #f2f2f2}

</style>

<style type="text/css">
    


body {
	transform: perspective(1400px)
		matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
	transform-style: preserve-3d;
	background-image:url(" ${pageContext.request.contextPath}/images/e.jpg");
	background-size: 100% 100%;
}
</style>

</head>
<body>

	<div class="container">
		<div>
			<div style="margin-top: 20px;color: red;">
				<ul type="square">
				<c:forEach items="${errorMessages }" var="msg">
					<li> ${msg} </li>
				</c:forEach>
				</ul>
			</div>

			<table>

				<h2>Cash Balance: ${portfolio.cashBalance } $</h2>
				
				<h2> portfolio ID: ${portfolio.portfolioId }</h2>

				<caption>,

					<h3><u>Portfolio Performance!!</u></h3>

				</caption>

				<tbody>

					<tr>

						<!-- <th>Name</th> -->

						<th>Symbol</th>

						<th>Purchase Price</th>
						<th>Last Price</th>

						<th>Change</th>

						<th>Shares</th>

						<th>Cost basis</th>

						<th>Mkt Value</th>

						<th>Gain</th>

						<th>Gain %</th>

						<th>Overall return</th>

					</tr>
					<c:forEach items="${performanceMatrix}" var="perf">

						<tr>

							<%-- <th>${stock.stockSymbol }</th> --%>

							<td>${perf.stock.stockSymbol}</td>
							<td>
								<c:if test="${perf.stock.stockExchangeType.stockExchangeId == 1}">
								${perf.stock.purchasePrice} $								
								</c:if>
								<c:if test="${perf.stock.stockExchangeType.stockExchangeId == 2}">
								${perf.stock.purchasePrice} $ / <fmt:formatNumber maxFractionDigits="2" groupingUsed="true" value="${perf.stock.purchasePrice * sessionScope.fxRate }" />  &#8377;								
								</c:if>														
							</td>
							<td>
							<c:if test="${perf.stock.stockExchangeType.stockExchangeId == 1}">
								${perf.lastPrice} $								
								</c:if>
								<c:if test="${perf.stock.stockExchangeType.stockExchangeId == 2}">
								${perf.lastPrice} $ / <fmt:formatNumber maxFractionDigits="2" groupingUsed="true" value="${perf.lastPrice * sessionScope.fxRate }" />  &#8377;
																
							</c:if>
							</td>
							<td>${perf.change}</td>

							<td>${perf.stock.shareQuantity}</td>

							<td>${perf.costBasis}</td>

							<td>${perf.marketValue}</td>

							<td>${perf.gain}</td>

							<td>${perf.gainPercentage}</td>

							<!-- <td>${perf.daysGain}</td> -->

							<td>${perf.overallReturn}</td>

						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
		<br/>
		<br/>
			<form action="${pageContext.request.contextPath}/addCash.htm" style="border: 1px;">				
				<div>	
					<label>Add Cash:</label> <input type="text" name="cashAmount"></input>				
				
				<select name="withdraw">
					<option value="false">Deposit</option>
					<option value="true">Withdraw</option>					
				</select>
				
				<input type="hidden" value="${portfolio.portfolioId}"
					name="portfolioId" />
				<input type="submit" value="Submit"></input>
				</div>
				<%-- <input type="hidden" value="false" name="withdraw">
			
				<h3><br>
					<label>Withdraw Cash:</label> <input type="text" name="cashAmount"></input>
					<input type="submit" value="Withdraw"></input><br><br>
				</h3>
				<input type="hidden" value="${portfolio.portfolioId}"
					name="portfolioId" />
				<input type="hidden" value="true" name="withdraw"> --%>
			</form>
	<br/><br/>
	<div>
		<form action="${pageContext.request.contextPath}/BuyShare.htm?portfolioId=${portfolio.portfolioId}">
			<input type="submit" value="BuyShare"></input>
			<input type="hidden" value="${portfolio.portfolioId}"
					name="portfolioId" />
		</form>		

		<form action="${pageContext.request.contextPath}/SellShare.htm?portfolioId=${portfolio.portfolioId}">
			<input type="submit" value="SellShare"></input>
			<input type="hidden" value="${portfolio.portfolioId}"
					name="portfolioId" />
		</form>	
	</div>	
			
	</div>

</body>
</html>

