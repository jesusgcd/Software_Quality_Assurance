import { inject, TestBed } from '@angular/core/testing';

import { CalculadoraService } from './calculadora.service';
import { CalculadoraComponent } from './calculadora.component';


describe('CalculadoraService', () => {
  let service: CalculadoraService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CalculadoraService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

describe('Sumar', function () {
  it('10 + 15 debe ser 25', inject([CalculadoraService], (service:
    CalculadoraService) => {
    expect(service.sumar(10, 15)).toBe(25);
  }));
});

describe('Sumar dos valores decimales', function () {
  it('2.5 + 3.6 debe ser 6.1', inject([CalculadoraService], (service: CalculadoraService) => {
    // Arrange
    let resultado;

    // Act
    resultado = service.sumar(2.5, 3.6);

    // Assert
    expect(resultado).toBe(6.1);
  }));
});

describe('Restar 2 menos 2', function () {
  it('2 - 2 debe ser 0', inject([CalculadoraService], (service: CalculadoraService) => {
    // Arrange
    let resultado;

    // Act
    resultado = service.restar(2, 2);

    // Assert
    expect(resultado).toBe(0);
  }));
});

describe('Restar 3 menos 4', function () {
  it('3 - 4 debe ser -1', inject([CalculadoraService], (service: CalculadoraService) => {
    // Arrange
    let resultado;

    // Act
    resultado = service.restar(3, 4);

    // Assert
    expect(resultado).toBe(-1);
  }));
});

describe('Multiplicar3 por 2', function () {
  it('3 * 2 debe ser 6', inject([CalculadoraService], (service: CalculadoraService) => {
    // Arrange
    let resultado;

    // Act
    resultado = service.multiplicar(3, 2);

    // Assert
    expect(resultado).toBe(6);
  }));
});

describe('Dividir', function () {
  it("2 dividido por 2 debe ser 1", inject([CalculadoraService],
    (service: CalculadoraService) => {
      expect(service.dividir(2, 2)).toBe(1);
    }));
  it("6 dividido por 0 debe generar una Excepción",
    inject([CalculadoraService], (service: CalculadoraService) => {
      expect(function () { service.dividir(6, 0); }).toThrowError(Error,
        'División por cero');
      expect(function () {
        service.dividir(6, 0);
      }).toThrowError('División por cero');
      expect(function () { service.dividir(6, 0); }).toThrowError(Error);
      expect(function () {
        service.dividir(6, 0);
      }).toThrowError(/División por cero/);
    }));
});

describe('Cancatenar numero', function () {
  let component: CalculadoraComponent;
  beforeEach(() => {
    component = new CalculadoraComponent();
  });
  it("concatenar 2 + 5 = 25", function () {
    expect(component.concatenarNumero("2", "5")).toBe("25");
  });
  it("concatenar Vacío + . = 0.", function () {
    expect(component.concatenarNumero("", ".")).toBe("0.");
  });
  it("concatenar Decimal 5. + 5 = 5.5", function () {
    expect(component.concatenarNumero("5.", "5")).toBe("5.5");
  });
  it("concatenar Más de un punto 5.5 + . = 5.5", function () {
    expect(component.concatenarNumero("5.5", ".")).toBe("5.5");
  });
});
