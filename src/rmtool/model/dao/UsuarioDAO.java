/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.model.dao;

import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import rmtool.model.bean.Requisito;
import rmtool.model.bean.Usuario;

/**
 *
 * @author jonimane
 */
public class UsuarioDAO {

    public void add (Usuario user){
        Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        
        try
        {
        
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
       
        
     }catch(Exception ex){
    
      }
   }
    
    
    public void deletar (Usuario user){
        
        Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        try{
        
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
       
        
     }catch(Exception ex){
        
    
      }
   }
    
    public void editar (Usuario user){
        
        Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession();//Verificar
        
        try{
        
        session.beginTransaction();
        session.merge(user);
        session.getTransaction().commit();
       
        
     }catch(Exception ex){
        
    
      }
        session.close();
   }
    
    public List<Usuario> lista(Usuario user)throws Exception
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Usuario> listarUsuario = s.createCriteria(Usuario.class).list();
        
        return listarUsuario;
    }
    
    public Usuario procurarId(int id) throws Exception
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        Criteria cri = s.createCriteria(Usuario.class);
        cri.add(Restrictions.eq("id", id));
        
        return (Usuario) cri.list().get(0);
    }
}