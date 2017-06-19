/*atributos do objeto
@OneToMany = Existem várias ocorrências da variável com essa anotação para essa classe
@ManyToOne = Esta classe só pode ter uma dessa variável, mas a variavel pode ter várias dessa classe;*/
package br.jsf.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity

public class Cor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToMany(mappedBy = "cor")
    private List<Veiculo> veiculos;
   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id;
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
        final Cor other = (Cor) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private String nmCor;

    public Cor() {
    }

    public String getNmCor() {
        return nmCor;
    }

    public void setNmCor(String nmCor) {
        this.nmCor = nmCor;
    }

}
