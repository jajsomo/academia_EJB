package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the matriculas database table.
 * 
 */
@Entity
@Table(name="matriculas")
@NamedQuery(name="Matricula.findAll", query="SELECT m FROM Matricula m")
public class Matricula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMatriculas;

	private double nota;

	//bi-directional many-to-one association to Alumno
	@ManyToOne
	@JoinColumn(name="idAlumno")
	private Alumno alumno;

	//bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name="idCurso")
	private Curso curso;

	public Matricula() {
	}

	public Matricula( double nota, Alumno alumno, Curso curso) {
		super();
		this.nota = nota;
		this.alumno = alumno;
		this.curso = curso;
	}

	public int getIdMatriculas() {
		return this.idMatriculas;
	}

	public void setIdMatriculas(int idMatriculas) {
		this.idMatriculas = idMatriculas;
	}

	public double getNota() {
		return this.nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}