package dominio;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "livros")
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_LIVRO")
	@SequenceGenerator(name = "ID_LIVRO", sequenceName = "SEQ_ID_LIVRO",
	                    allocationSize = 1, initialValue=1)
	private Long id;
	private String nomeLivro;
	private String descricao;
	private int anoLancamento;
	private int numPaginas;
	private float preco;
	
	@OneToMany(mappedBy="livro", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	private List<Compra> compra;
	
	public Livro(String nomeLivro, String descricao, int anoLancamento, int numPaginas, float preco) {
		super();
		this.preco = preco;
		this.nomeLivro = nomeLivro;
		this.descricao = descricao;
		this.anoLancamento = anoLancamento;
		this.numPaginas = numPaginas;
	}	
	
	public Livro() {
		super();
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNomeLivro() {
		return nomeLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}

	public int getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public int getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}

	public List<Compra> getCompra() {
		return compra;
	}

	public void setCompra(List<Compra> compra) {
		this.compra = compra;
	}
	
	

	
}
