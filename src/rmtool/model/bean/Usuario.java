/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.model.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jonimane
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    private Integer id;
    private String nome;
    private String login;
    private String senha;
    private String email;
    private Set<Projeto> participaProjetos = new HashSet<>(0);
    private Set<Requisito> requisitos = new HashSet<>(0);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(nullable = false, unique = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(nullable = false)
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToMany
    @JoinTable(
        name = "participa",
        joinColumns = {
            @JoinColumn(name = "usuario_id")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "projeto_id")
        }
    )
    public Set<Projeto> getParticipaProjetos() {
        return participaProjetos;
    }

    public void setParticipaProjetos(Set<Projeto> participaProjetos) {
        this.participaProjetos = participaProjetos;
    }

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "usuario"
    )
    public Set<Requisito> getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(Set<Requisito> requisitos) {
        this.requisitos = requisitos;
    }
    
    @Override
    public boolean equals( Object u )
    {
        if( u == this )
        {
            return true;
        }
        
        if( u instanceof Usuario )
        {
            return u.equals( u );
        }
        
        return false;
    }
    
    public boolean equals( Usuario u )
    {
        return u.getId() == getId();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }
}
