/*@OneToMany = Existem várias ocorrências da variável com essa anotação para essa classe
@ManyToOne = Esta classe só pode ter uma dessa variável, mas a variavel pode ter várias dessa classe;*/
package br.jsf.model;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author lucas
 */
@Entity

public class PessoaJuridica extends  Pessoa implements Serializable {
 
     
    private String cnpj;
    private String razaoSocial;

    

   

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    
}
