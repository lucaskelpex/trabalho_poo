package br.jsf.controller;

import br.jsf.JpuUtil.JpaUtil;
import br.jsf.interfaces.InterfaceBeans;
import br.jsf.model.TipoCombustivel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class TipoCombustivelController implements Serializable, InterfaceBeans {

    private TipoCombustivel tipoCombustivel;
    private TipoCombustivel tipoCombustivelSelect;
    private List<TipoCombustivel> listTiposCombustiveis = new ArrayList<>();
    private int tpPesquisa;
    private String desPesquisa;
    private EntityManager entityManager = JpaUtil.getEntityManager();
    
    public TipoCombustivelController(){
        tipoCombustivel = new TipoCombustivel();
        tipoCombustivelSelect = new TipoCombustivel();
        listTiposCombustiveis = entityManager.createQuery("From TipoCombustivel").getResultList();
    }
    
    @Override
    public void gravar (){
        
        entityManager.getTransaction().begin();
        entityManager.merge(tipoCombustivel);
        entityManager.getTransaction().commit();
        tipoCombustivel = new TipoCombustivel();

        pesquisar();
    }
    
    
    @Override
    public void excluir(){
     System.out.println("TipoCombustivel id" + tipoCombustivelSelect.getId());
        System.out.println("tipoCombustivel + " + tipoCombustivelSelect.getNmTipoCombustivel());

        Object tpv = (TipoCombustivel) entityManager.merge(tipoCombustivelSelect);

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
                listTiposCombustiveis = entityManager.createQuery("From  TipoCombustivel").getResultList();
                break;
            }
            case 2: {          
                listTiposCombustiveis = entityManager.createQuery("From  TipoCombustivel  where nmTipoCombustivel like'%"+desPesquisa+"%'  ").getResultList();
                break;
            }
            case 3: {                
                 listTiposCombustiveis  = entityManager.createQuery("From  TipoCombustivel where id = "+desPesquisa).getResultList();
                break;
            }

        }

    }

    public TipoCombustivel getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(TipoCombustivel tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public TipoCombustivel getTipoCombustivelSelect() {
        return tipoCombustivelSelect;
    }

    public void setTipoCombustivelSelect(TipoCombustivel tipoCombustivelSelect) {
        this.tipoCombustivelSelect = tipoCombustivelSelect;
    }

    public List<TipoCombustivel> getListTiposCombustiveis() {
        return listTiposCombustiveis;
    }

    public void setListTipoCombustivel(List<TipoCombustivel> listTiposCombustiveis) {
        this.listTiposCombustiveis = listTiposCombustiveis;
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
