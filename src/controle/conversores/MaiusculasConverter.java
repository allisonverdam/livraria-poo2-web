package controle.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="maiusculas")
public class MaiusculasConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext contexto, UIComponent campo, String valor) {
		System.out.println(getClass().getName() + ".getAsObject()");
		return valor.toUpperCase();
	}

	@Override
	public String getAsString(FacesContext contexto, UIComponent campo, Object valor) {
		String str = (String) valor;
		return str.toUpperCase();
	}

}
