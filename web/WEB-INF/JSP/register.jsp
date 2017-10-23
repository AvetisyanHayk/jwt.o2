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
    <o2:head title="Register"/>
    <body>
        <header>
            <h1>Oefening 2</h1>
        </header>
        <main class="container">
            <form method="post" class="form-compact">
                <fieldset>
                    <legend><i class="fa fa-asterisk"></i> Register</legend>
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username"
                           placeholder="Username" required value="${param.username}"
                           pattern="^_{0,1}[a-zA-Z]+(_{0,1}[a-zA-Z0-9]+)+$"
                           maxlength="16"/>
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password"
                           placeholder="Password" required
                           pattern="^[^$\s]+\S{3,}$"/>
                    <label for="password-repeat">Repeat Password</label>
                    <input type="password" id="password-repeat" name="password-repeat"
                           placeholder="Repeat Password" required/>
                </fieldset>
                <button type="submit" class="button"><i class="fa fa-fw fa-check"></i> Register</button>
                <a href="<c:url value='/signin.htm'/>" class="button"><i class="fa fa-fw fa-sign-in"></i> Sign-In</a>
                <c:if test="${not empty errorUsername}">
                    <div class="alert alert-warning"><i class="fa fa-warning"></i> <strong>Wrong username</strong><br/>
                        Username must contain at least 4 characters (letters, numbers, or underscore)
                        and must begin with a letter or underscore.
                    </div>
                </c:if>
                <c:if test="${not empty errorPassword}">
                    <div class="alert alert-warning"><i class="fa fa-warning"></i> <strong>Wrong password</strong><br/>
                        Password must contain at least 4 characters (letters, numbers, or special characters)
                        and may not begin with a dollar sign ($).
                    </div>
                </c:if>
                <c:if test="${not empty errorPasswordsMismatch}">
                    <div class="alert alert-warning"><i class="fa fa-warning"></i> <strong>Passwords don't match</strong><br/>
                        Passwords must be the same.
                    </div>
                </c:if>
                <c:if test="${not empty errorUserExists}">
                    <div class="alert alert-warning"><i class="fa fa-warning"></i> <strong>User exists</strong><br/>
                        Usernames must be unique. User <strong>${param.username}</strong> already exists in our database.
                    </div>
                </c:if>
            </form>
        </main>
        <o2:footer/>
        <o2:tail/>
    </body>
</html>
