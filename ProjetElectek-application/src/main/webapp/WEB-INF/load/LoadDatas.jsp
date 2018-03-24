<%-- 
    Document   : LoadDatas
    Created on : 18 mars 2018, 00:39:45
    Author     : drajasin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LoadDatas</title>
         
       
    </head>
    <body>
        <%@include  file="WEB-INF/TopMenu.jsp"%>
        
        <h1 style="color: #4485b8;">Bonjour ADMIN !</h1>
        <p><strong style="color: #000;">Information :</strong> Cette page vos offre les droits d'importation de la base de donn&eacute;es.<br />Edit any of the two areas and see the other changing in real time.&nbsp;</p>
        <h4>Use the table below to test most of the features</h4>
            <% String errorMSG = (String)request.getAttribute("error");
                Long countAdresse = (Long)request.getAttribute("countAdresses");
                Long countZones = (Long)request.getAttribute("countAttachedZones");
                Long countResults = (Long)request.getAttribute("countResults");
                Long countOffices = (Long)request.getAttribute("countVoteOffices");
                if(errorMSG!=null){
            %>
            <div style="height: 49px;color:red;"><%=errorMSG%></div>
            <%
            }%>
        
        <form id="form" method="post" action="/ProjetElectek-application/LoadData">
            <table class="editorDemoTable" style="vertical-align: top;">
                <thead>
                    <tr style="height: 23px;">
                        <td style="height: 23px; text-align: center;">Donn&eacute;es</td>
                        <td style="height: 23px; text-align: center;">URL</td>
                        <td style="height: 23px; text-align: center; " >Option</td>
                         <%
                             if(countAdresse!=null || countZones!=null ||countResults!=null || countOffices!=null){
                                 %>
                                 <td style="height: 23px; text-align: center; " >Nombre de lignes rajoutées</td>
                        
                                 <%
                             }
                         %>
                        
                    </tr>
                </thead>
                <tbody>
                    <tr style="height: 67px;">
                        <td style="min-width: 140px; height: 67px; text-align: center;"><b>Adresses</b></td>
                        <td style="height: 67px;"><a href="https://opendata.paris.fr/api/records/1.0/search/?dataset=adresse_paris">https://opendata.paris.fr/api/records/1.0/search/?dataset=adresse_paris</a></td>
                        <td style="height: 67px; text-align: left;">
                            <input name="amount" type="radio" value="100" />100 <br />
                            <input name="amount" type="radio" value="500" />500<br />
                            <input name="amount" type="radio" value="-1" checked="checked"/>Toutes les adresses</td>
                    
                        <% 
                            if(countAdresse!=null){
                        %>
                        <td style="height: 49px;"><%=countAdresse%></td>
                        <%
                        }%>
                    </tr>
                    <tr style="height: 135px;">
                        <td style="height: 48px; text-align: center;"><strong>Zones de Rattachement</strong></td>
                        <td style="background-color: rgba(0, 0, 0, 0.1); height: 48px;"><a href="https://opendata.paris.fr/api/records/1.0/search/?dataset=zones-de-rattachement-des-bureaux-de-vote-en-2014">https://opendata.paris.fr/api/records/1.0/search/?dataset=zones-de-rattachement-des-bureaux-de-vote-en-2014</a></td>
                        <td style="height: 48px;"></td>
                        <% 
                            if(countZones!=null){
                        %>
                        <td style="height: 49px;"><%=countZones%></td>
                        <%
                        }%>
                    </tr>
                    <tr style="height: 49px;">
                        <td style="height: 45px; text-align: center;"><strong>Bureaux de votes</strong></td>
                        <td style="height: 45px;"><a href="https://opendata.paris.fr/api/records/1.0/search/?dataset=bureaux-de-votes">https://opendata.paris.fr/api/records/1.0/search/?dataset=bureaux-de-votes</a></td>
                        <td style="height: 45px;"></td>
                    
                        <% 
                            if(countOffices!=null){
                        %>
                        <td style="height: 49px;"><%=countOffices%></td>
                        <%
                        }%>
                    </tr>
                    <tr style="height: 107px;">
                        <td style="height: 49px; text-align: center;"><strong>R&eacute;sultats</strong></td>
                        <td style="height: 49px;"><a href="https://opendata.paris.fr/api/records/1.0/search/?dataset=resultats_electoraux">https://opendata.paris.fr/api/records/1.0/search/?dataset=resultats_electoraux</a></td>
                        <td style="height: 49px;"></td>
                        
                        <% 
                            if(countOffices!=null){
                        %>
                        <td style="height: 49px;"><%=countResults%></td>
                        <%
                        }%>   
                    </tr>
                </tbody>
            </table>
        </form>
        <p><button type="submit" form="form" value="Submit">Submit</button></p>
        <hr />
        <h1><strong>Licence de r&eacute;utilisation des donn&eacute;es</strong></h1>
        <form></form><form></form>
        <p><a title="Open" href="https://opendata.paris.fr/page/lalicence/">https://opendata.paris.fr/page/lalicence/</a></p>
        <p><a title="" href="https://opendata.paris.fr/terms/terms-of-use/">https://opendata.paris.fr/terms/terms-of-use/</a></p>





    </body>
</html>
