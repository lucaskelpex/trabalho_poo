package br.jsf.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class TipoVeiculo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nmTipoVeiculo;
    @OneToMany(mappedBy = "tipoVeiculo")
    private List<Veiculo> veiculos;
   

   


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id;
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
        final TipoVeiculo other = (TipoVeiculo) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    public TipoVeiculo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNmTipoVeiculo() {
        return nmTipoVeiculo;
    }

    public void setNmTipoVeiculo(String nmTipoVeiculo) {
        this.nmTipoVeiculo = nmTipoVeiculo;
    }
    

}
