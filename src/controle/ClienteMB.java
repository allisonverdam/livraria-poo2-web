package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import controle.util.JSFUtil;
import dominio.Cliente;
import dominio.dao.ClienteDAO;

@ManagedBean(name = "clienteMB")
@RequestScoped
public class ClienteMB
{
	private Cliente Cliente = new Cliente();
	private ClienteDAO dao = new ClienteDAO();

	private List<Cliente> Clientes = null;

	public List<Cliente> getClientes()
	{
		if (this.Clientes == null)
			this.Clientes = this.dao.lerTodos();

		return this.Clientes;
	}

	public Cliente getCliente()
	{
		return Cliente;
	}

	public void setCliente(Cliente cliente)
	{
		this.Cliente = cliente;
	}

	/**
	 * 
	 */
	public String acaoListar()
	{
		return "clienteListar";
	}

	/**
	 * 
	 */
	public String acaoAbrirInclusao()
	{
		// limpar o objeto da página
		this.setCliente(new Cliente());

		return "clienteEditar";
	}
	

	public void setClientes(List<Cliente> clientes) {
		Clientes = clientes;
	}

	/**
	 * 
	 */
	public String acaoAbrirAlteracao()
	{
		Long id = JSFUtil.getParametroLong("itemId");
		Cliente objetoDoBanco = this.dao.lerPorId(id);
		this.setCliente(objetoDoBanco);

		return "clienteEditar";
	}

	/**
	 * 
	 */
	public String acaoSalvar()
	{
		/**
		 * Deve limpar o ID com valor zero, pois o JSF sempre converte o campo
		 * vazio para um LONG = 0.
		 */
		if ((this.getCliente().getId() != null) && (this.getCliente().getId().longValue() == 0))
			this.getCliente().setId(null);

		/**
		 * Se o usuário não tiver ID, deve testar se existe o mesmo no banco
		 */
		if (this.getCliente().getId() == null)
		{
			Cliente objetoDoBanco = this.dao.lerPorLogin(this.getCliente().getLogin());

			if (objetoDoBanco != null)
			{
				JSFUtil.retornarMensagemErro("Outro usuário com o mesmo login já existe no sistema.", null, null);
				return null; // volta p/mesma página
			}
		}

		this.dao.salvar(this.getCliente());
		// limpa a lista
		this.Clientes = null;

		// limpar o objeto da página
		this.setCliente(new Cliente());

		return "clienteListar";
	}

	/**
	 * 
	 */
	public String acaoCancelar()
	{
		// limpar o objeto da página
		this.setCliente(new Cliente());

		return "clienteListar";
	}

	/**
	 * 
	 */
	public String acaoExcluir()
	{
		Long id = JSFUtil.getParametroLong("itemId");
		Cliente objetoDoBanco = this.dao.lerPorId(id);
		this.dao.excluir(objetoDoBanco);

		// limpar o objeto da página
		this.setCliente(new Cliente());
		// limpa a lista
		this.Clientes = null;

		return "clienteListar";
	}

}
