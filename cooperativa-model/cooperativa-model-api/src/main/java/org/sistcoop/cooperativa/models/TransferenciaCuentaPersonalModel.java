package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

public interface TransferenciaCuentaPersonalModel extends TransaccionClienteModel {

    String getNumeroCuentaOrigen();

    String getNumeroCuentaDestino();

    BigDecimal getMonto();

    String getReferencia();

}
