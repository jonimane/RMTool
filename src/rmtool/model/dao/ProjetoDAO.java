/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.model.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import rmtool.model.bean.Projeto;
import rmtool.model.bean.Usuario;

/**
 *
 * @author jonimane
 */
public class ProjetoDAO {
    public void criar( Projeto p )
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(p);
        s.getTransaction().commit();
    }
    
    public void alterar( Projeto p )
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.merge(p);
        s.getTransaction().commit();
    }
    
    public void salvar( Projeto p )
    {
        if( p.getId() == null )
        {
            criar(p);
        }
        else
        {
            alterar(p);
        }
    }
    
    public void excluir( Projeto p )
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.delete(p);
        s.getTransaction().commit();
    }
    
    public List<Projeto> listar()
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Projeto> projetos = s.createCriteria(Projeto.class).list();
        
        return projetos;
    }
    
    public Projeto procurarId(int id)
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        Criteria cri = s.createCriteria(Projeto.class);
        cri.add(Restrictions.eq("id", id));
        
        return (Projeto) cri.list().get(0);
    }
    
    public Projeto buscarProjeto(String Nome)
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        Criteria cri = s.createCriteria(Projeto.class);
        cri.add(Restrictions.eq("nome", Nome));
        
        //List list = s.createCriteria(Projeto.class).add(Restrictions.eq("nome", Nome)).list();
        
        return (Projeto) cri.list().get(0);
    }
    
    public List<Projeto> procurar(Usuario u)
    {
        List<Projeto> projetos = listar();
        List<Projeto> res = new ArrayList<>();
        
        for (Projeto projeto : projetos) {
            if( projeto.getParticipantes().contains( u ) )
            {
                res.add(projeto);
            }
        }
        
        return res;
    }
}
