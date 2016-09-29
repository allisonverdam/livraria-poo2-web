package dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="gerentes")
public class Gerente {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_GERENTE")
	@SequenceGenerator(name = "ID_GERENTE", sequenceName = "SEQ_ID_GERENTE",
	                    allocationSize = 1, initialValue=1)
	private int id;
	
	private String login;
	private String senha;
	private String nome;
	private boolean admin;
	
	public Gerente(){
		super();
	}

	
	public Gerente(String login, String senha, String nome, boolean admin) {
		super();
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.admin = admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
