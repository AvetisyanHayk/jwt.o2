<%-- 
    Document   : index
    Created on : Oct 10, 2017, 1:06:41 PM
    Author     : Hayk
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="o2" uri="http://jwt.o2/tags" %>
<!DOCTYPE html>
<html>
    <o2:head title="Sign-In"/>
    <body class="signin">
        <header>
            <h1>Oefening 2</h1>
        </header>
        <main>
            <form method="post">
                <fieldset>
                    <legend><i class="fa fa-code"></i> Sign-In</legend>
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" placeholder="Username" required/>
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" placeholder="Password" required/>
                </fieldset>
                <button type="submit" class="button"><i class="fa fa-sign-in"></i> Sign-In</button>
                <a href="<c:url value='/register.htm'/>" class="button"><i class="fa fa-fw fa-user"></i> Register</a>
                <br/>
                <a href="<c:url value='/partims.htm'/>" target="_blank"><i class="fa fa-fw fa-external-link-square"></i> See all our partims</a>
            </form>
        </main>
        <o2:footer/>
    </body>
</html>
