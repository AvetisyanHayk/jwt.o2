<%-- 
    Document   : index
    Created on : Oct 10, 2017, 1:06:41 PM
    Author     : Hayk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="o2" uri="http://jwt.o2/tags"%>
<!DOCTYPE html>
<html>
    <o2:head title="Registration Completed"/>
    <body>
        <header>
            <h1>Oefening 2</h1>
        </header>
        <main class="container">
            <p>Registration completed. Now you can
                <a href="<c:url value='/signin.htm'/>" class="button"><i class="fa fa-fw fa-sign-in"></i> Sign-In</a></p>
        </main>
        <o2:footer/>
        <o2:tail/>
    </body>
</html>
