/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.model.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import rmtool.model.NomeEditavel;
import rmtool.model.Prioridade;
import rmtool.model.Situacao;
import rmtool.model.TipoRequisito;
import rmtool.model.dao.RequisitoDAO;

/**
 *
 * @author jonimane
 */
@Entity
@Table(name = "requisito")
public class Requisito implements Serializable, NomeEditavel {
    private Integer id;
    private String nome;
    private String descricao;
    private Byte prioridade;
    private Byte situacao;
    private Integer versao;
    private Byte tipo;
    private Date criacao;
    private Projeto projeto;
    private Usuario usuario;

    @Id
    @GeneratedValue
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

    public Byte getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        if( prioridade != null )
        {
            this.prioridade = prioridade.toByte();
        }
    }

    public void setPrioridade(Byte prioridade) {
        this.prioridade = prioridade;
    }

    public Byte getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        if( situacao != null )
        {
            this.situacao = situacao.toByte();
        }
    }

    public void setSituacao(Byte situacao) {
        this.situacao = situacao;
    }

    public Integer getVersao() {
        return versao;
    }

    public void setVersao(Integer versao) {
        this.versao = versao;
    }

    public Byte getTipo() {
        return tipo;
    }

    public void setTipo(TipoRequisito tipo) {
        if( tipo != null )
        {
            this.tipo = tipo.toByte();
        }
    }

    public void setTipo(Byte tipo) {
        this.tipo = tipo;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Transient
    @Override
    public String getNomeEditavel() {
        return getNome();
    }

    @Override
    public NomeEditavel setNomeEditavel(String nome) {
        setNome(nome);
        
        return this;
    }

    @Override
    public boolean salvar() {
        try {
            RequisitoDAO requisitoDAO = new RequisitoDAO();
            requisitoDAO.alterar(this);
        }
        catch( Exception e )
        {
            return false;
        }
        
        return true;
    }
}
