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
            <div>
                <div class="container">
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
                            <option value="CANDIDATELN">prenom</option>
                            <option value="NUMBV">bureau de vote</option>
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
                                        out.println("<tr><td>" + r.getCandidateFN() + "</td><td>" + r.getCandidateLN() + "</td><td>"
                                                + r.getNbVoie() + "</td><td>" + r.getNbExprime() + "</td><td>" + r.getCaption() + "</td><td>" + r.getTurn() + "</td><td>" + r.getNumBV() + "</td></tr>");
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
    </body>
    <%@include file="../template/Footer.jsp" %>
</html>
