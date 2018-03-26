/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.session;

import javax.ejb.Stateless;
import upec.groupe1.entities.Users;
import upec.groupe1.session.Exceptions.NotFoundException;

/**
 *
 * @author adam
 */
@Stateless
public class UserEJB extends ConcretEJB<Users>{
   
    public void changeStatus(String login,String right) throws NotFoundException{
        Users user = super.find(Users.class, login);
        user.setType(right);
        super.update(user);
    }
    public void changePassword(String login,String password) throws NotFoundException{
        
        Users user = super.find(Users.class, login);
        user.setPassword(password);
        super.update(user);
    }
    
    public void changeMail(String login, String mail) throws NotFoundException{
        Users user = super.find(Users.class, login);
        user.setMail(mail);
        super.update(user);
    }
}
