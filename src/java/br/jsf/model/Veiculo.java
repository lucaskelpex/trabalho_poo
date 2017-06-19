package br.jsf.model;

import java.io.Serializable;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Veiculo implements Serializable {


    @ManyToOne
   private Cor cor;
    @OneToMany(mappedBy = "veiculo")
    private List<Locacao> locacaos;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;
     
      
  
      
    @ManyToOne
    private TipoCombustivel tipoCombustivel;    
    @ManyToOne
    private Modelo modelo;
    
    @ManyToOne
    private TipoVeiculo tipoVeiculo;

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }
    

   
    
    public TipoCombustivel getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(TipoCombustivel tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

  

    public List<Locacao> getLocacaos() {
        return locacaos;
    }

    public void setLocacaos(List<Locacao> locacaos) {
        this.locacaos = locacaos;
    }
    
    private double valorLocacao;
     

    public double getValorLocacao() {
        return valorLocacao;
    }

    public void setValorLocacao(double valorLocacao) {
        this.valorLocacao = valorLocacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Veiculo other = (Veiculo) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

   

  

   

   

   
    
    
    
    
    
}
