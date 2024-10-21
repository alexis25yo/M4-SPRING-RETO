package co.bancolombia.aplicacionbancaria.config;

public class CuentaNoEncontradaException extends RuntimeException {
    public CuentaNoEncontradaException(Long id) {
        super("No se pudo encontrar la cuenta con el id: " + id);
    }
}