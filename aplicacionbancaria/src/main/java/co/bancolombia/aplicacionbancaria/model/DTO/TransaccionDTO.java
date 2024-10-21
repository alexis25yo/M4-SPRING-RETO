package co.bancolombia.aplicacionbancaria.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Validated
public class TransaccionDTO {
    @NotNull(message = "La cuenta es obligatoria!!")
    private long cuenta;
    @NotNull(message = "El monto es obligatorio!!")
    @Positive(message = "El monto debe ser mayor a cero!!")
    private BigDecimal monto;
    @NotBlank(message = "La descripción es obligatoria!!")
    private String descripcion;

    public TransaccionDTO(long cuenta, BigDecimal monto, String descripcion) {
        this.cuenta = cuenta;
        this.monto = monto;
        this.descripcion = descripcion;
    }

    public long getCuenta() {
        return cuenta;
    }

    public void setCuenta(long cuenta) {
        this.cuenta = cuenta;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TransaccionDTO{" +
                "cuenta='" + cuenta + '\'' +
                ", monto=" + monto +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
