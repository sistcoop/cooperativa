package org.sistcoop.cooperativa.models;

import java.util.List;

public interface BovedaModel extends Model {

	Integer getId();

	String getMoneda();

	String getDenominacion();

	void setDenominacion(String denominacion);

	boolean isAbierto();

	void setAbierto(boolean abierto);

	boolean getEstadoMovimiento();

	void setEstadoMovimiento(boolean estadoMovimiento);

	boolean getEstado();

	void setEstado(boolean estado);

	String getAgencia();

	List<HistorialBovedaModel> getHistorial();

	List<BovedaCajaModel> getBovedaCajas();
}