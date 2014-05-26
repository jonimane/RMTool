/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.model.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import rmtool.model.bean.Projeto;
import rmtool.model.bean.Requisito;

/**
 *
 * @author jonimane
 */
public class RequisitoDAO {
    public void criar( Requisito r )
    {
        r.setCriacao( Calendar.getInstance().getTime() );
        
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(r);
        s.getTransaction().commit();
    }
    
    public void alterar( Requisito r )
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.merge(r);
        s.getTransaction().commit();
    }
    
    public void salvar( Requisito r )
    {
        if( r.getId() == null)
        {
            criar(r);
        }
        else
        {
            alterar(r);
        }
    }
    
    public void excluir( Requisito r )
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.delete(r);
        s.getTransaction().commit();
    }
    
    public List<Requisito> listar()
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Requisito> requisitos = s.createCriteria(Requisito.class).list();
        
        return requisitos;
    }
    
    public Requisito procurarId(int id)
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        Criteria cri = s.createCriteria(Requisito.class);
        cri.add(Restrictions.eq("id", id));
        
        return (Requisito) cri.list().get(0);
    }
    
    public List<Requisito> procurar( Projeto p )
    {
        List<Requisito> requisitos = listar();
        List<Requisito> res = new ArrayList<>();
        
        for (Requisito requisito : requisitos) {
            if( requisito.getProjeto().equals( p ) ) {
                res.add(requisito);
            }
        }
        
        return res;
    }
}
