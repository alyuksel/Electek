<%-- 
    Document   : signIn
    Created on : 18 mars 2018, 12:29:50
    Author     : adam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscription</title>
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
                %>
                <h1>S'inscrire</h1>
                <form name="signin" action="/ProjetElectek-application/SignIn" method="POST">
                    <p>Nom</p>
                    <input type="text" name="name" />
                    <p>Prenom</p>
                    <input type="text" name="lastname" />
                    <p>mail</p>
                    <input type="email" name="mail" />
                    <p>login</p>
                    <input type="text" name="username" /> 
                    <p>mot de passe</p>
                    <input type="password" name="password" />
                    <p>Verification du mot de pass</p>
                    <input type="password" name="password2"/>

                    <input class="btn btn-success" type="submit" name="S'inscire !" /> 
                </form>
            </div>
        </div>

    </body>
    <%@include file="../template/Footer.jsp" %>
</html>
