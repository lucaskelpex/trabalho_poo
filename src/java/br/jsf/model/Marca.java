
package br.jsf.model;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Marca implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String nmMarca;
    @OneToMany(mappedBy = "marca",cascade = CascadeType.ALL)
    private List<Modelo> modelos = new ArrayList<>();
     public Marca() {
    }

    public List<Modelo> getModelos() {
        return modelos;
    }

    public void setModelos(List<Modelo> modelos) {
        this.modelos = modelos;
    }
    

  

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
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
        final Marca other = (Marca) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

   

    public String getNmMarca() {
        return nmMarca;
    }

    public void setNmMarca(String nmMarca) {
        this.nmMarca = nmMarca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
}
