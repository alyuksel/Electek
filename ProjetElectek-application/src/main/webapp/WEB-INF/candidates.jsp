<%-- 
    Document   : candidates
    Created on : 16 mars 2018, 10:25:36
    Author     : AMM
--%>

<%@page import="java.util.Map"%>
<%@page import="upec.groupe1.entities.Results"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Liste des candidats</h1>
        <table border="1">    

        <%
            
            Map<String,Long> map = (Map<String,Long>) request.getAttribute("res");
            out.println("<tr><th>Nom</th><th>Prenom</th></tr>");
            for (String key : map.keySet()){
                String nom = key.split("_")[0];
                String prenom = key.split("_")[1];
                if (!nom.equals("-"))
                    out.println("<tr><td>"+nom+"</td><td>"+prenom+"</td><td>"+map.get(key)+"</td></tr>");
            }
        %>

        </table>
        
    </body>
</html>
