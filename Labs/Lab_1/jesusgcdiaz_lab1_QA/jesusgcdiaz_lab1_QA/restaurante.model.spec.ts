import { Restaurant } from './restaurant.model';
import { SortedListOfImmutables } from './sorted-list-of-immutables.model';
import { Entree } from './entree.model';
import { Food } from './food.model';

describe('Restaurant', () => {
  let restaurante: Restaurant;
  let listaAlimentos: SortedListOfImmutables;
  let entrada: Entree;
  let alimento1: Food;
  let alimento2: Food;

  // Antes de cada prueba, inicializamos los objetos necesarios
  beforeEach(() => {
    restaurante = new Restaurant('Restaurante de Prueba', 1000);  // Inicializa con 1000 en efectivo
    alimento1 = new Food('Bacon', 89, 185, 'Bacon.jpg');
    alimento2 = new Food('Egg', 47, 89, 'Egg.jpg');
    listaAlimentos = new SortedListOfImmutables(null);  // Lista vacía de alimentos
  });

  // Prueba para realizar un pedido con un solo alimento en inventario
  it('debería procesar el pedido con un solo alimento en el inventario', () => {
    listaAlimentos.add(alimento1);  // Añade solo Bacon
    entrada = new Entree('Entrada de Prueba', listaAlimentos);  // Crea una entrada con Bacon

    restaurante.addShipmentToInventory(listaAlimentos);  // Añade los alimentos al inventario

    const valorMinorista = entrada.getRetailValue();
    console.log('Valor minorista de la entrada (Bacon):', valorMinorista);  // Depuración

    const efectivoInicial = restaurante.getCash();
    console.log('Efectivo inicial antes del pedido:', efectivoInicial);  // Depuración

    const resultado = restaurante.placeOrder(entrada);
    const efectivoFinal = restaurante.getCash();

    console.log('Efectivo final después del pedido:', efectivoFinal);  // Depuración

    expect(resultado).toBe(true);  // El pedido debería realizarse correctamente
    expect(efectivoFinal).toBe(efectivoInicial + valorMinorista);  // Verifica que el efectivo haya aumentado correctamente
  });

  // Prueba para realizar un pedido con varios alimentos en inventario
  it('debería procesar el pedido con varios alimentos en el inventario', () => {
    listaAlimentos.add(alimento1);
    listaAlimentos.add(alimento2);  // Añade Bacon y Egg a la lista
    entrada = new Entree('Entrada de Prueba', listaAlimentos);  // Crea una entrada con Bacon y Egg

    restaurante.addShipmentToInventory(listaAlimentos);  // Añade los alimentos al inventario

    const valorMinorista = entrada.getRetailValue();
    console.log('Valor minorista de la entrada (Bacon + Egg):', valorMinorista);  // Depuración

    const efectivoInicial = restaurante.getCash();
    console.log('Efectivo inicial antes del pedido:', efectivoInicial);  // Depuración

    const resultado = restaurante.placeOrder(entrada);
    const efectivoFinal = restaurante.getCash();

    console.log('Efectivo final después del pedido:', efectivoFinal);  // Depuración

    expect(resultado).toBe(true);  // El pedido debería realizarse correctamente
    expect(efectivoFinal).toBe(efectivoInicial + valorMinorista);  // Verifica que el efectivo haya aumentado correctamente
  });

  // Prueba para evitar un pedido si faltan elementos en el inventario
  it('no debería procesar el pedido si faltan elementos en el inventario', () => {
    listaAlimentos.add(alimento1);  // Solo Bacon en la lista
    entrada = new Entree('Entrada de Prueba', listaAlimentos);  // Crea una entrada con Bacon

    const resultado = restaurante.placeOrder(entrada);
    expect(resultado).toBe(false);  // El pedido no debería realizarse si el inventario está vacío
  });
});
