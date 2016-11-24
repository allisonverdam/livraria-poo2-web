package controle.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import dominio.Editora;
import dominio.dao.EditoraDAO;

@FacesConverter(value="editora-converter", forClass=Editora.class)
public class EditoraConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent componente,
			String valor) {

		if (valor == null || valor.length() == 0)
			return null;
		
		Long id=null;
		try {
			id = new Long(valor);
		} catch (NumberFormatException e) {
			return null;
		}

		EditoraDAO dao = new EditoraDAO();
		Editora editora = dao.lerPorId(id);

		return editora;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent componente,
			Object objeto) {

		if (objeto instanceof Editora)
			return ((Editora) objeto).getId().toString();

		return null;
	}

}
