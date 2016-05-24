package ejbs;

import java.util.List;

import javax.ejb.Local;

import entidades.Matricula;

@Local
public interface GestionMatriculasLocal {
	void registrarMatricula(int idCurso,int idAlumno);
	List<Matricula> consultarMatriculas();
	List<Matricula> consultarMatriculaPorCurso(int idCurso);
	List<Matricula> consultarMatriculaPorAlumno(int idAlumno);
	List<Matricula> consultarMatriculaPorDni(String dni);
	Matricula consultarMatriculaPorIdMatricula(int idMatricula);
	boolean eliminarMatricula(int idCurso, int idAlumno);
	void eliminarMatriculaPorId(int idMatricula);
	void ponerNota(Matricula m, Double nota);
	
}
