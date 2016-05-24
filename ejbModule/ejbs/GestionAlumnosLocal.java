package ejbs;

import java.util.List;

import javax.ejb.Local;

import entidades.Alumno;


@Local
public interface GestionAlumnosLocal {
	void registrarAlumno(Alumno a);
	List<Alumno> consultarAlumnos();
	Alumno consultarAlumnosPorDni(String dni);
	Alumno consultarAlumnosPorId(int idAlumno);
	boolean estaRegistrado(String user, String pwd);
	void eliminarAlumno(int idAlumno);
}
