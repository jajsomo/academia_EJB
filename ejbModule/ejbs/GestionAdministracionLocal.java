package ejbs;

import javax.ejb.Local;

import entidades.Administracion;

@Local
public interface GestionAdministracionLocal {
	void registrarAdministrador(Administracion admin);
	boolean estaRegistrado(String user, String pwd);
}
