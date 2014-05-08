/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.model.dao;

import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
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
        JOptionPane.showMessageDialog(null,"Usuário cadastrado com sucesso");
        
     }catch(Exception ex){
        JOptionPane.showMessageDialog(null,"Erro ao cadastrar o Usuário ");
    
      }
   }
    
    
    public void deletar (Usuario user){
        
        Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        try{
        
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        JOptionPane.showMessageDialog(null,"Usuário deletado com sucesso");
        
     }catch(Exception ex){
        JOptionPane.showMessageDialog(null,"Erro ao deletar o Usuário ");
    
      }
   }
    
    public void editar (Usuario user){
        
        Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession();//Verificar
        
        try{
        
        session.beginTransaction();
        session.merge(user);
        session.getTransaction().commit();
        JOptionPane.showMessageDialog(null,"Usuário editado com sucesso");
        
     }catch(Exception ex){
        JOptionPane.showMessageDialog(null,"Erro ao editado o Usuário ");
    
      }
        session.close();
   }
    
    public void listar (Usuario user)
    {
        Session session = HibernateUtilDAO.getSessionFactory().getCurrentSession();
        
        try
        {
            session.beginTransaction();
            List<Usuario> listarUsuario = session.createQuery("from usuario").list();

            for(Usuario u : listarUsuario)
            {
                System.out.println("Nome " +u.getNome());
                System.out.println("Email"+u.getEmail());
                System.out.println("Login"+u.getLogin());
            }
            JOptionPane.showMessageDialog(null,"Usuário Listado com sucesso");
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Listar o Usuário ");
        }
    }
}