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
        <%@include file="../template/Header.jsp" %>

    </head>
    <body>
        <%@include  file="../template/TopMenu.jsp"%>
        <div class="middleContent">
            <%@include file="../template/SideMenu.jsp" %>
            <div class="container">
                <div>
                    <h1>Détail du bureau de vote n° ${param.numero}</h1>
                    <table class="table">
                        <%            Map<String, AffineBV> results = (Map<String, AffineBV>) request.getAttribute("result");
                            if (results.isEmpty()) {
                                out.println("pas de détail pour ce bureau de vote");
                            } else {
                                AffineBV affineBV = results.get(request.getParameter("numero"));

                                if (affineBV.getMapScores().isEmpty()) {
                                    out.println("<h4>Pas de résultats pour ce bureau de vote</h4>");
                                } else {
                                    out.println("<tr><th scope=`\"col\">Candidat</th><th scope=`\"col\">Score %</th></tr>");
                                    for (String candidat : affineBV.getMapScores().keySet()) {
                                        out.println("<tr><td>" + candidat + "</td><td>" + affineBV.purcent(candidat) + "</td></tr>");
                                    }
                                }
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
