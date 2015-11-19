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
    private int servico;
    private Peca peca;
    private double valor, parcial;

    public Servico_peca(int id, int quantidade, int servico, Peca peca, double valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.servico = servico;
        this.peca = peca;
        this.valor = valor;
        this.parcial = valor * quantidade;
    }

    public Servico_peca() {
        
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
        this.parcial = this.valor * quantidade;
    }

    public int getServico() {
        return servico;
    }

    public void setServico(int servico) {
        this.servico = servico;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
        this.parcial = valor * this.quantidade;
    }

    public double getParcial() {
        return parcial;
    }
    
        
}
