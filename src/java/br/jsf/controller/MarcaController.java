package br.jsf.controller;

import br.jsf.JpuUtil.JpaUtil;
import br.jsf.interfaces.InterfaceBeans;
import br.jsf.model.Marca;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;


@ManagedBean
@ViewScoped
public class MarcaController implements Serializable, InterfaceBeans {

    private Marca marca;
    private Marca marcaSelect;
    private List<Marca> listMarcas = new ArrayList<>();

    private int tpPesquisa;
    private String desPesquisa;

    private EntityManager entityManager = JpaUtil.getEntityManager();

    public MarcaController() {
   
        marca = new Marca();
        marcaSelect = new Marca();
        listMarcas = entityManager.createQuery("From Marca").getResultList();

    }

    @Override
    public void gravar() {

        entityManager.getTransaction().begin();
        entityManager.merge(marca);
        entityManager.getTransaction().commit();
        marca = new Marca();

        pesquisar();

    }

    @Override
    public void excluir() {

        System.out.println("Marca id" + marcaSelect.getId());
        System.out.println("marca + " + marcaSelect.getNmMarca());

        Object c = (Marca) entityManager.merge(marcaSelect);

        entityManager.getTransaction().begin();
        entityManager.remove(c);
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
                listMarcas = entityManager.createQuery("From  Marca").getResultList();
                break;
            }
            case 2: {          
                listMarcas = entityManager.createQuery("From  Marca  where nmMarca like'%"+desPesquisa+"%'  ").getResultList();
                break;
            }
            case 3: {                
                 listMarcas  = entityManager.createQuery("From  Marca where id = "+desPesquisa).getResultList();
                break;
            }

        }

    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Marca getMarcaSelect() {
        return marcaSelect;
    }

    public void setMarcaSelect(Marca marcaSelect) {
        this.marcaSelect = marcaSelect;
    }

    public List<Marca> getListMarcas() {
        return listMarcas;
    }

    public void setListMarcas(List<Marca> listMarcas) {
        this.listMarcas = listMarcas;
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
