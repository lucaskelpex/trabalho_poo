/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jsf.model;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author lucas
 */
@Entity

public class PessoaFisica extends Pessoa implements Serializable {
 

  

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    private String cpf;
    
}
