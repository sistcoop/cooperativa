package org.sistcoop.cooperativa.services.resources.admin.pattern;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import org.sistcoop.cooperativa.admin.client.resource.CajaTrabajadorResource;
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.TrabajadorRepresentation;

@Stateless
public class CajaTrabajadorResourceImpl implements CajaTrabajadorResource {

	private TrabajadorCajaModel trabajadorCajaModel;
	
	@Inject
	private TrabajadorCajaProvider trabajadorCajaProvider;

	public CajaTrabajadorResourceImpl(TrabajadorCajaModel trabajadorCajaModel) {
		this.trabajadorCajaModel = trabajadorCajaModel;
	}

	@Override
	public TrabajadorRepresentation trabajador() {
		return ModelToRepresentation.toRepresentation(trabajadorCajaModel);
	}

	@Override
	public void update(TrabajadorRepresentation trabajadorRepresentation) {
		throw new BadRequestException();
	}

	@Override
	public void remove() {
		trabajadorCajaProvider.removeTrabajadorCaja(trabajadorCajaModel);
	}	

}
