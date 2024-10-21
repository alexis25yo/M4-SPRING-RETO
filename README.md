# M4-SPRING-RETO
# Reto: Aplicación de Gestión de Cuenta Bancaria con SpringBoot y PostgreSQL
> **Larry M. Ramírez - Coach Técnico**

## Objetivo.
Crear una aplicación Spring Boot que permita la gestión de cuentas bancarias, aplicando los conceptos vistos durante el módulo: Spring Boot, Inyección de Dependencias, Spring MVC, manejo de excepciones, y JPA con PostgreSQL. El sistema debe permitir realizar operaciones bancarias sobre diferentes tipos de cuentas, gestionando el saldo, registrando las transacciones, y almacenando la información en una base de datos PostgreSQL.

## Funcionalidades Principales
1. Cuentas Bancarias:
Debes crear una clase abstracta y dos clases hijas:
	- `Cuenta`: representa la cuenta bancaria básica, con los siguientes atributos:
	    - `id`: Identificador único (generado automáticamente).
	    - `saldo`: Saldo actual de la cuenta.
	    - `numeroCuenta`: Número único de cuenta.
	    - `listaTransacciones`: Historial de transacciones de la cuenta.
	- Utiliza herencia para definir dos tipos de cuentas:
	    - `CuentaBásica`: cuenta estándar con comisiones por algunas operaciones.
	    - `CuentaPremium`: cuenta sin comisiones adicionales.
2. Operaciones sobre las cuentas: Implementa las siguientes operaciones que afectarán el saldo de las cuentas. Utiliza polimorfismo para que las reglas cambien según el tipo de cuenta:
	- **Depósito desde sucursal**: sin costo.
	- **Depósito desde cajero automático**: costo de 2 USD (solo en `CuentaBásica`).
	- **Depósito desde otra cuenta**: costo de 1.5 USD.
	- **Compra en establecimiento físico**: sin costo.
	- **Compra en página web**: costo de 5 USD por seguro contra robos.
	- **Retiro en cajero**: costo de 1 USD (solo en `CuentaBásica`).
3. Historial de Transacciones: Al realizar cualquier operación, se debe registrar la transacción en una tabla de historial en PostgreSQL. Cada transacción debe tener:
	- Tipo de transacción (depósito, retiro, compra).
	- Monto de la operación.
	- Fecha y hora de la transacción (autogenerados por el sistema).
	- Código único de transacción.
4. Consultas:
	- Permitir al usuario consultar el saldo actual de su cuenta.
	- Consultar el historial de las últimas 5 transacciones realizadas (ordenadas por fecha).
## Requerimientos Técnicos
1. Base de Datos:
	- Utiliza PostgreSQL para almacenar la información de cuentas y transacciones.
	- Configura el acceso a PostgreSQL en el archivo `application.properties`.
2. SpringBoot y JPA:
	- Utiliza SpringBoot con JPA para el acceso a la base de datos y las operaciones CRUD sobre cuentas y transacciones.
	- Implementa controladores REST para cada funcionalidad: operaciones, consultas, y historial.
3. Manejo de Excepciones:
	- Implementa manejo de excepciones para errores comunes como `CuentaNoEncontrada`, `SaldoInsuficiente`, y `TransacciónNoPermitida`.

**Nota:** Al igual que los talleres, el reto debe entregarse por medio de la estrategia establecida para la formación, por medio de un Pull Request desde el repositorio Fork hacia la rama main del repositorio del reto.



## Solución
1. Creación bases de datos:
-- Crear tabla cuenta
CREATE TABLE cuenta (
    id SERIAL PRIMARY KEY,
    saldo NUMERIC(15, 2) NOT NULL,
    numero_cuenta VARCHAR(20) NOT NULL,
    tipo_cuenta VARCHAR(10) NOT NULL
);

-- Crear tabla transaccion
CREATE TABLE transaccion (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    monto NUMERIC(15, 2) NOT NULL,
    fecha_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    codigo_unico VARCHAR(50) NOT NULL,
    cuenta_id INTEGER NOT NULL,
    CONSTRAINT fk_cuenta FOREIGN KEY (cuenta_id) REFERENCES cuenta(id)
);

-- Insertar registros en la tabla cuenta.
INSERT INTO cuenta (saldo, numero_cuenta, tipo_cuenta) VALUES (1000.00, '1234567890', 'BASICA');
INSERT INTO cuenta (saldo, numero_cuenta, tipo_cuenta) VALUES (2000.00, '1234567891', 'PREMIUM');
INSERT INTO cuenta (saldo, numero_cuenta, tipo_cuenta) VALUES (1500.00, '1234567892', 'BASICA');
INSERT INTO cuenta (saldo, numero_cuenta, tipo_cuenta) VALUES (2500.00, '1234567893', 'PREMIUM');
INSERT INTO cuenta (saldo, numero_cuenta, tipo_cuenta) VALUES (3000.00, '1234567894', 'BASICA');

-- Insertar registros en la tabla transaccion
INSERT INTO transaccion (tipo, monto, codigo_unico, cuenta_id) VALUES ('DEPOSITO_SUCURSAL', 100.00, 'TX-1', 1);
INSERT INTO transaccion (tipo, monto, codigo_unico, cuenta_id) VALUES ('COMPRA_FISICA', 50.00, 'TX-2', 2);
INSERT INTO transaccion (tipo, monto, codigo_unico, cuenta_id) VALUES ('RETIRO_CAJERO', 20.00, 'TX-3', 3);
INSERT INTO transaccion (tipo, monto, codigo_unico, cuenta_id) VALUES ('COMPRA_WEB', 30.00, 'TX-4', 4);
INSERT INTO transaccion (tipo, monto, codigo_unico, cuenta_id) VALUES ('DEPOSITO_OTRA_CUENTA', 200.00, 'TX-5', 5);

-- Verificar información de las tablas.
SELECT * FROM cuenta;
SELECT * FROM transaccion;
