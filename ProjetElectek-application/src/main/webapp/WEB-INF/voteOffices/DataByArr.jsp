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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.29.6/js/jquery.tablesorter.js"></script>
        
       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>VoteOffice from AttachedZone</title>
    </head>
    <body>
        <%@include  file="../template/TopMenu.jsp"%>
        
        <h1>Hello World!</h1>
        <table id="myTable" class="tablesorter"> 
            <thead>
                <tr>
                <th>Arrondissment</th>
                <th>IdVoteOffice</th>
                <th>Numéro</th>
                <th>Libélé</th>
                <th>Adresse</th>
                <th>Code Postale</th>
                </tr>
            </thead>
            <tbody>
            <%
            Map<String,List<VoteOffices>> posts = (Map<String,List<VoteOffices>>) request.getAttribute("MapVoteOffices"); 
           
            for(String key : posts.keySet()){
                for(VoteOffices vo: posts.get(key)){
                %>
                <tr>
                    <td><%=key%></td>
                    <td><%=vo.getIdVoteOffices()%></td>
                    <td><%=vo.getNumber()%></td>
                    <td><%=vo.getCaption()%></td>
                    <td><%=vo.getAdress()%></td>
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