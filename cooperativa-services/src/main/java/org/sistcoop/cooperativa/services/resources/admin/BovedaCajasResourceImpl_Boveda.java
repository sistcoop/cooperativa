package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.admin.client.resource.BovedaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.BovedaCajasResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.BovedaCajaRepresentation;
import org.sistcoop.cooperativa.services.resources.producers.BovedaCajas_Boveda;

@Stateless
@BovedaCajas_Boveda
public class BovedaCajasResourceImpl_Boveda implements BovedaCajasResource {

	@PathParam("boveda")
	private String boveda;
	
	@Inject
	private BovedaProvider bovedaProvider;
	
	@Inject
	private BovedaCajaProvider bovedaCajaProvider;
	
	@Inject
	private CajaProvider cajaProvider;
	
	@Inject
	private RepresentationToModel representationToModel;
	
	@Context
	private UriInfo uriInfo;
	
	@Inject
	private BovedaCajaResource cajaBovedaResource;
	
	public BovedaModel getBovedaModel() {
		return bovedaProvider.getBovedaById(boveda);
	}

	@Override
	public BovedaCajaResource boveda(String bovedaCaja) {	
		return cajaBovedaResource;
	}

	@Override
	public Response create(BovedaCajaRepresentation[] bovedaCajaRepresentations) {
		representationToModel.createBovedaCaja(
				bovedaCajaRepresentations, 
				getBovedaModel(), 
				cajaProvider,
				bovedaCajaProvider);
		
		return Response.ok().build();
	}
	
	/*@Override
	public Response create(BovedaCajaRepresentation bovedaCajaRepresentation) {
		BovedaCajaModel bovedaCajaModel = representationToModel.createBovedaCaja(
				bovedaCajaRepresentation, 
				getBovedaModel(), 
				cajaProvider,
				bovedaCajaProvider);
		
		return Response.created(uriInfo.getAbsolutePathBuilder()
				.path(bovedaCajaModel.getId()).build())
				.header("Access-Control-Expose-Headers", "Location")
				.entity(Jsend.getSuccessJSend(bovedaCajaModel.getId())).build();
	}*/
	
	@Override
	public List<BovedaCajaRepresentation> search() {
		List<BovedaCajaModel> bovedaCajaModels = bovedaCajaProvider.getBovedaCajas(getBovedaModel());
		List<BovedaCajaRepresentation> result = new ArrayList<>();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {			
			result.add(ModelToRepresentation.toRepresentation(bovedaCajaModel));
		}
		return result;
	}

	@Override
	public List<BovedaCajaRepresentation> search(String boveda, String caja) {
		CajaModel cajaModel = cajaProvider.getCajaById(caja);
		
		List<BovedaCajaModel> bovedaCajaModels = bovedaCajaProvider.getBovedaCajas(getBovedaModel(), cajaModel);
		List<BovedaCajaRepresentation> result = new ArrayList<>();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
			result.add(ModelToRepresentation.toRepresentation(bovedaCajaModel));
		}
		return result;
	}

}
