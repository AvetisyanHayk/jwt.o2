<%-- 
    Document   : head
    Created on : Oct 10, 2017, 4:34:08 PM
    Author     : Hayk
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="HTML head" pageEncoding="UTF-8"%>
<%@attribute name="showSignOutButton" required="false" type="java.lang.Boolean"%>
<header>
    <nav>
        <ul>
            <li><a href="#" class="print"><i class="fa fa-print"></i> Print</a></li>
        </ul>
        <c:if test="${not empty showSignOutButton and showSignOutButton}">
            <ul class="pull-right">
                <li><a href="?signout=true" class="signout-button"><i class="fa fa-sign-out"></i> <span>Sign-Out</span></a></li>
            </ul>
        </c:if>
    </nav>
    <h1>Oefening 2</h1>
</header>