package dominio.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import controle.util.JpaDAO;
import dominio.Editora;
import dominio.Livro;

public class EditoraDAO extends JpaDAO<Editora> {
	
	public EditoraDAO() {
		super();
	}

	public EditoraDAO(EntityManager manager) {
		super(manager);
	}
	
	public Editora lerPorNome(String nomeEditora)
	{
		Editora resultado;

		Query consulta = this.getEntityManager().createQuery("from Editora u where u.nomeEditora = :nomeEditora");
		consulta.setParameter("nomeEditora", nomeEditora);

		try
		{
			resultado = (Editora) consulta.getSingleResult();
		}
		catch (NoResultException e)
		{
			resultado = null;
		}

		return resultado;
	}
}
