<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<body>
	<form action="">
		<table>
			<tr>
				<td>
				select share:
				</td>
				<td>
					<select>
						<option value="FB">FB</option>
						<option value="GOOGL">GOOGL</option>
						<option value="AAPL">AAPL</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td>
					Quantity:
				</td>
				<td>
					<input type="text" name = "shareQuantity">
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
				<td>
					<input type="submit" value="Sell Share" name="sellShare">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>