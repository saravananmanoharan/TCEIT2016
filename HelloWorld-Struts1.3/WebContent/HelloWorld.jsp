<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tdl/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tdl/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/tdl/struts-logic.tld" prefix="logic"%>
<html>
<title>Hello World!</title>
<html:form action="/helloWorld">
<bean:define id="helloWorld" name="helloWorldForm" type="edu.tce.it.form.HelloWorldForm"/>
<bean:write name="helloWorld" property="message"/>
</html:form>
</html>