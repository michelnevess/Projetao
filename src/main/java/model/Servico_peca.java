/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author michel
 */
public class Servico_peca {
    
    private int id, quantidade;
    private Servico servico;
    private Peca peca;

    public Servico_peca(int id, int quantidade, Servico servico, Peca peca) {
        this.id = id;
        this.quantidade = quantidade;
        this.servico = servico;
        this.peca = peca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }
    
    
    
    
}
