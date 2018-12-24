<#import "/spring.ftl" as spring/>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8"/>
<title>Add Contact</title>
<link rel="stylesheet" type="text/css" href="<@spring.url 'css/style.css'/>"/>
</head>
<body>
<#if errorMessages??>
<#list errorMessages as errorMessage>
<div style="color:red;font-style:italic;">
${errorMessage}
</div>
</#list>
</#if>
<div>
<fieldset>
<legend>Add Contact</legend>
<form name="contact" action="" method="POST">
First Name: <@spring.formInput "contactForm.firstName" "" "text"/><br/>
Last Name: <@spring.formInput "contactForm.lastName" "" "text"/><br/>
<input type="submit" value="Create"/>
</form>
</fieldset>
</div>
</body>
</html>