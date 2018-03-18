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
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rangs par bureaux de votes</title>
    </head>
    <body>
        <nav class="navbar navbar-dark bg-primary">
            <a class="navbar-brand" href="/ProjetElectek-application/index.html">Accueil</a>
            <a class="navbar-brand" href="/ProjetElectek-application/Candidates">Classement</a>
            <a class="navbar-brand" href="/ProjetElectek-application/SortResults">Resultats</a>
            <a class="navbar-brand" href="/ProjetElectek-application/MyResults">Mes Résultats</a>
            <a class="navbar-brand" href="/ProjetElectek-application/results/presidentielle">Resultats</a>
            <a class="navbar-brand" href="/ProjetElectek-application/voteoffices">Bureaux</a>
            <%
               if(session.getAttribute("user")!=null){
                   out.println("<a class=\"navbar-brand\" href=\"/ProjetElectek-application/Logout\">Logout</a>");
               }
            %>
        </nav>
        <h1>Rangs par bureaux de votes</h1>
        <table border="2" width="1" cellspacing="1" cellpadding="1">
        <%
            Map<Long,AffineBV> map = (Map<Long,AffineBV>) request.getAttribute("results");
            out.println("<tr><th>BV n°</th><th>premier</th><th>dernier</th><th>Plus ?</th></tr>");
            for(Long l : map.keySet()){
                String first = map.get(l).getFirst();
                String last = map.get(l).getLast();
                out.print("<tr><td>"+l+"</td><td>"+first+"</td><td>"+last+"</td><td>");
                out.println("<form action=\"/ProjetElectek-application/DetailBV\" method=\"POST\">"
                            +"<input type=\"hidden\" value=\""+l+"\" name=\"numero\">"
                            +"<input type=\"submit\" value=\"détail\" name=\"detail\" />"
                            +"</form></td></tr>");
                
            }
        %>
        
        </table>
    </body>
</html>
