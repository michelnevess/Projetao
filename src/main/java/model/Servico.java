/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author michel
 */
public class Servico {
 
    private int id;
    private String descricao;
    private double valor;
    private Date d_inicio, d_fim;
    private boolean pago = false;
    private Funcionario funcionario;
    private Cliente cliente;
    private Veiculo veiculo;

    public Servico(int id, String descricao, double valor, Date d_inicio, Date d_fim, boolean pago, Funcionario funcionario, Cliente cliente, Veiculo veiculo) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.d_inicio = d_inicio;
        this.d_fim = d_fim;
        this.pago = pago;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.veiculo = veiculo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Servico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getD_inicio() {
        return d_inicio;
    }

    public void setD_inicio(Date d_inicio) {
        this.d_inicio = d_inicio;
    }

    public Date getD_fim() {
        return d_fim;
    }

    public void setD_fim(Date d_fim) {
        this.d_fim = d_fim;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }    
    
}
