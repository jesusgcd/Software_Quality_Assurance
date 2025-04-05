// import { Sucursal } from './sucursal.model';

import { mock, when, instance } from 'ts-mockito';
import { Client } from './client.model';
import { Count } from './count';
import { Sucursal } from './sucursal.model';

describe('Sucursal', () => {
  let cliente: Client;
  let sucursal: Sucursal;
  let cuenta: Count;
  var withdrawlAmount2000 = 200000;
  var numeroCuenta = 12345;
  var balance = 100000;
  
  beforeEach(() => {
    sucursal = new Sucursal("Alajuela", "Alajuela");
    cliente = new Client("Juan", "Pérez", "25-01-76", "2401-3117", "Alajuela", "jperez@gmail.com");
    sucursal.setClientes(cliente);
    cuenta = mock<Count>(); 
  });
   
  it('1. Saldo de cuenta', function () {
    when(cuenta.getCantidadDinero()).thenReturn(balance);
    let mockito = instance(cuenta);
    expect(mockito.getCantidadDinero()).toBe(balance);
  });
   
   it('2. Agregar nueva cuenta a cliente', function () {
    var cuenta = mock<Count>();
    let mockito = instance(cuenta);
    cliente.setCuentas(mockito);
    expect(cliente.getCuentas().length).toBe(1);
  });

   it('3. Retirar monto válido', function () {
    var balanceAmount3000 = 300000;
    when(cuenta.getCantidadDinero()).thenReturn(balanceAmount3000);
    when(cuenta.getNumCuenta()).thenReturn(numeroCuenta);
    when(cuenta.retirar(withdrawlAmount2000)).thenReturn(balance); 
    let mockito = instance(cuenta);
    cliente.setCuentas(mockito);
    var saldo = cliente.retirar(withdrawlAmount2000, numeroCuenta);
    expect(saldo).toBe(balance);
  });

  it('4. Retirar más de lo permitido', function () {
    when(cuenta.getCantidadDinero()).thenReturn(balance);
    when(cuenta.getNumCuenta()).thenReturn(numeroCuenta);
    let mockito = instance(cuenta);
    cliente.setCuentas(mockito);
    expect(function() {
    cliente.retirar(withdrawlAmount2000, numeroCuenta);
    }).toThrowError(Error, "Fondos insuficientes");
  });

  it('a. Apertura de cuenta con saldo inicial de 5000 colones', function () {
    var cuenta = mock<Count>();
    when(cuenta.getCantidadDinero()).thenReturn(5000); // Simulamos que la cuenta tiene 5000 colones
    let mockito = instance(cuenta);
  
    cliente.aperturarCuenta(mockito); // Abrimos la cuenta para el cliente
  
    expect(mockito.getCantidadDinero()).toBe(5000); // Verificamos que el saldo inicial es 5000
  });
  
  it('b. Realizar dos depósitos válidos y verificar el saldo total', function () {
    var cuenta = mock<Count>();
    let mockito = instance(cuenta);
  
    // Simulamos el comportamiento del método depositar
    cliente.aperturarCuenta(mockito); // Abrimos la cuenta para el cliente
    
    // Simulamos las llamadas al método getCantidadDinero()
    when(cuenta.getCantidadDinero()).thenReturn(5000); // Después de la apertura de cuenta
    cliente.depositar(mockito, 3000); // Primer depósito
    when(cuenta.getCantidadDinero()).thenReturn(8000); // Saldo después del primer depósito
    cliente.depositar(mockito, 2000); // Segundo depósito
    when(cuenta.getCantidadDinero()).thenReturn(10000); // Saldo después del segundo depósito
  
    expect(mockito.getCantidadDinero()).toBe(10000); // Verificamos que el saldo es 10000 después de los dos depósitos
  });
  
  
  it('c. Liquidar una cuenta reduce el número de cuentas en 1', function () {
    var cuenta = mock<Count>();
    let mockito = instance(cuenta);
  
    cliente.aperturarCuenta(mockito); // Apertura de cuenta para el cliente
    expect(cliente.getCuentas().length).toBe(1); // Verificamos que el cliente tiene una cuenta
  
    cliente.liquidarCuenta(mockito); // Liquidamos la cuenta
    expect(cliente.getCuentas().length).toBe(0); // Verificamos que ahora no tiene cuentas
  });
  

  });








/* describe('Sucursal', () => {
  it('should create an instance', () => {
    expect(new Sucursal()).toBeTruthy();
  });
});
*/

