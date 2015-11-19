/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author michel
 */
public class Historico {
    private int id, pk;
    private String nome_campo, nome_tabela, valor_antigo, valor_novo, operacao;
    private Date data_modificacao;
    
    public Historico(int id, int pk, String nome_campo, String nome_tabela, String valor_antigo, String valor_novo, Date data_modificacao, String operacao) {
        this.id = id;
        this.pk = pk;
        this.nome_campo = nome_campo;
        this.nome_tabela = nome_tabela;
        this.valor_antigo = valor_antigo;
        this.valor_novo = valor_novo;
        this.data_modificacao = data_modificacao;
        this.operacao = operacao;
    }

    public Historico() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getNome_campo() {
        return nome_campo;
    }

    public void setNome_campo(String nome_campo) {
        this.nome_campo = nome_campo;
    }

    public String getNome_tabela() {
        return nome_tabela;
    }

    public void setNome_tabela(String nome_tabela) {
        this.nome_tabela = nome_tabela;
    }

    public String getValor_antigo() {
        return valor_antigo;
    }

    public void setValor_antigo(String valor_antigo) {
        this.valor_antigo = valor_antigo;
    }

    public String getValor_novo() {
        return valor_novo;
    }

    public void setValor_novo(String valor_novo) {
        this.valor_novo = valor_novo;
    }

    public Date getData_modificacao() {
        return data_modificacao;
    }

    public void setData_modificacao(Date data_modificacao) {
        this.data_modificacao = data_modificacao;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }
    
    
}
