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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import upec.groupe1.entities.Users;
import upec.groupe1.entities.Adresse;
import upec.groupe1.entities.Results;
import upec.groupe1.session.AdresseEJB;
import upec.groupe1.session.Email;
import upec.groupe1.session.Exceptions.NotFoundException;
import upec.groupe1.session.ResultsEJB;
import upec.groupe1.session.UserEJB;

/**
 *
 * @author drajasin
 */
public class MailServlet extends HttpServlet {

    private final String MAIL = "/SendMail";
    private final String MAILRESPONSE = MAIL + "/response";
    @EJB
    Email email;
    @EJB
    UserEJB userEJB;
    @EJB
    AdresseEJB adresseEJB;
    @EJB
    ResultsEJB resultsEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {

            String path = request.getServletPath();
            switch (path) {
                case MAIL: {
                    this.getServletContext().getRequestDispatcher("/WEB-INF/mail/MailForm.jsp").forward(request, response);
                    break;
                }

                case MAILRESPONSE: {
                    this.getServletContext().getRequestDispatcher("/WEB-INF/mail/MailForm.jsp").forward(request, response);
                    break;
                }
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
            String login = (String) session.getAttribute("user");
            String mail = "";
            String ncandidat = (String) request.getParameter("ncandidat");
            String pcandidat = (String) request.getParameter("pcandidat");
            String caption = (String) request.getParameter("caption");
            String year = (String) request.getParameter("year");
            String turn = (String) request.getParameter("turn");
            String numAdress = (String) request.getParameter("snum");
            String adress = (String) request.getParameter("adress");
            Users user = null;
            Long score=Long.valueOf(0);
            Adresse addr = new Adresse();
            try {
                user = userEJB.find(Users.class, login);
                mail = user.getMail();
                Map<String,Object> mapParam = new HashMap<>();
                mapParam.put("streetNum", Integer.valueOf(numAdress));
                mapParam.put("streetName", numAdress+" "+adress);
                List<Adresse> list = adresseEJB.findNamedQuery("Adresse.findNumberAndStreetName",mapParam,Adresse.class);
                if (!list.isEmpty())
                    addr = list.get(0);
                System.out.println(addr);
                String arr = addr.getAttachedZone().getVoteOffice().getNumber().split("-")[0];
                String num = addr.getAttachedZone().getVoteOffice().getNumber().split("-")[1];
                System.out.println(pcandidat+" "+ncandidat+" "+turn+" "+caption+" "+num+" "+Double.valueOf(arr)+" "+year);
                List<Results> lr = resultsEJB.getResults();
                System.out.println(lr);
                Results result =  lr.stream().filter(r -> (r.getCandidateFN().equals(ncandidat)&&r.getCandidateLN().equals(pcandidat)&&r.getTurn().equals(turn)&&r.getYearEl().equals(year))).findAny().get();
                System.out.println(result.getNbVoie()+" "+result.getNbExprime());
                score = result.getNbVoie()*100/result.getNbExprime();
            } catch (NotFoundException ex) {
                Logger.getLogger(MailServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            String path = request.getServletPath();
            switch (path) {
                case MAIL: {
                    
                    System.err.println("PRINT mail" + ncandidat + pcandidat + adress);
                    
                    String message = "Bonjour Mr. "+user.getName() + " "+user.getLastName()+
                            "\n voici vos résultat pour le candidat "+ncandidat+" "+pcandidat+" \n son score est de : \n"
                            +score+"% au tour n° "+turn+" des élections "+caption+" de "+year ;

                    try{
                        email.setBody(message);
                        email.setTo(mail);
                        email.setFrom(mail);
                        email.setSubject("Resultat de "+ncandidat+" "+pcandidat);
                        email.send();
                    } catch (MessagingException ex) {
                        Logger.getLogger(MailServlet.class.getName()).log(Level.SEVERE, null, ex);
                        System.err.println("Impossible d'envoyer le mail" );
                    }
                    this.getServletContext().getRequestDispatcher("/WEB-INF/mail/MailForm.jsp").forward(request, response);
                    break;
                }

                case MAILRESPONSE: {
                    this.getServletContext().getRequestDispatcher("/WEB-INF/mail/MailForm.jsp").forward(request, response);
                    break;
                }
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
