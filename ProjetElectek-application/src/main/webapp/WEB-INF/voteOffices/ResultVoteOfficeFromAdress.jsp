<%-- 
    Document   : ResultVoteOfficeFromAdress
    Created on : 17 mars 2018, 00:24:31
    Author     : drajasin
--%>

<%@page import="upec.groupe1.entities.VoteOffices"%>
<%@page import="upec.groupe1.entities.AttachedZone"%>
<%@page import="upec.groupe1.entities.Adresse"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
       
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
        
            <%
                Adresse posts = (Adresse) request.getAttribute("adresse");
            %>
        <!-- #######  YAY, I AM THE SOURCE EDITOR! #########-->
        <h1 style="color: #5e9ca0;">Bureau de vote pour votre adresse:</h1>
        <h2 style="color: #2e6c80;"><span style="text-decoration: underline;">Adresse:</span></h2>
        <p>&nbsp;</p>
        <p>Vous avez sasie:</p>
        <table style="height: 138px;" width="297">
            <tbody>
                <tr>
                    <td style="width: 140px;"><strong><span style="text-decoration: underline;">Num&eacute;ro :</span></strong></td>
                    <td style="width: 141px;"><%=posts.getStreetNum()%></td>
                </tr>
                <tr>
                    <td style="width: 140px;"><strong><span style="text-decoration: underline;">Rue :</span></strong></td>
                    <td style="width: 141px;"><%=posts.getStreetName()%></td>
                </tr>
                <tr>
                    <td style="width: 140px;"><strong><span style="text-decoration: underline;">Arrondissement :</span></strong></td>
                    <td style="width: 141px;"><%=posts.getArr()%></td>
                </tr>
                <tr>
                    <td style="width: 140px;"><strong><span style="text-decoration: underline;">Pays :&nbsp;</span></strong></td>
                    <td style="width: 141px;">FRANCE</td>
                </tr>
            </tbody>
        </table>
        <p>&nbsp;L'adresse saisie n'est pas pr&eacute;cise. Voici la liste des adresse se rattachant a l'adresse saisie. PAS FINIS</p>
        <h2 style="color: #2e6c80;">Bureau de vote :</h2>
        <p>&nbsp;</p>
        <table class="editorDemoTable">
            <thead>
                <tr>
                    <td>Id Bureau</td>
                    <td>Bureau de vote</td>
                    <td>Lib&eacute;l&eacute;</td>
                    <td>Adresse</td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <%  AttachedZone zone = (AttachedZone) posts.getAttachedZone();
                        VoteOffices vo = (VoteOffices) zone.getVoteOffice();%>
                    <td><%=vo.getIdVoteOffices()%></td>
                    <td><%=vo.getNumber()%></td>
                    <td><%=vo.getCaption()%></td>
                    <td><%=vo.getAdress()%></td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
