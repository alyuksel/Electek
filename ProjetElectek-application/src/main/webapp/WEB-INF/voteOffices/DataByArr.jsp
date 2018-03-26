<%--
    Document   : DataByArr
    Created on : 16 mars 2018, 08:15:32
    Author     : drajasin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="upec.groupe1.entities.VoteOffices"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include  file="../template/Header.jsp"%>
        <title>Bureaux de vote par Arrondissement</title>
    </head>
    <body>
        <%@include  file="../template//TopMenu.jsp"%>
        <div class="middleContent">
            <%@include file="../template/SideMenu.jsp" %>
            <div class="container">
                <h1>Bureaux de vote par Arrondissement !</h1>
                <table>
                    <thead>
                        <tr>
                            <th>Arrondissment</th>
                            <th>Numéro</th>
                            <th>Libélé</th>
                            <th>Adresse</th>
                            <th>Code Postale</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%                Map<String, List<VoteOffices>> posts = (Map<String, List<VoteOffices>>) request.getAttribute("MapVoteOffices");

                            for (String key : posts.keySet()) {
                                for (VoteOffices vo : posts.get(key)) {
                        %>
                        <tr>
                            <td><%=key%></td>
                            <td><%=vo.getNumber()%></td>
                            <td><%=vo.getCaption()%></td>
                            <td><a href="<%=request.getContextPath()%>/voteoffices/detail?number=<%=vo.getNumber()%>" ><%=vo.getAdress()%></a></td>
                            <td><%=vo.getPostalCode()%></td>

                        </tr>
                        <%
                            }
                        %>

                        <%
                            }
                        %>
                        </tr>
                    </tbody>
                </table>
                <div>
                    <%@include file="../template/Footer.jsp" %>
                </div>
            </div>
        </div>
    </body>
</html>
