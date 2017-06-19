/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.jsf.controller;

import br.jsf.JpuUtil.JpaUtil;
import br.jsf.interfaces.InterfaceBeans;
import br.jsf.model.Locacao;
import br.jsf.model.Pessoa;
import br.jsf.model.Veiculo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * 
 * @author Lucas Juan 
 */
public class LocacaoController implements Serializable,InterfaceBeans {
    private Locacao locacao;
    private Locacao locacaoSelect;
    private List<Pessoa> listPessoas = new ArrayList<>();
    private List<Veiculo> listVeiculos = new ArrayList<>();
    private List<Locacao> listLocacao = new ArrayList<>();

    public List<Locacao> getListLocacao() {
        return listLocacao;
    }

    public void setListLocacao(List<Locacao> listLocacao) {
        this.listLocacao = listLocacao;
    }
    private int tpPesquisa;
    private String desPesquisa;
    private EntityManager entityManager = JpaUtil.getEntityManager();
    public LocacaoController(){
        locacao = new Locacao();
        locacaoSelect = new Locacao();
        listPessoas = entityManager.createQuery("From Pessoa").getResultList();
        listVeiculos = entityManager.createQuery("From Veiculo").getResultList();
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public Locacao getLocacaoSelect() {
        return locacaoSelect;
    }

    public void setLocacaoSelect(Locacao locacaoSelect) {
        this.locacaoSelect = locacaoSelect;
    }

    public List<Pessoa> getListPessoas() {
        return listPessoas;
    }

    public void setListPessoas(List<Pessoa> listPessoas) {
        this.listPessoas = listPessoas;
    }

    public List<Veiculo> getListVeiculos() {
        return listVeiculos;
    }

    public void setListVeiculos(List<Veiculo> listVeiculos) {
        this.listVeiculos = listVeiculos;
    }

    public int getTpPesquisa() {
        return tpPesquisa;
    }

    public void setTpPesquisa(int tpPesquisa) {
        this.tpPesquisa = tpPesquisa;
    }

    public String getDesPesquisa() {
        return desPesquisa;
    }

    public void setDesPesquisa(String desPesquisa) {
        this.desPesquisa = desPesquisa;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    

    @Override
     public void gravar (){
        
        entityManager.getTransaction().begin();
        entityManager.merge(locacao);
        entityManager.getTransaction().commit();
        locacao = new Locacao();

        pesquisar();
    }

    @Override
     public void excluir(){
     System.out.println("Locacao id" + locacaoSelect.getId());
        System.out.println("locacao + " + locacaoSelect.getPessoa() + locacaoSelect.getVeiculo() + locacaoSelect.getDataLocacao() + locacaoSelect.getDataDevolucao());

        Object tpv = (Locacao) entityManager.merge(locacaoSelect);

        entityManager.getTransaction().begin();
        entityManager.remove(tpv);
        entityManager.getTransaction().commit();

        pesquisar();
    }

    @Override
    public void alterar() {
       
    }

    @Override
    public void pesquisar() {

        switch (tpPesquisa) {

            case 1: {
                listLocacao = entityManager.createQuery("From  Locacao").getResultList();
                break;
            }
            case 2: {          
                listLocacao = entityManager.createQuery("From  Locacao  where pessoa like'%"+desPesquisa+"%'  ").getResultList();
                break;
            }
            case 3: {                
                 listLocacao  = entityManager.createQuery("From Locacao where id = "+desPesquisa).getResultList();
                break;
            }    
            case 4: {                
                 listLocacao  = entityManager.createQuery("From Locacao where veiculo = "+desPesquisa).getResultList();
                break;
            }

        }

    

}

}
