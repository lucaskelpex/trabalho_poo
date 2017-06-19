package br.jsf.converter;

import br.jsf.JpuUtil.JpaUtil;
import br.jsf.model.TipoCombustivel;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

@FacesConverter(forClass = TipoCombustivel.class, value = "TipoCombustivelConverter")
public class TipoCombustivelConverter implements Converter, Serializable {

    private EntityManager entityManager = JpaUtil.getEntityManager();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {

            return (TipoCombustivel) entityManager.createQuery("From TipoCombustivel where id = " + string).getSingleResult();

        } catch (NumberFormatException e) {

            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        TipoCombustivel obj = (TipoCombustivel) o;
        try {
            return String.valueOf(obj.getId());
        } catch (Exception e) {
            return null;
        }
    }
}
