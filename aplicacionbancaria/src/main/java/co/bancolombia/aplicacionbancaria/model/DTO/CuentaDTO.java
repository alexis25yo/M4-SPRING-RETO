package co.bancolombia.aplicacionbancaria.model.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


public class CuentaDTO {

    //@NotEmpty(message = "La cuenta es obligatoria!!")
    private Long cuenta;

    public Long getCuenta() {
        return cuenta;
    }

    public void setCuenta(Long cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public String toString() {
        return "CuentaDTO{" +
                "cuenta=" + cuenta +
                '}';
    }
}

