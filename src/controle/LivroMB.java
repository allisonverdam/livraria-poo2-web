package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import controle.util.JSFUtil;
import dominio.Editora;
import dominio.Livro;
import dominio.dao.EditoraDAO;
import dominio.dao.LivroDAO;

@ManagedBean(name = "livroMB")
@RequestScoped
public class LivroMB {


	private Livro livro = new Livro();
	private LivroDAO dao = new LivroDAO();
	private Editora filtroEditora = null;

	private List<Livro> livros = null;
	private List<Editora> editoras = null;


	public List<Editora> getEditoras() {
		if (this.editoras == null)
			this.editoras = new EditoraDAO().lerTodos();

		return this.editoras;
	}

	public List<Livro> getLivros()
	{
		if (this.livros == null)
			this.livros = this.dao.lerTodos();

		return this.livros;
	}
	
	public Editora getFiltroEditora() {
		return filtroEditora;
	}

	public void setFiltroEditora(Editora filtroEditora) {
		this.filtroEditora = filtroEditora;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}




	public void setEditoras(List<Editora> editoras) {
		this.editoras = editoras;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	/**
	 * 
	 */
	public String acaoListar() {
		return "LivroListar";
	}

	/**
	 * 
	 */
	public String acaoAbrirInclusao() {
		// limpar o objeto da página
		this.setLivro(new Livro());

		return "LivroEditar";
	}

	/**
	 * 
	 */
	public String acaoAbrirAlteracao() {
		// pega o ID escolhido que veio no parâmetro
		Long id = JSFUtil.getParametroLong("itemId");
		Livro objetoDoBanco = this.dao.lerPorId(id);
		this.setLivro(objetoDoBanco);

		return "LivroEditar";
	}
	
	

	/**
	 * 
	 */
	public String acaoSalvar() {
		/**
		 * Deve limpar o ID com valor zero, pois o JSF sempre converte o campo
		 * vazio para um LONG = 0.
		 */
		if ((this.getLivro().getId() != null)
				&& (this.getLivro().getId().longValue() == 0))
			this.getLivro().setId(null);

		this.dao.salvar(this.getLivro());
		// limpa a lista
		this.livros = null;

		// limpar o objeto da página
		this.setLivro(new Livro());

		// executa a ação listar e retorna a sua página
		return this.acaoListar();
	}

	/**
	 * 
	 */
	public String acaoCancelar() {
		// limpar o objeto da página
		this.setLivro(new Livro());

		// executa a ação listar e retorna a sua página
		return this.acaoListar();
	}

	/**
	 * 
	 */
	public String acaoExcluir() {
		Long id = JSFUtil.getParametroLong("itemId");
		Livro objetoDoBanco = this.dao.lerPorId(id);
		this.dao.excluir(objetoDoBanco);

		// limpar o objeto da página
		this.setLivro(new Livro());
		// limpa a lista
		this.livros = null;

		// executa a ação listar e retorna a sua página
		return this.acaoListar();
	}

}
