package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the administracion database table.
 * 
 */
@Entity
@NamedQuery(name="Administracion.findAll", query="SELECT a FROM Administracion a")
public class Administracion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAdministrador;

	private String password;

	private String usuario;

	public Administracion() {
	}

	public int getIdAdministrador() {
		return this.idAdministrador;
	}

	public void setIdAdministrador(int idAdministrador) {
		this.idAdministrador = idAdministrador;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}