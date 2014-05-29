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
import rmtool.model.bean.Usuario;

/**
 *
 * @author jonimane
 */
public class UsuarioDAO
{
    public List<Usuario> listar()
    {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<Usuario> usuarios = s.createCriteria(Usuario.class).list();
        
        return usuarios;
    }
    
    public void criar (Usuario user){
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(user);
        s.getTransaction().commit();
 
   }
    public void salvar(Usuario user)
    {
        if( user.getId() == null )
        {
           criar(user);
        }
        else
        {
            editar(user);
        }
    }
    
    public void deletar (Usuario user){
        
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.delete(user);
        s.getTransaction().commit();
 
   }
    
    public void editar (Usuario user){
        
       Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.merge(user);
        s.getTransaction().commit();
 
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

    public Usuario validar( Usuario u ) {
        Session s;
        
        s = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        Criteria cri = s.createCriteria(Usuario.class);
        cri.add( Restrictions.eq( "login", u.getLogin() ) );
        cri.add( Restrictions.eq( "senha", u.getSenha() ) );
        
        List<Usuario> lista = cri.list();
        
        if( lista.size() > 0 )
        {
            return lista.get(0);
        }
            
        return null;
    }
}