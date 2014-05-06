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
import rmtool.model.bean.Projeto;

/**
 *
 * @author jonimane
 */
public class ProjetoDAO {
    public void criar( Projeto p ) throws Exception
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(p);
        s.getTransaction().commit();
    }
    
    public void alterar( Projeto p ) throws Exception
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.merge(p);
        s.getTransaction().commit();
    }
    
    public void excluir( Projeto p ) throws Exception
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.delete(p);
        s.getTransaction().commit();
    }
    
    public List<Projeto> listar() throws Exception
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Projeto> projetos = s.createCriteria(Projeto.class).list();
        
        return projetos;
    }
    
    public Projeto procurarId(int id) throws Exception
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        Criteria cri = s.createCriteria(Projeto.class);
        cri.add(Restrictions.eq("id", id));
        
        return (Projeto) cri.list().get(0);
    }
}
