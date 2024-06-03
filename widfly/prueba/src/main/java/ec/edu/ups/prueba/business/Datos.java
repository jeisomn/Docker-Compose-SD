package ec.edu.ups.prueba.business;

import java.util.List;

import ec.edu.ups.prueba.dao.AutoDAO;
import ec.edu.ups.prueba.model.Auto;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;


@Stateless
public class Datos {

	@Inject
	private AutoDAO daoAuto;
	
	public void guardar(Auto auto) {
		Auto au = daoAuto.read(auto.getCodigo());
		if(au != null) {
			daoAuto.update(auto);
		}else {
			daoAuto.insert(auto);
		}
	}
	
	public void actualizarAuto(Auto auto) throws Exception{
		Auto au = daoAuto.read(auto.getCodigo());
		if (au != null){
			daoAuto.update(auto);
		}else {
			throw new Exception("El auto no existe");	
		}
	}
	
	public void borrarAuto(int codigo) {
		daoAuto.remove(codigo);
	}
	
	public List<Auto> getAutos(){
		return daoAuto.getAll();
	}
	
}
