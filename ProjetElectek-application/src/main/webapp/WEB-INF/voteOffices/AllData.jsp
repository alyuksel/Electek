<%-- 
    Document   : FoundVoteOfficeFromAdresse
    Created on : 16 mars 2018, 00:41:37
    Author     : drajasin
--%>

<%@page import="upec.groupe1.entities.VoteOffices"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.29.6/js/jquery.tablesorter.js"></script>

        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <table id="myTable" class="tablesorter"> 
            <thead>
                <tr>
                    <th>Id Bureau de Vote</th>
                    <th>Numéro</th>
                    <th>Libélé</th>
                    <th>Adresse</th>
                    <th>Code Postale</th>
                </tr>
            </thead>
            <%
            ArrayList<VoteOffices> posts = (ArrayList<VoteOffices>) request.getAttribute("ListeVoteOffices"); 
            for (VoteOffices vO: posts) {   
            %>
            <tr>
            <td><%=vO.getIdVoteOffices()%></td>
            <td><%=vO.getNumber()%></td>
            <td><%=vO.getCaption()%></td>
            <td><%=vO.getAdress()%></td>
            <td><%=vO.getPostalCode()%></td>  
            </tr>
            <%}%>
        </table>      

    </body>
</html>
<script>
    
    $(document).ready(function() 
    { 
        $("#myTable").tablesorter(); 
    } 
    ); 
    
    $(document).ready(function() 
    { 
        $("#myTable").tablesorter( {sortList: [[0,0], [1,0]]} ); 
    } 
    ); 
</script>