<%-- 
    Document   : candidates
    Created on : 16 mars 2018, 10:25:36
    Author     : AMM
--%>

<%@page import="java.util.Map"%>
<%@page import="upec.groupe1.entities.Results"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Choix pour le classement des candidats</title>
         
       
    </head>
    <body>
        <%@include  file="template/TopMenu.jsp"%>
        
        <h1>Choisissez les critères de recherches</h1>
        <form action="/ProjetElectek-application/resultatBV" method="POST">
            Elections : <br>
            <input type="radio" name="election" value="Presidentielle" checked/> Présidentielles <br>
            <input type="radio" name="election" value="Legislatives"/> Législatives <br>
            Tour : <br>
            <input type="radio" name="turn" value="1" checked/>1er<br>
            <input type="radio" name="turn" value="2"/>2ème<br>
            Année :<br>
            <input type="radio" name="year" value="2007" checked> 2007<br> 
            <input type="radio" name="year" value="2012">2012<br> 
            <input type="radio" name="year" value="2017">2017<br> 
         
            <input class="btn btn-success" type="submit" value="valider" name="valide" />
        </form>
    </body>
</html>
