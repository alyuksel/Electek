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
<html id="result">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test</title>
        <%@include  file="../template/Header.jsp"%>
        <script>
            $(document).ready(function () {
                $("#year").change(function () {
                    $.post("<%=(String)(request.getContextPath()+request.getAttribute("path"))%>",
                            {
                                year: $("#year option:selected").val(),
                                turn: $("#turn option:selected").val(),
                                dataType: 'html'

                            },
                            function (data, status) {
                                document.open();
                                document.write(data);
                                document.close();
                            });
                });
                $("#turn").change(function () {
                    $.post("<%=(String)(request.getContextPath()+request.getAttribute("path"))%>",
                            {
                                year: $("#year option:selected").val(),
                                turn: $("#turn option:selected").val(),
                                dataType: 'html'

                            },
                            function (data, status) {
                                document.open();
                                document.write(data);
                                document.close();
                            });
                });
            });
        </script>
    </head>
    <body>
        <%@include  file="../template/TopMenu.jsp"%>
        <div class="middleContent">
            <%@include file="../template/SideMenu.jsp" %>
            <div class="container">
                <h2>Consultation du score d'un candidat</h2>
                <%
                    String path = (String)(request.getContextPath()+request.getAttribute("path"));
                %>
                <form action="<%=path%>" method="POST" accept-charset="UTF-8">
                    <select name="turn" id="turn">
                        <option value="1" <%
                        if("1".equals((String)request.getAttribute("turn")))
                        {%>
                                selected="selected"
                                <%}
                                %>>1er</option>
                        <option value="2"<%if("2".equals((String)request.getAttribute("turn"))){%> selected="selected"<%}%>>2ème</option>
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
                    <select name="year" id="year">
                        <option value="2007" <%if("2007".equals((String)request.getAttribute("year"))){%> selected="selected"<%}%>>2007</option>
                        <option value="2012" <%if("2012".equals((String)request.getAttribute("year"))){%> selected="selected"<%}%>>2012</option>
                        <option value="2017" <%if("2017".equals((String)request.getAttribute("year"))){%> selected="selected"<%}%>>2017</option>
                    </select>
                    <%
                        List<Candidate> candidates = (List<Candidate>) request.getAttribute("candidates");
                        if(candidates!=null){
                    %>
                    <select name="fullName" id="fullname">
                        <%
                        for (Candidate cand : candidates)
                        {   
                            String item = (String) cand.getFullName();
                        %>
                        <option value="<%=item%>"><%=item%></option>
                        <%
                    }
                        %>
                    </select>
                    <%
                }
                    %>
                    <input class="btn btn-success" type="submit" value="Submit" />
                </form>
                <%
                    if((Boolean)request.getAttribute("isScore") == true && request.getAttribute("score") != null){

                        Score score = (Score) request.getAttribute("score");
                        Double percent = score.getPercent();
                        String voies = ""+score.getVoteNumber();
                        String voieTotal = ""+score.getTotal();
                        String candidatFN =(String) score.getCandidate().getFullName();
                %>
                <br>
                <table class="table">
                    <tr>
                        <th scope="row">Candidat</th>
                        <th scope="row">Nb Voies</th>
                        <th scope="row">Pourcentage</th>
                    </tr>
                    <tr>
                        <td><%=candidatFN%></td>
                        <td><%=voies%> sur <%=voieTotal%></td>
                        <td><%=percent%> %</td>
                    </tr>
                </table>
                <%}%>
            </div>
        </div>
        <div>
                <%@include file="../template/Footer.jsp" %>
        </div>
    </body>
</html>