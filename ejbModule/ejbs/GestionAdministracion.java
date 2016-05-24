package ejbs;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Administracion;


/**
 * Session Bean implementation class GestionAdministracion
 */
@Stateless
@LocalBean
public class GestionAdministracion implements GestionAdministracionLocal {
	@PersistenceContext(unitName="academia")
	EntityManager em;
	@Override
	public void registrarAdministrador(Administracion admin) {
		em.persist(admin);
	}

	@Override
	public boolean estaRegistrado(String user, String pwd) {
		boolean resultado;
		Query q=em.createQuery("select a from Administracion a where a.usuario=?1 and a.password=?2");
		q.setParameter(1, user);
		q.setParameter(2, pwd);
		try{
			Administracion adm = (Administracion) q.getSingleResult();
			resultado = true;
		}
		catch(NoResultException | NonUniqueResultException e) {
			resultado = false;
		}
		return resultado;
	}

 

}
