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
    <o2:head title="Homepage"/>
    <body>
        <o2:header showSignOutButton="true"/>
        <main class="container">
            <h2>Hello, ${user.username}</h2>

            <c:if test="${not empty partims}">
                <form method="post">
                    <fieldset>
                        <legend>Partims</legend>
                        <table class="data-table" data-area="toggle" data-toggle-controls="partims-edited" data-slaves="partim">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th class="text-center">Code</th>
                                    <th class="text-left">Partim name</th>
                                    <th class="text-center">Credits</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${partims}" var="partim">
                                    <tr>
                                        <td>
                                            <c:set var="found" value="false"/>
                                            <c:set var="checkedAttr"/>
                                            <c:forEach items="${user.partims}" var="userPartim">
                                                <c:if test="${not found and userPartim.id eq partim.id}">
                                                    <c:set var="checkedAttr" value=' checked="checked"'/>
                                                    <c:set var="found" value='true'/>
                                                </c:if>
                                            </c:forEach>
                                            <input type="checkbox" id="${partim.id}" name="partims"${checkedAttr}/>
                                        </td>
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
                        <button type="reset" class="button" disabled="disabled" data-toggle-control="partims-edited"><i class="fa fa-remove"></i> Cancel</button>
                        <button type="submit" class="button" disabled="disabled" data-toggle-control="partims-edited"><i class="fa fa-save"></i> Save</button>
                        <button type="button" class="button" data-select="partim"><i class="fa fa-checkbox-square"></i> Select All</button>
                        <button type="button" class="button" data-unselect="partim"><i class="fa fa-square-o"></i> Unselect All</button>
                    </fieldset>
                </form>
            </c:if>
        </main>
        <o2:footer/>
        <o2:tail/>
    </body>
</html>
