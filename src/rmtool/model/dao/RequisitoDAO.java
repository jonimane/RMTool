/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.model.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import rmtool.model.bean.Requisito;

/**
 *
 * @author jonimane
 */
public class RequisitoDAO {
    public void criar( Requisito r ) throws Exception
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(r);
        s.getTransaction().commit();
    }
    
    public void alterar( Requisito r ) throws Exception
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.merge(r);
        s.getTransaction().commit();
    }
    
    public void excluir( Requisito r ) throws Exception
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.delete(r);
        s.getTransaction().commit();
    }
    
    public List<Requisito> listar() throws Exception
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Requisito> requisitos = s.createCriteria(Requisito.class).list();
        
        return requisitos;
    }
    
    public Requisito procurarId(int id) throws Exception
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        Criteria cri = s.createCriteria(Requisito.class);
        cri.add(Restrictions.eq("id", id));
        
        return (Requisito) cri.list().get(0);
    }
}
