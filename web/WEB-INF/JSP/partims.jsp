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
    <o2:head title="List of Partims"/>
    <body>
        <o2:header showSignOutButton="${not empty user}" activeMenuItemId="2"/>
        <main class="container">
            <h2>Partims</h2>
            <c:if test="${not empty partims}">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th class="text-center">Code</th>
                            <th class="text-left">Partim name</th>
                            <th class="text-center">Credits</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${partims}" var="partim">
                            <tr>
                                <td class="text-center">${partim.code}</td>
                                <td class="text-left">${partim.name}</td>
                                <td class="text-center">
                                    <svg width="49" height="9">
                                    <rect width="49" height="9" style="fill:rgb(0,180,0);" />
                                    <c:forEach begin="1" end="${partim.credits}" varStatus="loop">
                                        <rect x="${2 + 2*(loop.index-1) + 5*(loop.index-1)}" y="2" width="5" height="5" style="fill:rgb(255,255,255);" />
                                    </c:forEach>
                                    Sorry, your browser does not support inline SVG.
                                    </svg> 
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </main>
        <o2:footer/>
        <o2:tail/>
    </body>
</html>
