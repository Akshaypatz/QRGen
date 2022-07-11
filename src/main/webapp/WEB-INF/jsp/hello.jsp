<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Hello ${name}!</title>
</head>
<body>

	<form method="POST">
		<label for="fname">First name:</label>
		<br> 
		<input type="text" id="fname" name="fname">
			<br>
			 <label for="lname">Last name:</label>
			<br>
			 <input type="text" id="lname" name="lname"> 
			 <br> 
			 <label for="product">product:</label>
			<br> 
			<input type="text" id="product" name="product">
			<br> 
			<label for="amount">amount:</label>
			<br>
		<input type="text" id="amount" name="amount">
		<br> 
		<h3>With Amount</h3>
		<input type="submit" formaction="computeQRwithAmount" />
		<h3>Without Amount</h3>
		<input type="submit" formaction="computeQRwithoutAmount" />

	</form>
</body>
</html>