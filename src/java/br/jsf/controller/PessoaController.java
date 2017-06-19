

package br.jsf.controller;

import br.jsf.model.Contato;
import br.jsf.model.Endereco;
import br.jsf.model.Pessoa;
import br.jsf.model.PessoaFisica;
import br.jsf.model.PessoaJuridica;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class PessoaController implements Serializable {
    private Pessoa pessoa;
    private Pessoa pessoaSelect;
    private List<Pessoa> listPessoas = new ArrayList<>();
    private PessoaFisica pessoaFisica;
    private PessoaJuridica pessoaJuridica;
    private Endereco endereco;
    private Contato contato;
    

    public PessoaController(){
        
pessoa = new Pessoa();


}
}

