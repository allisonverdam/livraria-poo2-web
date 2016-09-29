package dominio.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import controle.util.JpaDAO;
import dominio.Cliente;

public class ClienteDAO extends JpaDAO<Cliente>
{

	public ClienteDAO()
	{
		super();
	}

	public ClienteDAO(EntityManager manager)
	{
		super(manager);
	}

	public Cliente lerPorLogin(String login)
	{
		Cliente resultado;

		Query consulta = this.getEntityManager().createQuery("from Cliente u where u.login = :login");
		consulta.setParameter("login", login);

		try
		{
			resultado = (Cliente) consulta.getSingleResult();
		}
		catch (NoResultException e)
		{
			resultado = null;
		}

		return resultado;
	}

}
