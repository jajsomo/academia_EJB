package ejbs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



import entidades.Curso;
import entidades.Matricula;


/**
 * Session Bean implementation class GestionCursos
 */
@Stateless
@LocalBean
public class GestionCursos implements GestionCursosLocal {
	//inyectar EntityManager
		@PersistenceContext(unitName="academia")
		EntityManager em;

		@Override
		public void registrarCurso(Curso c) {
			em.persist(c);	
		}

		@Override
		public List<Curso> consultarCursos() {
			Query q=em.createNamedQuery("Curso.findAll");
			return q.getResultList();
		}

		@Override
		public Curso consultarCursoPorNombre(String nombre) {
			Curso curso;
			Query q=em.createQuery("select c from Curso c where c.nombre=?1");
			q.setParameter(1, nombre);
			try{
				curso = (Curso) q.getSingleResult();
			}
			catch(NoResultException | NonUniqueResultException e) {
					curso=null;
			}
			return curso;
		}

		@Override
		public Curso consultarCursoPorId(int idCurso) {
			return em.find(Curso.class, idCurso);
		}

		@Override
		public void eliminarCurso(int idCurso) {
			Curso c= em.find(Curso.class, idCurso);
			if (c!= null){
				em.remove(c);
			}
		}

		@Override
		public boolean estaRegistrado(String nombreCurso, Date fechaIni, Date fechaFin) {
			boolean resultado;
			Query q=em.createQuery("select c from Curso c where c.nombre=?1 and c.fechaIni=2 and c.fechaFin=3");
			q.setParameter(1, nombreCurso).setParameter(2, fechaIni).setParameter(3, fechaFin);
			try{
				q.getResultList();
				resultado =true;
			}
			catch(NoResultException | NonUniqueResultException e) {
					resultado = false;
			}
			return resultado;	
		}

		@Override
		public List<Curso> consultarMatriculas(List<Matricula> lista) {
			List<Curso> listadoCurso = new ArrayList<>();
			Curso curso;
			Query q=em.createQuery("select c from Curso c where c.idCurso=?1");
			for(Matricula m:lista){
				q.setParameter(1, m.getIdMatriculas());
				try{
					curso = (Curso) q.getSingleResult();
					listadoCurso.add(curso);
				}
				catch(NoResultException | NonUniqueResultException e) {
						listadoCurso=null;
				}
			}
			return listadoCurso;

		}
		

}
