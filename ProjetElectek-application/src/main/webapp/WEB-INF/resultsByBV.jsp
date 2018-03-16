<%-- 
    Document   : resultsByBV
    Created on : 16 mars 2018, 14:22:24
    Author     : AMM
--%>

<%@page import="java.util.Map"%>
<%@page import="upec.groupe1.affine.AffineBV"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rangs par bureaux de votes</title>
    </head>
    <body>
        <h1>Rangs par bureaux de votes</h1>
        <table border="2" width="1" cellspacing="1" cellpadding="1">
        <%
            Map<Long,AffineBV> map = (Map<Long,AffineBV>) request.getAttribute("results");
            out.println("<tr><th>BV n°</th><th>premier</th><th>dernier</th><th>scorePremier</th><th>scoreDernier</th></tr>");
            for(Long l : map.keySet()){
                String first = map.get(l).getFirst();
                String last = map.get(l).getLast();
                Long firstPurcent = map.get(l).purcent(first);
                Long lastPurcent = map.get(l).purcent(last);
                out.println("<tr><td>"+l+"</td><td>"+first+"</td><td>"+last+"</td><td>"+firstPurcent+"%</td><td>"+lastPurcent+"%</td></tr>");
            }
        
        %>
        </table>
    </body>
</html>
