package org.sistcoop.cooperativa.services.resources.admin;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.admin.client.resource.BovedaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.HistorialesBovedaCajaResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.BovedaCajaRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;
import org.sistcoop.cooperativa.services.ErrorResponseException;
import org.sistcoop.cooperativa.services.managers.BovedaCajaManager;

@Stateless
public class BovedaCajaResourceImpl implements BovedaCajaResource {

    @PathParam("bovedaCaja")
    private String bovedaCaja;

    @Inject
    private BovedaCajaProvider bovedaCajaProvider;

    @Inject
    private HistorialesBovedaCajaResource cajaBovedaHistorialesResource;

    @Inject
    private BovedaCajaManager bovedaCajaManager;

    private BovedaCajaModel getBovedaCajaModel() {
        return bovedaCajaProvider.findById(bovedaCaja);
    }

    @Override
    public BovedaCajaRepresentation toRepresentation() {
        BovedaCajaRepresentation rep = ModelToRepresentation.toRepresentation(getBovedaCajaModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException("BovedaCaja no encontrado");
        }
    }

    @Override
    public void update(BovedaCajaRepresentation bovedaCajaRepresentation) {
        throw new NotFoundException();
    }

    @Override
    public Response enable() {
        throw new NotFoundException();
    }

    @Override
    public Response disable() {
        BovedaCajaModel bovedaCaja = getBovedaCajaModel();
        HistorialBovedaCajaModel historialBovedaCajaActivo = bovedaCaja.getHistorialActivo();
        if (historialBovedaCajaActivo != null) {
            if (historialBovedaCajaActivo.isAbierto()) {
                return new ErrorResponseException("BovedaCaja abierto",
                        "No se puede desactivar BovedaCaja hasta que se cierre", Response.Status.BAD_REQUEST)
                        .getResponse();
            }

            List<DetalleHistorialBovedaCajaModel> detalleHistorialBovedaCaja = historialBovedaCajaActivo
                    .getDetalle();
            Function<DetalleHistorialBovedaCajaModel, BigDecimal> totalMapper = detalle -> detalle.getValor()
                    .multiply(new BigDecimal(detalle.getCantidad()));
            BigDecimal saldoHistorialBoveda = detalleHistorialBovedaCaja.stream().map(totalMapper)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            if (saldoHistorialBoveda.compareTo(BigDecimal.ZERO) != 0) {
                return new ErrorResponseException("BovedaCaja con saldo", "BovedaCaja tiene saldo="
                        + saldoHistorialBoveda + ", no se puede desactivar", Response.Status.BAD_REQUEST)
                        .getResponse();
            }
        }

        boolean result = bovedaCajaManager.disable(bovedaCaja);
        if (result) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("BovedaCaja no pudo ser desactivado", Response.Status.BAD_REQUEST);
        }
    }

    @Override
    public Response remove() {
        BovedaCajaModel bovedaCaja = getBovedaCajaModel();
        if (bovedaCaja == null) {
            throw new NotFoundException("BovedaCaja no encontrada");
        }
        boolean removed = bovedaCajaProvider.remove(bovedaCaja);
        if (removed) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("BovedaCaja no pudo ser eliminado", Response.Status.BAD_REQUEST);
        }
    }

    @Override
    public HistorialesBovedaCajaResource historiales() {
        return cajaBovedaHistorialesResource;
    }

}
