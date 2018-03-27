<%-- 
    Document   : Scores
    Created on : 25 mars 2018, 18:06:13
    Author     : alpi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="../template/Header.jsp" %>
    </head>
    <body>
        <%@include  file="../template/TopMenu.jsp"%>
        <div class="middleContent">
            <%@include file="../template/SideMenu.jsp" %>
            <div class="container">
                <h1>Scores des candidats</h1>
                <p>
                    Lien vers les scores présidentielle
                    <a href="<%=request.getContextPath()%>/scores/presidentielle">Présidentielle</a>
                </p>
                <p>
                    Lien vers les scores législatives
                    <a href="<%=request.getContextPath()%>/scores/legislatives">Législatives</a>
                </p>
            
            </div>
        </div>
        <div>
                <%@include file="../template/Footer.jsp" %>
        </div>
    </body>
</html>
