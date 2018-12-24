<#import "/spring.ftl" as spring/>

<html>
<head>
<title>Your Contact List</title>
<link rel="stylesheet" href="text/css" href="<@spring.url '/css/style.css'/>"/>
</head>
<body>
<h3>Your Contact List</h3>
<a href="addContact">Add Contact</a>
<br><br>
<div>
<table border="1">
<tr>
<th>First Name</th>
<th>Last Name</th>
</tr>
<#list contacts as contact>
<tr>
<td>${contact.firstName}</td>
<td>${contact.lastName}</td>
</tr>
</#list>
</table>
</div>
</body>
</html>