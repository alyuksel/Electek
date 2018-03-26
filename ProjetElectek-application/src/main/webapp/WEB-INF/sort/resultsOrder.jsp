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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Résultats</title>
        <%@include file="../template/Header.jsp" %>
    </head>
    <body>
        <%@include  file="../template/TopMenu.jsp"%>
        <div class="middleContent">
            <%@include file="../template/SideMenu.jsp" %>
            <div class="container">
                <div>
                    <h1>Résultats généraux</h1>
                    <form method="POST" action="/ProjetElectek-application/SortResults">

                        <select class="form-control form-control-sm" name="election">
                            <option value="Presidentielle">Presidentielles</option>
                            <option value="Legislatives">Legislatives</option>
                        </select>
                        <select  class="form-control form-control-sm" name="year">
                            <option value="2007">2007</option>
                            <option value="2012">2012</option>
                            <option value="2017">2017</option>
                        </select>
                        <select  class="form-control form-control-sm" name="sort">
                            <option value="CANDIDATEFN">Nom</option>
                            <option value="CANDIDATELN">Prenom</option>
                            <option value="ARR,NUMBV">Bureau de vote</option>
                        </select>
                        <input class="btn btn-success" type="submit" name="Afficher" />
                    </form>
                    <table class="table">

                        <%                try {
                                List<Results> results = (List<Results>) request.getAttribute("results");
                                if (!results.isEmpty()) {
                                    out.println("<tr><th scope=`\"col\">nom</th>"
                                            + "<th scope=`\"col\">prenom</th>"
                                            + "<th scope=`\"col\">nombre de voies</th>"
                                            + "<th scope=`\"col\">nombre de votes</th>"
                                            + "<th scope=`\"col\">election</th><th>tour</th>"
                                            + "<th scope=`\"col\">bureau</th></tr>");
                                    for (Results r : results) {
                                        out.print("<tr><td>" + r.getCandidateFN() + "</td><td>" + r.getCandidateLN() + "</td><td>"
                                                + r.getNbVoie() + "</td><td>" + r.getNbExprime() + "</td><td>" + r.getCaption() + "</td><td>" + r.getTurn() + "</td>");
                                        String link = "<td> <a href='"+request.getContextPath()+"/voteoffices/detail?number="+ r.getArr().intValue()+"-"+r.getNumBV() + "'>"+r.getArr().intValue()+"-"+r.getNumBV()+"</a></td></tr>" ;
                                        out.println(link);
                                    }
                                }

                            } catch (NullPointerException e) {
                                out.println("");
                            }
                        %>
                    </table>
                </div>   
            </div>
        </div>
        <div>
            <%@include file="../template/Footer.jsp" %>
        </div>  
    </body>
</html>
