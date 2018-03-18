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
    </head>
    <body>
        
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
