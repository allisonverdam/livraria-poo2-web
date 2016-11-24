package controle.validadores;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import controle.util.JSFUtil;

@FacesValidator(value="email-validator")
public class EmailValidator implements Validator {

	@Override
	public void validate(FacesContext contexto, UIComponent campo, Object valor)
			throws ValidatorException {
		
		String valorS = (String) valor;
		if (valorS.indexOf("@") <= 0)
		{			
			String mensagem = JSFUtil.getMensagemI18N("exceptionEmailInvalido");

			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, null));
		}
		

	}

}
