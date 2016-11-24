package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import controle.util.JSFUtil;
import dominio.Editora;
import dominio.dao.EditoraDAO;

@ManagedBean(name = "editoraMB")
@RequestScoped
public class EditoraMB
{
	private Editora editora = new Editora();
	private EditoraDAO dao = new EditoraDAO();

	private List<Editora> editoras = null;

	public List<Editora> getEditoras()
	{
		if (this.editoras == null)
			this.editoras = this.dao.lerTodos();

		return this.editoras;
	}

	public Editora getEditora()
	{
		return editora;
	}

	public void setEditora(Editora editora)
	{
		this.editora = editora;
	}

	/**
	 * 
	 */
	public String acaoListar()
	{
		return "editoraListar";
	}

	/**
	 * 
	 */
	public String acaoAbrirInclusao()
	{
		// limpar o objeto da página
		this.setEditora(new Editora());

		return "editoraEditar";
	}

	/**
	 * 
	 */
	public String acaoAbrirAlteracao()
	{
		Long id = JSFUtil.getParametroLong("itemId");
		Editora objetoDoBanco = this.dao.lerPorId(id);
		this.setEditora(objetoDoBanco);

		return "editoraEditar";
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
		if ((this.getEditora().getId() != null) && (this.getEditora().getId().longValue() == 0))
			this.getEditora().setId(null);

		this.dao.salvar(this.getEditora());
		// limpa a lista
		this.editoras = null;

		// limpar o objeto da página
		this.setEditora(new Editora());

		return "editoraListar";
	}
	/**
	 * 
	 */
	public String acaoCancelar()
	{
		// limpar o objeto da página
		this.setEditora(new Editora());

		return "editoraListar";
	}

	/**
	 * 
	 */
	public String acaoExcluir()
	{
		Long id = JSFUtil.getParametroLong("itemId");
		Editora objetoDoBanco = this.dao.lerPorId(id);
		this.dao.excluir(objetoDoBanco);

		// limpar o objeto da página
		this.setEditora(new Editora());
		// limpa a lista
		this.editoras = null;

		return "editoraListar";
	}

}
