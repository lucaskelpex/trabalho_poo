package br.jsf.controller;

import br.jsf.JpuUtil.JpaUtil;
import br.jsf.interfaces.InterfaceBeans;
import br.jsf.model.TipoVeiculo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class TipoVeiculoController implements Serializable, InterfaceBeans {

    private TipoVeiculo tipoVeiculo;
    private TipoVeiculo tipoVeiculoSelect;
    private List<TipoVeiculo> listTiposVeiculos = new ArrayList<>();
    private int tpPesquisa;
    private String desPesquisa;
    private EntityManager entityManager = JpaUtil.getEntityManager();
    
    public TipoVeiculoController(){
        tipoVeiculo = new TipoVeiculo();
        tipoVeiculoSelect = new TipoVeiculo();
        listTiposVeiculos = entityManager.createQuery("From TipoVeiculo").getResultList();
    }
    
    @Override
    public void gravar (){
    entityManager.getTransaction().begin();
        entityManager.merge(tipoVeiculo);
        entityManager.getTransaction().commit();
        tipoVeiculo = new TipoVeiculo();

        pesquisar();
    }
    
    @Override
    public void excluir(){
     System.out.println("TipoVeiculo id" + tipoVeiculoSelect.getId());
        System.out.println("tipoVeiculo + " + tipoVeiculoSelect.getNmTipoVeiculo());

        Object tpv = (TipoVeiculo) entityManager.merge(tipoVeiculoSelect);

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
                listTiposVeiculos = entityManager.createQuery("From  TipoVeiculo").getResultList();
                break;
            }
            case 2: {          
                listTiposVeiculos = entityManager.createQuery("From  TipoVeiculo  where nmTipoVeiculo like'%"+desPesquisa+"%'  ").getResultList();
                break;
            }
            case 3: {                
                 listTiposVeiculos  = entityManager.createQuery("From  TipoVeiculo where id = "+desPesquisa).getResultList();
                break;
            }

        }

    }

    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public TipoVeiculo getTipoVeiculoSelect() {
        return tipoVeiculoSelect;
    }

    public void setTipoVeiculoSelect(TipoVeiculo tipoVeiculoSelect) {
        this.tipoVeiculoSelect = tipoVeiculoSelect;
    }

    public List<TipoVeiculo> getListTiposVeiculos() {
        return listTiposVeiculos;
    }

    public void setListTiposVeiculos(List<TipoVeiculo> listTiposVeiculos) {
        this.listTiposVeiculos = listTiposVeiculos;
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
    

}
