<%-- 
    Document   : DetailBV
    Created on : 17 mars 2018, 00:32:34
    Author     : adam
--%>

<%@page import="java.util.Map"%>
<%@page import="upec.groupe1.affine.AffineBV"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>détail BV</title>
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
       
    </head>
    <body>
        <nav class="navbar navbar-dark bg-primary">
            <a class="navbar-brand" href="/ProjetElectek-application/index.html">Accueil</a>
            <a class="navbar-brand" href="/ProjetElectek-application/Candidates">Classement</a>
            <a class="navbar-brand" href="/ProjetElectek-application/SortResults">Resultats</a>
            <a class="navbar-brand" href="/ProjetElectek-application/MyResults">Mes Résultats</a>
            <%
               if(session.getAttribute("user")!=null){
                   out.println("<a class=\"navbar-brand\" href=\"/ProjetElectek-application/Logout\">Logout</a>");
               }
            %>
        </nav>
        <h1>Détail du bureau de vote n° ${param.numero}</h1>
        <table border="2" width="2" cellspacing="2">
        <%
            Map<Long,AffineBV> results = (Map<Long,AffineBV>) request.getAttribute("result");
            if(results.isEmpty())
                out.println("pas de détail pour ce bureau de vote");
            else{
                AffineBV affineBV = results.get(Long.valueOf(request.getParameter("numero")));
                
                if(affineBV.getMapScores().isEmpty()){
                    out.println("<h4>Pas de résultats pour ce bureau de vote</h4>");
                }else{
                    out.println("<tr><th>candidat</th><th>score</th></tr>");
                    for(String candidat : affineBV.getMapScores().keySet()){
                        out.println("<tr><td>"+candidat+"</td><td>"+affineBV.purcent(candidat)+"</td></tr>");
                    }
                }
            }
        %>
          </table>
    </body>
</html>
