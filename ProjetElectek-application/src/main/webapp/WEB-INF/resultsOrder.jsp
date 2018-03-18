<%-- 
    Document   : results
    Created on : 17 mars 2018, 16:21:30
    Author     : adam
--%>

<%@page import="upec.groupe1.entities.Results"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Résultats</title>
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
        <h1>Résultats généraux</h1>
        <form method="POST" action="/ProjetElectek-application/SortResults">

            <select name="election">
                <option value="Presidentielle">Presidentielles</option>
                <option value="Legislatives">Legislatives</option>
            </select>
            <select name="year">
                <option value="2007">2007</option>
                <option value="2012">2012</option>
                <option value="2017">2017</option>
            </select>
            <select name="sort">
                <option value="CANDIDATEFN">Nom</option>
                <option value="CANDIDATELN">prenom</option>
                <option value="NUMBV">bureau de vote</option>
            </select>
            <input class="btn btn-success" type="submit" name="Afficher" />
        </form>
        <table border="1" width="2" cellspacing="1">

            <%
                try{
                    List<Results> results = (List<Results>)request.getAttribute("results");
                    if(!results.isEmpty()){
                        out.println("<tr><th>nom</th><th>prenom</th><th>nombre de voies</th><th>nombre de votes</th><th>election</th><th>tour</th><th>bureau</th></tr>");
                        for (Results r : results){
                            out.println("<tr><td>"+r.getCandidateFN()+"</td><td>"+r.getCandidateLN()+"</td><td>"+
                                    r.getNbVoie()+"</td><td>"+r.getNbExprime()+"</td><td>"+r.getCaption()+"</td><td>"+r.getTurn()+"</td><td>"+r.getNumBV()+"</td></tr>");
                        }
                    }
                    
                }catch(NullPointerException e){
                    out.println("");
                }
            %>
        </table>

    </body>
</html>
