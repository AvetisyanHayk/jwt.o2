<%-- 
    Document   : head
    Created on : Oct 10, 2017, 4:34:08 PM
    Author     : Hayk
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="HTML head" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true" type="java.lang.String"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/screen.css'/>" type="text/css"/>
    <title>${title}</title>
</head>