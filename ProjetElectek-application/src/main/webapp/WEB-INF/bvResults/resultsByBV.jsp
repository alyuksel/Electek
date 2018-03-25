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
        <%@include file="../template/Header.jsp" %>
    </head>
    <body>
        <%@include  file="../template/TopMenu.jsp"%>
        <div class="middleContent">
            <%@include file="../template/SideMenu.jsp" %>
            <div>
                <div class="container">
                    <h1>Rangs par bureaux de votes</h1>
                    <table class="table">
                        <%            Map<String, AffineBV> map = (Map<String, AffineBV>) request.getAttribute("results");
                            out.println("<tr><th scope=`\"col\">BV n°</th><th scope=`\"col\">premier</th>"
                                    + "<th scope=`\"col\">dernier</th><th scope=`\"col\">Plus ?</th></tr>");
                            for (String l : map.keySet()) {
                                String first = map.get(l).getFirst();
                                String last = map.get(l).getLast();
                                out.print("<tr><td>" + l + "</td><td>" + first + "</td><td>" + last + "</td><td>");
                                out.println("<form action=\"/ProjetElectek-application/DetailBV\" method=\"POST\">"
                                        + "<input type=\"hidden\" value=\"" + l + "\" name=\"numero\">"
                                        + "<input class=\"btn btn-outline-secondary\" "
                                        + "type=\"submit\" value=\"détail\" name=\"detail\" />"
                                        + "</form></td></tr>");

                            }
                        %>

                    </table>
                </div>
            </div>
        </div>
    </body>
    <%@include file="../template/Footer.jsp" %>
</html>
