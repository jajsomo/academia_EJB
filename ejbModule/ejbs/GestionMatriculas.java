package ejbs;


import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Alumno;
import entidades.Curso;
import entidades.Matricula;


/**
 * Session Bean implementation class GestionMatriculas
 */
@Stateless
@LocalBean
public class GestionMatriculas implements GestionMatriculasLocal {

	@PersistenceContext(unitName="academia")
	EntityManager em;

	@Override
	public void registrarMatricula(int idCurso, int idAlumno) {
       Curso curso=em.find(Curso.class, idCurso);
        curso.setPlazasLibres(curso.getPlazasLibres()-1);
        em.merge(curso);
	
		 
        Alumno alumno=em.find(Alumno.class, idAlumno);
        Matricula m=new Matricula(0, alumno, curso);      
        em.persist(m);		
  	}

	@Override
	public List<Matricula> consultarMatriculas() {
		Query q=em.createNamedQuery("Curso.findAll");
		return (List<Matricula>)q.getResultList();
	}

	@Override
	public List<Matricula> consultarMatriculaPorCurso(int idCurso) {
		Query q=em.createQuery("select m from Matricula m where m.curso.idCurso=?1");
		q.setParameter(1, idCurso);
		List<Matricula> lista = (List<Matricula>)q.getResultList();
		return lista;
		}

	@Override
	public List<Matricula> consultarMatriculaPorAlumno(int idAlumno) {
		Query q=em.createQuery("select m from Matricula m where m.alumno.idAlumno=?1");
		q.setParameter(1, idAlumno);
		List<Matricula> lista=(List<Matricula>)q.getResultList();
		return lista;
	}
	

	@Override
	public List<Matricula> consultarMatriculaPorDni(String dni) {
		Query q=em.createQuery("select m from Matricula m where m.alumno.dni=?1");
		q.setParameter(1, dni);
		List<Matricula> lista= (List<Matricula>)q.getResultList();
		return lista;	
	}

	@Override
	public boolean eliminarMatricula(int idCurso, int idAlumno) {
		boolean resultado;
		Query q=em.createQuery("select m from Matricula m where m.idCurso=?1 and m.idAlumno=?2");
		q.setParameter(1, idCurso).setParameter(2, idAlumno);
		try{
			Matricula matricula = (Matricula) q.getSingleResult();
			em.remove(matricula);
			resultado = true;
		}
		catch(NoResultException | NonUniqueResultException e) {
			resultado = false;
		}
		return resultado;
	}

	@Override
	public void eliminarMatriculaPorId(int idMatricula) {
		Matricula matricula= em.find(Matricula.class, idMatricula);
		if (matricula!= null){
			em.remove(matricula);
		}		
	}

	@Override
	public Matricula consultarMatriculaPorIdMatricula(int idMatricula) {
		return em.find(Matricula.class, idMatricula);
	}

	@Override
	public void ponerNota(Matricula m, Double nota) {
		m.setNota(nota);
		em.merge(m);
	}


}
