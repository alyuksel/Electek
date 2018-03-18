/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upec.groupe1.session;


import com.google.gson.Gson;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.Query;
import upec.groupe1.entities.VoteOffices;
import upec.groupe1.session.Exceptions.NotFoundException;
import upec.groupe1.tools.Tools;

/**
 *
 * @author drajasin
 */
@Stateless
public class VoteOfficeEJB extends ConcretEJB<VoteOffices> {

    
    public void importFromAPI() {
        System.out.println("DEBUG - Entrer Import VoteOffices");
        String json = Tools.getResults("https://opendata.paris.fr/api/records/1.0/search/?dataset=bureaux-de-votes&rows=-1&facet=lib&facet=cp");
            Map<String,Object> bv = new Gson().fromJson(json, Map.class);
            
        
            List<Map<String,Object>> records = (List<Map<String,Object>>) bv.get("records");
        
            for (Map<String,Object> o : records){
                Map<String,Object> infos = (Map<String,Object>) o.get("fields");
                VoteOffices vo = new VoteOffices();
                vo.setCaption((String)infos.get("lib"));
                vo.setAdress((String) infos.get("adresse"));
                vo.setNumber((String)infos.get("id_bv"));
                vo.setPostalCode((String)infos.get("cp"));
                super.create(vo);
            }
            System.out.println("DEBUG - Sortie Import VoteOffices");
        
        
         //To change body of generated methods, choose Tools | Templates.
    }
    public List<VoteOffices> filtredVoteOffices (Map<String,Object> para){
        Query q = em.createQuery("SELECT v FROM VoteOffices v WHERE v.adress LIKE UPPER(:adress) and v.number LIKE :number AND v.caption LIKE :caption");
        para.forEach((parat,value)->{
            if(value == null)value="";
            q.setParameter(parat,"%"+value+"%");
        });
        return q.getResultList();
    }
    public VoteOffices findVoteOffice(int numBV, int arr) throws NotFoundException{
        List<VoteOffices> result = em.createNamedQuery("VoteOffices.findByNumber",VoteOffices.class)
                .setParameter("number", arr+"-"+numBV)
                .getResultList();
        if(! result.isEmpty()) {
            return result.get(0);
        }
        throw new NotFoundException("Bureau de vote non trouvé");
    }
    
    public Map<String, List<VoteOffices>> getVoteOfficesByArrondissement() {
        List<VoteOffices> voteOffices = findNamedQuery("VoteOffices.findAll");
        return voteOffices.stream().collect(Collectors.groupingBy(VoteOffices::extractArr));  
    }
}