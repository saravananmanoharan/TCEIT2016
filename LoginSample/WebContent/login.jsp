<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tdl/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tdl/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/tdl/struts-logic.tld" prefix="logic"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>App-Login</title>
</head>
<body>
	<div style="color: red">
		<html:errors />
	</div>
	<html:form action="/login">
	<table>
		<tr><td>User Name<td><td></td><td><html:text name="LoginForm" property="userName" /></td></tr>
		<tr><td>Password <td><td></td><td><html:password name="LoginForm" property="password" /></td></tr>
		<tr><td></td><td><html:submit value="Login" /></td><td></td></tr>
	</table>
	</html:form>
</body>
</html>