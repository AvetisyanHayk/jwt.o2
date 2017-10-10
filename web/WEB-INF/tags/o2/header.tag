<%-- 
    Document   : head
    Created on : Oct 10, 2017, 4:34:08 PM
    Author     : Hayk
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="HTML head" pageEncoding="UTF-8"%>
<%@attribute name="showSignOutButton" required="false" type="java.lang.Boolean"%>
<%@attribute name="activeMenuItemId" required="false" type="java.lang.Integer"%>
<header>
    <nav>
        <ul>
            <li><a href="#" class="print"><i class="fa fa-fw fa-print"></i> Print</a></li>
            <li<c:if test="${activeMenuItemId eq 1}"> class="active"</c:if>><a href="<c:url value='/index.htm'/>"><i class="fa fa-fw fa-home"></i> Home</a></li>
            <li<c:if test="${activeMenuItemId eq 2}"> class="active"</c:if>><a href="<c:url value='/partims.htm'/>"><i class="fa fa-fw fa-external-link-square"></i> All Partims</a></li>
        </ul>
        <c:if test="${not empty showSignOutButton and showSignOutButton}">
            <ul class="pull-right">
                <li><a href="?signout=true" class="signout-button"><i class="fa fa-sign-out"></i> <span>Sign-Out</span></a></li>
            </ul>
        </c:if>
    </nav>
    <h1>Oefening 2</h1>
</header>