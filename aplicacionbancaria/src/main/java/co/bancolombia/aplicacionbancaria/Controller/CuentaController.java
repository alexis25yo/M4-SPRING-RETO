package co.bancolombia.aplicacionbancaria.Controller;

import co.bancolombia.aplicacionbancaria.model.Transaccion;
import co.bancolombia.aplicacionbancaria.service.CuentaService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping("/saldo/{cuentaId}")
    public BigDecimal obtenerSaldo(@PathVariable Long cuentaId) {
        return cuentaService.obtenerSaldo(cuentaId);
    }

    @GetMapping("/transacciones/{cuentaId}")
    public List<Transaccion> obtenerUltimasTransacciones(@PathVariable Long cuentaId) {
        return cuentaService.obtenerUltimasTransacciones(cuentaId);
    }

    @PostMapping("/deposito/sucursal/{cuentaId}")
    public BigDecimal depositoSucursal(@PathVariable Long cuentaId, @RequestParam BigDecimal monto) {
        return cuentaService.depositoSucursal(cuentaId, monto);
    }

    @PostMapping("/deposito/cajero/{cuentaId}")
    public BigDecimal depositoCajero(@PathVariable Long cuentaId, @RequestParam BigDecimal monto) {
        return cuentaService.depositoCajero(cuentaId, monto);
    }

    @PostMapping("/deposito/otra/{cuentaId}")
    public BigDecimal depositoOtraCuenta(@PathVariable Long cuentaId, @RequestParam BigDecimal monto) {
        return cuentaService.depositoOtraCuenta(cuentaId, monto);
    }

    @PostMapping("/compra/fisica/{cuentaId}")
    public BigDecimal compraFisica(@PathVariable Long cuentaId, @RequestParam BigDecimal monto) {
        return cuentaService.compraFisica(cuentaId, monto);
    }

    @PostMapping("/compra/web/{cuentaId}")
    public BigDecimal compraWeb(@PathVariable Long cuentaId, @RequestParam BigDecimal monto) {
        return cuentaService.compraWeb(cuentaId, monto);
    }

    @PostMapping("/retiro/cajero/{cuentaId}")
    public BigDecimal retiroCajero(@PathVariable Long cuentaId, @RequestParam BigDecimal monto) {
        return cuentaService.retiroCajero(cuentaId, monto);
    }
}