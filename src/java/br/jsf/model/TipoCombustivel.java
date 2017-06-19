package br.jsf.model;

import java.io.Serializable;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TipoCombustivel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nmTipoCombustivel;
    @OneToMany(mappedBy = "tipoCombustivel")
    private List<Veiculo> veiculos;
   

      

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
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
        final TipoCombustivel other = (TipoCombustivel) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    public TipoCombustivel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNmTipoCombustivel() {
        return nmTipoCombustivel;
    }

    public void setNmTipoCombustivel(String nmTipocombustivel) {
        this.nmTipoCombustivel = nmTipocombustivel;
    }
    

}
