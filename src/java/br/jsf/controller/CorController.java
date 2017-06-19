/*As classe chamadas de controllers são basicamente as ações do determinado objeto, diferente dos models,
como o nome já diz são os modelos, ou seja contém os atributos */
package br.jsf.controller;

import br.jsf.JpuUtil.JpaUtil;
import br.jsf.interfaces.InterfaceBeans;
import br.jsf.model.Cor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.faces.bean.ViewScoped;


@ManagedBean
/*intermediar a comunicação entre as páginas (componentes do JSF) e nosso modelo*/
@ViewScoped
/*mantem a sessão do manege manege bean aberta enquanto o usuario esta na pagina*/
public class CorController implements Serializable, InterfaceBeans {
    /*public, private e protected são nossos controles de acesso
    private só pode ser acessado pela propria classe
   public pode ser acessado por qualquer parte do projeto
    protected pode ser acessado pelas classes do pacote e pertencentes da herança*/
/* a implementação da serialização é para os casos em que os objetos serão transmitidos em arquivos ou pela rede
    basicamente permite a utilização do mesmo em diferentes locais do código*/
    private Cor cor;
    
    /*para utilizar uma classe deve instancear a mesma (ou seja criar seu objeto)*/
    private Cor corSelect;
    private List<Cor> listCores = new ArrayList<>();
    private int tpPesquisa;
    /*tp pesquise referese ao tipo, geral,id,descrição, que nesse caso é passado como interio sua referencia*/
    private String desPesquisa;
    /*des pesquisa referese a descrição*/
    private EntityManager entityManager = JpaUtil.getEntityManager();
/* a implementação do entity maneger permite uma interação mais simplificada 
    entre seu código e o banco de dados, já que o mesmo monta as funções em sql facilmente*/
    public CorController() {
        cor = new Cor();
        corSelect = new Cor();
        listCores = entityManager.createQuery("From Cor").getResultList();
    }   /* criação de um objrto Cor cor = new Cor()n ou seja informo o tipo do objeto, o nome de e atribuo a ele 
    um novo valor da classe cor
    anteriormente eu coloquei que cor é um objeto do tipo cor logo não preciso definir no momento da criação, e isso 
    é possivel, mesmo eu tendo criado como private, graças ao fato do meu meto construtor estar 
    dentro da mesma classe */
/*uma classe abstrata não pode ser instanceada*/
    
    
    @Override
    /* garante que você está sobrescrevendo um método e não criando um novo.
    ou seja esses referidos metodos estão no nosso interfacebeans, porém eles estão com implementações diferentes
    nesse controller, já que a superclasse gravar no interfacebeans não tem qualquer outro parametro*/
    public void gravar() {
        
        entityManager.getTransaction().begin();
        entityManager.merge(cor);//sintaxes do manegerbean
        entityManager.getTransaction().commit();
        cor = new Cor();

        pesquisar();

    }
     @Override
    public void excluir() {

        System.out.println("Cor id" + corSelect.getId());
        System.out.println("cor + " + corSelect.getNmCor());

        Object c = (Cor) entityManager.merge(corSelect);
/*nesse excluir estamos criando um novo objeto que receberá os dados do objeto Cor selecionado,
        logo eu excluo a referencia e não objeto, já que c só existe no momento da transação*/
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
/*criação dos casos 1 geral 2 descrição 3 id*/
            case 1: {
                listCores = entityManager.createQuery("From  Cor").getResultList();
                break;
            }
            case 2: {          
                listCores = entityManager.createQuery("From  Cor  where nmCor like'%"+desPesquisa+"%'  ").getResultList();
                break;
            }
            case 3: {                
                 listCores  = entityManager.createQuery("From  Cor where id = "+desPesquisa).getResultList();
                break;
            }

        }

    }
/*após a criação das classes privadaa, nos utilizamos o metodo de encapsulamento que nada mais é do que nossos
    getters e setrs que são usados para termos o controle dos acessos aos atributos*/
    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public Cor getCorSelect() {
        return corSelect;
    }

    public void setCorSelect(Cor corSelect) {
        this.corSelect = corSelect;
    }

    public List<Cor> getListCores() {
        return listCores;
    }

    public void setListCores(List<Cor> listCores) {
        this.listCores = listCores;
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
