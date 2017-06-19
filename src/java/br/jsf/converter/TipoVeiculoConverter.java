package br.jsf.converter;

import br.jsf.JpuUtil.JpaUtil;
import br.jsf.model.TipoVeiculo;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

@FacesConverter(forClass = TipoVeiculo.class, value = "TipoVeiculoConverter")
public class TipoVeiculoConverter implements Converter, Serializable {

    private EntityManager entityManager = JpaUtil.getEntityManager();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {

            return (TipoVeiculo) entityManager.createQuery("From TipoVeiculo where id = " + string).getSingleResult();

        } catch (NumberFormatException e) {

            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        TipoVeiculo obj = (TipoVeiculo) o;
        try {
            return String.valueOf(obj.getId());
        } catch (Exception e) {
            return null;
        }
    }
}
