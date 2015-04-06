package org.sistcoop.cooperativa.models.jpa;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;

@Named
@Stateless
@Local(BovedaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaBovedaProvider implements BovedaProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public BovedaModel addBoveda(String moneda, String denominacion, String agencia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean desactivarBoveda(BovedaModel bovedaModel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BovedaModel getBovedaById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BovedaModel> getBovedas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BovedaModel> getBovedas(String agencia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BovedaModel> getBovedas(boolean estado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BovedaModel> getBovedas(String agencia, boolean estado) {
		// TODO Auto-generated method stub
		return null;
	}

}
