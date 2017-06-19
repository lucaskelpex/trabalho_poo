
package br.jsf.controller;

import br.jsf.JpuUtil.JpaUtil;
import br.jsf.interfaces.InterfaceBeans;
import br.jsf.model.Cor;
import br.jsf.model.Marca;
import br.jsf.model.TipoCombustivel;
import br.jsf.model.TipoVeiculo;
import br.jsf.model.Veiculo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;

@ManagedBean
@ViewScoped
public class VeiculoController implements Serializable, InterfaceBeans{
    private Veiculo veiculo;
    private Veiculo veiculoSelect;
    private Marca marca = new Marca();
    
 
    private List<Veiculo> listVeiculos = new ArrayList<>();
    private List<Marca> listMarca = new ArrayList<>();
    private List<TipoCombustivel> listTipoCombustiveis = new ArrayList<>();
    private List<Cor> listColors = new ArrayList<>();
    private List<TipoVeiculo> listTipoVeiculos = new ArrayList<>();  
    private int tpPesquisa;
    private String desPesquisa;
    private EntityManager entityManager = JpaUtil.getEntityManager();
    /*Essas listas irão alimentar as combobox*/
    
    public VeiculoController(){
        veiculo = new Veiculo();
        veiculoSelect = new Veiculo();
        marca = new Marca();
        
        listMarca = entityManager.createQuery("From Marca").getResultList();
                listTipoCombustiveis = entityManager.createQuery("From TipoCombustivel").getResultList();
        listColors = entityManager.createQuery("From Cor").getResultList();
        listTipoVeiculos = entityManager.createQuery("From TipoVeiculo").getResultList();
        
    }
     

    public Veiculo getVeiculoSelect() {
        return veiculoSelect;
    }

    public void setVeiculoSelect(Veiculo veiculoSelect) {
        this.veiculoSelect = veiculoSelect;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public List<Veiculo> getListVeiculos() {
        return listVeiculos;
    }

    public void setListVeiculos(List<Veiculo> listVeiculos) {
        this.listVeiculos = listVeiculos;
    }

  

    public List<Marca> getListMarca() {
        return listMarca;
    }

    public void setListMarca(List<Marca> listMarca) {
        this.listMarca = listMarca;
    }

    public List<TipoCombustivel> getListTipoCombustiveis() {
        return listTipoCombustiveis;
    }

    public void setListTipoCombustiveis(List<TipoCombustivel> listTipoCombustiveis) {
        this.listTipoCombustiveis = listTipoCombustiveis;
    }

    public List<TipoVeiculo> getListTipoVeiculos() {
        return listTipoVeiculos;
    }

    public void setListTipoVeiculos(List<TipoVeiculo> listTipoVeiculos) {
        this.listTipoVeiculos = listTipoVeiculos;
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

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public List<Cor> getListColors() {
        return listColors;
    }

    public void setListColors(List<Cor> listColors) {
        this.listColors = listColors;
    }

     @Override
    public void gravar (){
        
        entityManager.getTransaction().begin();
        entityManager.merge(veiculo);
        entityManager.getTransaction().commit();
        veiculo = new Veiculo();

        pesquisar();
    }

    @Override
    public void excluir(){
     System.out.println("Veiculo id" + veiculoSelect.getId());
        System.out.println("veiculo + " + veiculoSelect.getModelo());

        Object tpv = (Veiculo) entityManager.merge(veiculoSelect);

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
                listVeiculos = entityManager.createQuery("From  Veiculo").getResultList();
                break;
            }
            case 2: {          
                listVeiculos = entityManager.createQuery("From  Veiculo  where modelo like'%"+desPesquisa+"%'  ").getResultList();
                break;
            }
            case 3: {                
                 listVeiculos  = entityManager.createQuery("From Veiculo where id = "+desPesquisa).getResultList();
                break;
            }

        }

    }
    
    
    
     
    
}
