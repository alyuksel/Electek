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
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
       
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-dark bg-primary">
            <a class="navbar-brand" href="/ProjetElectek-application/index.html">Accueil</a>
            <a class="navbar-brand" href="/ProjetElectek-application/Candidates">Classement</a>
            <a class="navbar-brand" href="/ProjetElectek-application/SortResults">Resultats</a>
            <a class="navbar-brand" href="/ProjetElectek-application/MyResults">Mes Résultats</a>
            <a class="navbar-brand" href="/ProjetElectek-application/results/presidentielle">Resultats</a>
            <a class="navbar-brand" href="/ProjetElectek-application/voteoffices">Bureaux</a>
            <%
               if(session.getAttribute("user")!=null){
                   out.println("<a class=\"navbar-brand\" href=\"/ProjetElectek-application/Logout\">Logout</a>");
               }
            %>
        </nav>
        <h1>Bureaux de votes de Paris !</h1>
        <%
                ArrayList<VoteOffices> posts = (ArrayList<VoteOffices>) request.getAttribute("ListeVoteOffices");
                String erreur = (String) request.getAttribute("erreur");
                if(erreur!=null){
                    %><p>Erreur mauvais Filtrage : <%=erreur%></p><%
                }
                
        %>
        <form id="form" method="post" action="/ProjetElectek-application/voteoffices">
        <table id="myTable" class="tablesorter"> 
            <p><button type="submit" form="form" value="Submit">Submit</button></p>
            <tr>
                <td></td>
                <td><input name="number" type="text" /></td>
                <td><input name="caption" type="text" /></td>
                <td><input name="adress" type="text" /></td>
                <td></td>
            </tr>
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
                for (VoteOffices vO : posts) {
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
        </form>
    </body>
</html>
<script>

    $(document).ready(function ()
    {
        $("#myTable").tablesorter();
    }
    );

    $(document).ready(function ()
    {
        $("#myTable").tablesorter({sortList: [[0, 0], [1, 0]]});
    }
    );
    $(document).ready(function(){
    $('#numberFilter').click(function(){
        $.ajax({
            type: 'POST',
            url: "/ProjetElectek-application/voteoffices",
            async: false,
             data: { 
                 'foo': 'bar', 
                 'ca$libri': 'no$libri'
             },
            success: function(result){
            $("div").html(result);
        }});
    });
});
    
</script>