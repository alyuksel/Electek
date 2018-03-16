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
        <title>VoteOffice from AttachedZone</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <table> 
            <th>Arrondissment</th>
            <th>IdVoteOffice</th>
            <th>Numéro</th>
            <th>Libélé</th>
            <th>Adresse</th>
            <th>Code Postale</th>
            
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
        </table>      

    </body>
</html>