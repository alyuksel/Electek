<%-- 
    Document   : SearchVoteOfficeFromAdress
    Created on : 16 mars 2018, 22:04:14
    Author     : drajasin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         
       
    </head>
    <body>
        <%@include  file="../TopMenu.jsp"%>
        
        <h1>Trouver votre bureau de vote!</h1>
        
        <%String message = (String)request.getAttribute("message");
         
            if(message!=null){
            %>
            <div style="color: red;"><%=message%></div>
            <%
            }
        
        %>
        <form method="post" action="/ProjetElectek-application/voteoffices/searchAdress">
            <fieldset>
                <legend>Adresse</legend>
                <p>Entrez votre adresse.</p>
                <label for="numero">Numéro <span class="requis">*</span></label>
                <input type="text" id="numero" name="numero" value="" size="20" maxlength="100" />
                <br />

                <label for="rue">Rue <span class="requis">*</span></label>
                <input type="text" id="rue" name="rue" value="" size="20" maxlength="300" />
                <br />

                <label for="cp">Code Postale <span class="requis">*</span></label>
                <input type="text" id="cp" name="cp" value="750" size="20" maxlength="5" />
                <br />

                <label for="pays">Pays</label>
                <input type="text" id="pays" name="pays" value="FRANCE" size="20" maxlength="20" readonly/>
                <br />

                <input type="submit" value="Trouver" class="sansLabel" />
                <br />
            </fieldset>
        </form>
    </body>
</html>
