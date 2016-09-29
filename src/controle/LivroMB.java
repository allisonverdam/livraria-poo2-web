package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import controle.util.JSFUtil;
import dominio.Livro;
import dominio.dao.LivroDAO;

@ManagedBean(name = "livroMB")
@RequestScoped
public class LivroMB
{
	private Livro livro = new Livro();
	private LivroDAO dao = new LivroDAO();

	private List<Livro> Livros = null;

	public List<Livro> getLivros()
	{
		if (this.Livros == null)
			this.Livros = this.dao.lerTodos();

		return this.Livros;
	}

	public Livro getLivro()
	{
		return livro;
	}

	public void setLivro(Livro livro)
	{
		this.livro = livro;
	}

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
	public String acaoAbrirInclusao()
	{
		// limpar o objeto da página
		this.setLivro(new Livro());

		return "LivroEditar";
	}

	/**
	 * 
	 */
	public String acaoAbrirAlteracao()
	{
		Long id = JSFUtil.getParametroLong("itemId");
		Livro objetoDoBanco = this.dao.lerPorId(id);
		this.setLivro(objetoDoBanco);

		return "LivroEditar";
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
		if ((this.getLivro().getId() != null) && (this.getLivro().getId().longValue() == 0))
			this.getLivro().setId(null);

		/**
		 * Se o usuário não tiver ID, deve testar se existe o mesmo no banco
		 */
		if (this.getLivro().getId() == null)
		{
			Livro objetoDoBanco = this.dao.lerPorNome(this.getLivro().getNomeLivro());

			if (objetoDoBanco != null)
			{
				JSFUtil.retornarMensagemErro("Outro usuário com o mesmo login já existe no sistema.", null, null);
				return null; // volta p/mesma página
			}
		}

		this.dao.salvar(this.getLivro());
		// limpa a lista
		this.Livros = null;

		// limpar o objeto da página
		this.setLivro(new Livro());

		return "LivroListar";
	}

	/**
	 * 
	 */
	public String acaoCancelar()
	{
		// limpar o objeto da página
		this.setLivro(new Livro());

		return "LivroListar";
	}

	/**
	 * 
	 */
	public String acaoExcluir()
	{
		Long id = JSFUtil.getParametroLong("itemId");
		Livro objetoDoBanco = this.dao.lerPorId(id);
		this.dao.excluir(objetoDoBanco);

		// limpar o objeto da página
		this.setLivro(new Livro());
		// limpa a lista
		this.Livros = null;

		return "LivroListar";
	}

}
