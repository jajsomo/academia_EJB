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
import entidades.Alumno;
import entidades.Curso;
import entidades.Matricula;

/**
 * Session Bean implementation class GestionAlumnos
 */
@Stateless
@LocalBean
public class GestionAlumnos implements GestionAlumnosLocal {
	//inyectar EntityManager
	@PersistenceContext(unitName="academia")
	EntityManager em;
	
	@Override
	public void registrarAlumno(Alumno a) {
		em.persist(a);	
	}

	@Override
	public List<Alumno> consultarAlumnos() {
		Query q=em.createNamedQuery("Alumno.findAll");
		return q.getResultList();

	}

	@Override
	public Alumno consultarAlumnosPorDni(String dni) {
		Alumno alumno;
		Query q=em.createQuery("select a from Alumno a where a.dni=?1");
		q.setParameter(1, dni);
		try{
			alumno = (Alumno) q.getSingleResult();
		}
		catch(NoResultException | NonUniqueResultException e) {
			alumno=null;
		}
		return alumno;	
	}

	@Override
	public boolean estaRegistrado(String user, String pwd) {
		boolean resultado;
		Query q=em.createQuery("select a from Alumno a where a.dni=?1 and a.password=?2");
		q.setParameter(1, user);
		q.setParameter(2, pwd);
		try{
			Alumno alumno = (Alumno) q.getSingleResult();
			resultado = true;
		}
		catch(NoResultException | NonUniqueResultException e) {
			resultado = false;
		}
		return resultado;	
	}

	@Override
	public Alumno consultarAlumnosPorId(int idAlumno) {
		return em.find(Alumno.class, idAlumno);
	}

	@Override
	public void eliminarAlumno(int idAlumno) {
		Alumno a= em.find(Alumno.class, idAlumno);
		if (a!= null){
			em.remove(a);
		}	
	}

   
}
