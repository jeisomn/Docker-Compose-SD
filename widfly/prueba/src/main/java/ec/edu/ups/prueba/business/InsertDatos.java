package ec.edu.ups.prueba.business;

import ec.edu.ups.prueba.dao.AutoDAO;
import ec.edu.ups.prueba.model.Auto;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;


@Singleton
@Startup
public class InsertDatos {

	@Inject
	private AutoDAO daoAuto;
	
	@PostConstruct
	public void init() {
		System.out.println("Iniciando");
		
		Auto au = new Auto();
		au.setCodigo(1);
		au.setMarca("BMW");
		au.setPlaca("ABC-837");
		au.setPrecio(2500.50);
		
		
		daoAuto.insert(au);
		
		System.out.println("Auto" + au);
	}
}
