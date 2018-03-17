<%-- 
    Document   : ResultVoteOfficeFromAdress
    Created on : 17 mars 2018, 00:24:31
    Author     : drajasin
--%>

<%@page import="upec.groupe1.entities.Adresse"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <%
            Adresse posts = (Adresse) request.getAttribute("adresse"); 
            %>
            <td><%posts.getStreetName();%></td>
            
    </body>
</html>
