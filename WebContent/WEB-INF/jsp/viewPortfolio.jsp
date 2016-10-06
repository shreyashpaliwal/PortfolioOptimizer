<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
</style>

<style type="text/css">
html, body {
	width: 100%;
	height: 100%;
	margin: 0px;
}

body {
	transform: perspective(1400px)
		matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
	transform-style: preserve-3d;
	background-color: transparent;
}
</style>

</head>
<body>

	<div class="container">
		<div>

			<table>

				<h2>Cash Balance: ${portfolio.cashBalance }</h2>

				<caption>

					<h3>Portfolio Performance!!</h3>

				</caption>

				<tbody>

					<tr>

						<!-- <th>Name</th> -->

						<th>Symbol</th>

						<th>Last Price</th>

						<th>Change</th>

						<th>Shares</th>

						<th>Cost basis</th>

						<th>Mkt Value</th>

						<th>Gain</th>

						<th>Gain %</th>

						<th>Day's gain</th>

						<th>Overall return</th>

					</tr>

					<c:forEach items="${performanceMatrix}" var="perf">
						<tr>

							<%-- <th>${stock.stockSymbol }</th> --%>

							<td>${perf.stock.stockSymbol}</td>

							<td>${perf.lastPrice}</td>
							<td>${perf.change}</td>

							<td>${perf.stock.shareQuantity}</td>

							<td>${perf.costBasis}</td>

							<td>${perf.marketValue}</td>

							<td>${perf.gain}</td>

							<td>${perf.gainPercentage}</td>

							<td>${perf.daysGain}</td>

							<td>${perf.overallReturn}</td>

						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
		</article>

		<div>
			<form action="${pageContext.request.contextPath}/addCash.htm">
				<h3><br>
					<label>Add Cash:</label> <input type="text" name="cashAmount"></input>
					<input type="submit" value="deposit"></input>
				</h3>
				<input type="hidden" value="${portfolio.portfolioId}"
					name="portfolioId" />
			</form>
			
			<form action="${pageContext.request.contextPath}/removeCash.htm">
				<h3><br>
					<label>Withdraw Cash:</label> <input type="text" name="cashAmount"></input>
					<input type="submit" value="Withdraw"></input>
				</h3>
				<input type="hidden" value="${portfolio.portfolioId}"
					name="portfolioId" />
			</form>

			<a
				href="${pageContext.request.contextPath}/BuyShare.htm?portfolioId=${portfolio.portfolioId}">Buy
				Share</a>
				
			
		</div>
			<a
				href="${pageContext.request.contextPath}/SellShare.htm?portfolioId=${portfolio.portfolioId}"><br>Sell
				Share</a>
	</div>

</body>
</html>

