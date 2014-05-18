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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jonimane
 */
@Entity
@Table(name = "projeto")
public class Projeto implements Serializable {
    private Integer id;
    private String nome;
    private String descricao;
    private Set<Usuario> participantes = new HashSet<>(0);
    private Set<Requisito> requisitos = new HashSet<>(0);
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Lob
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @ManyToMany(mappedBy = "participaProjetos")
    public Set<Usuario> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Set<Usuario> participantes) {
        this.participantes = participantes;
    }

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "projeto"
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
        
        if( u instanceof Projeto )
        {
            return u.equals( u );
        }
        
        return false;
    }
    
    public boolean equals( Projeto p )
    {
        return p.getId() == getId();
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }
}
