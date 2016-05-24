package ejbs;


import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import entidades.Curso;
import entidades.Matricula;

@Local
public interface GestionCursosLocal {
	void registrarCurso(Curso c);
	boolean estaRegistrado(String nombreCurso,Date fechaIni,Date fechaFin);
	List<Curso> consultarCursos();
	List<Curso> consultarMatriculas(List<Matricula> lista);
	Curso consultarCursoPorNombre(String nombre);
	Curso consultarCursoPorId(int idCurso);
	void eliminarCurso(int idCurso);
}
