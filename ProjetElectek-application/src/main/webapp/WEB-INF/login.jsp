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
    </head>
    <body>
                <%@include  file="template/TopMenu.jsp"%>

        <% 
            if(request.getAttribute("error")!=null){
                out.println("<font color=red>"+request.getAttribute("error")+"</font>");
            }
            if(request.getAttribute("signin")!=null){
                out.println("<font color=green>"+request.getAttribute("signin")+"</font>");
            }
        %>
        <h1>Login</h1>
        <form name="login" action="/ProjetElectek-application/Login" method="POST">
            <p>login</p>
            <input type="text" name="username" /> 
            <p>mot de passe</p>
            <input type="password" name="password" /> 
            <input class="btn btn-success" type="submit" name="Se Connecter" /> 
        </form>
        <p>Vous ne posséder pas de compte ? <a href="/ProjetElectek-application/SignIn" >Par ici ! </a>
    </body>
</html>
