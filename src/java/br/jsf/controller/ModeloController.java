/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.jsf.controller;

import br.jsf.JpuUtil.JpaUtil;
import br.jsf.interfaces.InterfaceBeans;
import br.jsf.model.Marca;
import br.jsf.model.Modelo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;


@ManagedBean
@ViewScoped


public class ModeloController implements Serializable, InterfaceBeans{
private Modelo modelo;
private Modelo modeloSelect;
private Marca marca = new Marca();
private List<Modelo> listModelo = new ArrayList<>();
private List<Marca> listMarcas = new ArrayList<>();
  private int tpPesquisa;
    private String desPesquisa;

    private EntityManager entityManager = JpaUtil.getEntityManager();
    public ModeloController(){
        modelo = new Modelo();
        modeloSelect = new Modelo();
        listModelo = entityManager.createQuery("From Modelo").getResultList();
        listMarcas = entityManager.createQuery("From Marca").getResultList();
        
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public List<Marca> getListMarcas() {
        return listMarcas;
    }

    public void setListMarcas(List<Marca> listMarcas) {
        this.listMarcas = listMarcas;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Modelo getModeloSelect() {
        return modeloSelect;
    }

    public void setModeloSelect(Modelo modeloSelect) {
        this.modeloSelect = modeloSelect;
    }

    public List<Modelo> getListModelo() {
        return listModelo;
    }

    public void setListModelo(List<Modelo> listModelo) {
        this.listModelo = listModelo;
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
        entityManager.merge(modelo);
        entityManager.getTransaction().commit();
        modelo = new Modelo();

        pesquisar();
    }

    @Override
     public void excluir(){
     System.out.println("Modelo id" + modeloSelect.getId());
        System.out.println("Modelo + " + modeloSelect.getNomeModelo());

        Object tpv = (Modelo) entityManager.merge(modeloSelect);

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
                listModelo = entityManager.createQuery("From  Modelo").getResultList();
                break;
            }
            case 2: {          
                listModelo = entityManager.createQuery("From  Modelo  where nomeModelo like'%"+desPesquisa+"%'  ").getResultList();
                break;
            }
            case 3: {                
                 listModelo  = entityManager.createQuery("From Modelo where id = "+desPesquisa).getResultList();
                break;
            }

        }
   }
}


