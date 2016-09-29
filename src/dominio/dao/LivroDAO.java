package dominio.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import controle.util.JpaDAO;
import dominio.Livro;

public class LivroDAO extends JpaDAO<Livro> {
	
	public LivroDAO() {
		super();
	}

	public LivroDAO(EntityManager manager) {
		super(manager);
	}
	
	public Livro lerPorNome(String nome)
	{
		Livro resultado;

		Query consulta = this.getEntityManager().createQuery("from Livro u where u.nome = :nome");
		consulta.setParameter("nome", nome);

		try
		{
			resultado = (Livro) consulta.getSingleResult();
		}
		catch (NoResultException e)
		{
			resultado = null;
		}

		return resultado;
	}
}
