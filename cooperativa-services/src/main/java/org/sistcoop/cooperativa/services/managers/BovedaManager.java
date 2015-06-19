package org.sistcoop.cooperativa.services.managers;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BovedaManager {

	@Inject
	private HistorialBovedaProvider historialBovedaProvider;

	@Inject
	private DetalleHistorialBovedaProvider detalleHistorialBovedaProvider;
	
	public boolean activarBoveda(BovedaModel model) {	
		return false;
	}
	
	public boolean desactivarBoveda(BovedaModel model) {	
		//desactivar bovedas
		model.desactivar();		
		model.commit();
		
		//desactivar boveda-cajas
		List<BovedaCajaModel> bovedaCajaModels = model.getBovedaCajas();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
			bovedaCajaModel.desactivar();
			bovedaCajaModel.commit();
		}
		
		return true;
	}

}
