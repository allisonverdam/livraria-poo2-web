package controle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import controle.util.JSFUtil;
import dominio.Cliente;
import dominio.dao.ClienteDAO;

@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB implements Serializable
{
	private static final long serialVersionUID = 1L;

	private boolean autenticado = false;
	private Cliente Cliente = new Cliente(null, "(não autenticado)", null);

	private String login;
	private String senha;

	public Cliente getCliente()
	{
		return Cliente;
	}

	public void setCliente(Cliente Cliente)
	{
		this.Cliente = Cliente;
	}

	public boolean isAutenticado()
	{
		return autenticado;
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getSenha()
	{
		return senha;
	}

	public void setSenha(String senha)
	{
		this.senha = senha;
	}

	/**
	 * 
	 */
	public String acaoAbrirLogin()
	{
		return "login";
	}

	/**
	 * 
	 */
	
	/**
	 * 
	 */
	public String acaoListar()
	{
		return "LivroListar";
	}

	/**
	 * 
	 */
	public String acaoAutenticar()
	{
		ClienteDAO dao = new ClienteDAO();

		Cliente ClienteDoBanco = dao.lerPorLogin(this.getLogin());

		if (ClienteDoBanco == null)
		{
			JSFUtil.retornarMensagemErro("Usuário não existe.", null, null);
			return "login";
		}
		else if (ClienteDoBanco.senhaCorreta(this.getSenha()))
		{
			// guardar o obteto usuário
			this.setCliente(ClienteDoBanco);
			this.autenticado = true;

			return "home";
		}
		else
		{
			JSFUtil.retornarMensagemErro("Senha inválida.", null, null);
			return "login";
		}
	}

	/**
	 * 
	 */
	public String acaoLogout()
	{
		this.Cliente = new Cliente(null, "(não autenticado)", null);
		this.autenticado = false;
		this.login = null;
		this.senha = null;

		// encerrar a sessão atual
		JSFUtil.getHttpSession().invalidate();

		return "login";
	}

}
