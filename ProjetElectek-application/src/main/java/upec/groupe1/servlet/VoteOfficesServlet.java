/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.servlet;

import java.io.IOException;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import upec.groupe1.entities.Adresse;
import upec.groupe1.entities.VoteOffices;
import upec.groupe1.session.AdresseEJB;
import upec.groupe1.session.VoteOfficeEJB;

/**
 *
 * @author alpi
 */
public class VoteOfficesServlet extends HttpServlet {
    private final String VOTE_OFFICE = "/voteoffices";
    private final String VOTE_OFFICE_BY_ARR = VOTE_OFFICE+"/arr";
    private final String VOTE_OFFICE_BY_ADRESS = VOTE_OFFICE+"/searchAdress";

    public static final String CHAMP_NUMERO = "numero";
    public static final String CHAMP_RUE = "rue";
    public static final String CHAMP_CP = "cp";
    public static final String CHAMP_PAYS = "pays";
    
    
    @EJB
    private VoteOfficeEJB officesEJB;
    @EJB
    private AdresseEJB adressEJB;
    
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
            
        Map<String, String[]> params = request.getParameterMap();
        String path = request.getServletPath();
        switch(path){
            case VOTE_OFFICE : {
                List<VoteOffices> liste = officesEJB.findNamedQuery("VoteOffices.findAll", VoteOffices.class);
                request.setAttribute("ListeVoteOffices", liste);
                this.getServletContext().getRequestDispatcher( "/WEB-INF/voteOffices/AllData.jsp" ).forward( request, response );
                break;
            }

            case VOTE_OFFICE_BY_ARR : {

                Map<String, List<VoteOffices>> mapVoteOffices= officesEJB.getVoteOfficesByArrondissement();
                request.setAttribute("MapVoteOffices", mapVoteOffices);    
                this.getServletContext().getRequestDispatcher( "/WEB-INF/voteOffices/DataByArr.jsp" ).forward( request, response );
                break;
            }

            case VOTE_OFFICE_BY_ADRESS : {
                this.getServletContext().getRequestDispatcher( "/WEB-INF/voteOffices/SearchVoteOfficeFromAdress.jsp" ).forward( request, response );
                break;
            }
            default :  
                   response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String path = request.getServletPath();
        switch(path){
            case VOTE_OFFICE_BY_ADRESS : {
                
                String numero = request.getParameter( CHAMP_NUMERO );
                String rue = request.getParameter( CHAMP_RUE );
                String cp = request.getParameter( CHAMP_CP );
                String pays = request.getParameter( CHAMP_PAYS );
                try{
                    System.out.println(numero+rue+cp+pays);
                    Map<String, Integer> mapConvert = validate(numero,rue,cp,pays);
                    Map<String,Object> sqlPara = new HashMap<>();
                    mapConvert.forEach((k,v)->{
                        if(k.equalsIgnoreCase(numero)){
                            sqlPara.put("streetNum", v);
                        }else if (k.equalsIgnoreCase(rue)){
                            sqlPara.put("streetName",v);
                        }else if (k.equals(cp)){
                            
                        }
                    });
                    sqlPara.put("streetName",rue);
                    System.out.println("DILAN  -  " + sqlPara );
                    List<Adresse> a = adressEJB.findNamedQuery("Adresse.findNumberAndStreetName", sqlPara, Adresse.class);
                    if(!a.isEmpty()){
                        request.setAttribute("adresse", a);
                    }else{
                        System.out.println("empty");
                    }
                    System.out.println(a);
                    
                }
                catch(Exception e){
                    e.printStackTrace();
                    request.setAttribute("message", "Erreur : Merci de vérifier les informations saisies.");
                }
            break;
            }
            default :  
                   response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Map<String , Integer> validate(String numero, String rue, String cp, String pays) throws Exception {
        
        if(numero == null  || rue== null || cp== null){
            throw new Exception("Argument Invalid");
        }
        int num = Integer.parseInt(numero);
        int codePostale = Integer.parseInt(cp);
        Map<String,Integer> map = new HashMap<>();
        map.put(numero, num);
        map.put(cp,codePostale);
        return map;
        
    }

}
