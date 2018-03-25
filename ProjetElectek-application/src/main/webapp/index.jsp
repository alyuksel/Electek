<%-- 
    Document   : index
    Created on : 18 mars 2018, 16:08:24
    Author     : adam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Accueil</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="WEB-INF/template/Header.jsp" %>
    </head>
    <body>
        <%@include  file="WEB-INF/template/TopMenu.jsp"%>
        <div class="middleContent">
            <%@include file="WEB-INF/template/SideMenu.jsp" %>
            <div>
                <h1 class="h1">Bienvenue sur ElecteK!</h1>
                <h4 class="h4">une nouvelle vision des resultats electoraux !</h4>
            </div>
        </div>
    </body>
    <%@include file="WEB-INF/template/Footer.jsp" %>
</html>

