package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaEntity;

public class DetalleTransaccionBovedaCajaAdapter implements DetalleTransaccionBovedaCajaModel {

	private static final long serialVersionUID = 1L;

	protected BovedaEntity bovedaEntity;
	protected EntityManager em;

	public DetalleTransaccionBovedaCajaAdapter(EntityManager em, BovedaEntity bovedaEntity) {
		this.em = em;
		this.bovedaEntity = bovedaEntity;
	}

	public BovedaEntity getAgenciaEntity() {
		return bovedaEntity;
	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getValor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCantidad() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BigDecimal getSubtotal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransaccionBovedaCajaModel getTransaccionBovedaCaja() {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
