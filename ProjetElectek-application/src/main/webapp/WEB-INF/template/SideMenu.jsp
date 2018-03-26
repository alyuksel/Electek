<%-- 
    Document   : sideMenu
    Created on : 24 mars 2018, 23:56:56
    Author     : alpi9
--%>
<%String paths =request.getContextPath();%>
<div class="nav-side-menu">
    <div class="brand">Menu</div>
    <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>
    <div class="menu-list">
        <ul id="menu-content" class="menu-content collapse out">
            <li data-toggle="collapse" data-target="#products" class="collapsed active">
                <a><i class="fa fa-gift fa-lg"></i> Scores Candidats <span class="arrow"></span></a>
            </li>
            <ul class="sub-menu collapse" id="products">
                <li data-toggle="collapse" data-target="#Presidentielles" class="collapsed active">
                    Presidentielle
                </li>
                <ul class="sub-menu collapse" id="Presidentielles">
                    <li onclick="location.href = '<%=paths%>/scores/presidentielle?turn=1&year=2017';"> presidentielle 2017 - 1er Tour</li>
                    <li onclick="location.href = '<%=paths%>/scores/presidentielle?turn=2&year=2017';"> presidentielle 2017 - 2eme Tour</li>
                    <li onclick="location.href ='<%=paths%>/scores/presidentielle?turn=1&year=2012';"> presidentielle 2012 - 1er Tour</li>
                    <li onclick="location.href = '<%=paths%>/scores/presidentielle?turn=2&year=2012';"> presidentielle 2012 - 2eme Tour</li>
                    <li onclick="location.href = '<%=paths%>/scores/presidentielle?turn=1&year=2007';"> presidentielle 2007 - 1er Tour</li>
                    <li onclick="location.href = '<%=paths%>/scores/presidentielle?turn=2&year=2007';">presidentielle 2007 - 2eme Tour</li>
                </ul>
                
                <li data-toggle="collapse" data-target="#Legislatives" class="collapsed active">
                    Législatives
                </li>
                <ul class="sub-menu collapse" id="Legislatives">
                    <li onclick="location.href = '<%=paths%>/scores/legislatives?turn=1&year=2017';"> législatives 2017 - 1er Tour</li>
                    <li onclick="location.href = '<%=paths%>/scores/legislatives?turn=2&year=2017';"> législatives 2017 - 2eme Tour</li>
                    <li onclick="location.href = '<%=paths%>/scores/legislatives?turn=1&year=2012';"> législatives 2012 - 1er Tour</li>
                    <li onclick="location.href = '<%=paths%>/scores/legislatives?turn=2&year=2012';"> législatives 2012 - 2eme Tour</li>
                    <li onclick="location.href = '<%=paths%>/scores/legislatives?turn=1&year=2007';"> législatives 2007 - 1er Tour</li>
                    <li onclick="location.href = '<%=paths%>/scores/legislatives?turn=2&year=2007';"> législatives 2007 - 2eme Tour</li>
                </ul>
            </ul>
            <li data-toggle="collapse" data-target="#bv" class="collapsed">
                <a><i class="fa fa-globe fa-lg"></i> Bureaux de votes <span class="arrow"></span></a>
            </li>
            <ul class="sub-menu collapse" id="bv">
                <li onclick="location.href = '<%=paths%>/voteoffices';">Tout les Bureaux de vote</li>
                <li onclick="location.href = '<%=paths%>/voteoffices/searchAdress' ;">Trouver un Bureau de vote</li>
                <li onclick="location.href = '<%=paths%>/voteoffices/arr';" >Bureaux de vote par arrondissement</li>
            </ul>
            <li onclick="location.href = '<%=paths%>/ProfileServlet';"> Profile</li>
                    
        </ul>
    </div>
</div>