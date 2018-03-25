<%--
    Document   : Results
    Created on : 15 mars 2018, 23:41:07
    Author     : alpi
--%>

<%@page import="java.util.stream.Collectors"%>
<%@page import="upec.groupe1.dto.Score"%>
<%@page import="upec.groupe1.dto.Candidate"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Test</title>
    </head>
    <body>
        <%@include  file="../TopMenu.jsp"%>
        <h2>Consultation du score d'un candidat</h2>
        <%
            String path = (String)(request.getContextPath()+request.getAttribute("path"));
        %>
        <form action="<%=path%>" method="POST">
            <select name="fullName">
                <%
                List<Candidate> candidates = (List<Candidate>) request.getAttribute("candidates");
                for (Candidate cand : candidates)
                {
                        String item = (String) cand.getFullName();
                %>
                   <option value="<%=item%>"><%=item%></option>
                <%
                }
                %>
            </select>
            <select name="turn">
                <option value="1">1er</option>
                <option value="2">2ème</option>
            </select>
            <select name="place">
                <option>global</option>
                <%
                    int i;
                    for(i = 1 ; i<=20 ; i++){
                %>
                <option value="<%=i%>"><%=i%></option>
                <%}%>
            </select>
            <select name="year">
                <option value="2007">2007</option>
                <option value="2012">2012</option>
                <option value="2017">2017</option>
            </select>
            <input type="submit" value="Submit" />
        </form>
            <%
                if((Boolean)request.getAttribute("isScore") == true){

                    Score score = (Score) request.getAttribute("score");
                    String percent = ""+score.getPercent();
                    String voies = ""+score.getVoteNumber();
                    String voieTotal = ""+score.getTotal();
                    String candidatFN =(String) score.getCandidate().getFullName();
                %>
                <br>
                <table>
                    <tr>
                      <th>Candidat</th>
                      <th>Nb Voies</th>
                      <th>Pourcentage</th>
                    </tr>
                    <tr>
                      <td><%=candidatFN%></td>
                      <td><%=voies%> sur <%=voieTotal%></td>
                      <td><%=percent%> %</td>
                    </tr>
              </table>
            <%}%>
    </body>
</html>
