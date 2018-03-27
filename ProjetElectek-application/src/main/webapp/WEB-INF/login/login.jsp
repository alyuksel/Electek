<%-- 
    Document   : login
    Created on : 18 mars 2018, 12:24:40
    Author     : adam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <%@include file="../template/Header.jsp" %>
    </head>
    <body>
        <%@include  file="../template/TopMenu.jsp"%>
        <div class="middleContent">
            <%@include file="../template/SideMenu.jsp" %>
            <div class="container">
                <%                    if (request.getAttribute("error") != null) {
                        out.println("<font color=red>" + request.getAttribute("error") + "</font>");
                    }
                    if (request.getAttribute("signin") != null) {
                        out.println("<font color=green>" + request.getAttribute("signin") + "</font>");
                    }
                %>
                <h1>Login</h1>
                <form name="login" action="<%=request.getContextPath()%>/Login" method="POST">
                    <p>login</p>
                    <input type="text" name="username" /> 
                    <p>mot de passe</p>
                    <input type="password" name="password" /> 
                    <input class="btn btn-success" type="submit" name="Se Connecter" /> 
                </form>
                <p>Vous ne posséder pas de compte ? <a href="<%=request.getContextPath()%>/SignIn" >Par ici ! </a>
                
            </div>
        </div>
        <div>
            <%@include file="../template/Footer.jsp" %>
        </div>
    </body>
</html>
