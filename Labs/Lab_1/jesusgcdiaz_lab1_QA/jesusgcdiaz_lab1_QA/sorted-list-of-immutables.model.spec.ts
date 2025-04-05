import { SortedListOfImmutables } from './sorted-list-of-immutables.model';
import { Food } from './food.model';

describe('SortedListOfImmutables', () => {
    let lista: SortedListOfImmutables;
    let alimento1: Food;
    let alimento2: Food;
    let listaParaVerificar: SortedListOfImmutables;
  
    // Antes de cada prueba, inicializamos los objetos necesarios
    beforeEach(() => {
      alimento1 = new Food('Bacon', 89, 185, 'Bacon.jpg');
      alimento2 = new Food('Egg', 47, 89, 'Egg.jpg');
      lista = new SortedListOfImmutables(null);  // Lista principal vacía
      listaParaVerificar = new SortedListOfImmutables(null);  // Lista que verificaremos
    });
  
    // Prueba parametrizada para verificar la disponibilidad de elementos en la lista
    it('debería verificar la disponibilidad de los elementos en la lista', () => {
      lista.add(alimento1);  // Añade Bacon a la lista principal
      listaParaVerificar.add(alimento1);  // Añade Bacon a la lista para verificar
  
      const resultado = lista.checkAvailabilityToList(listaParaVerificar);
      expect(resultado).toBe(true);  // Espera que Bacon esté en la lista principal
    });
  
    // Prueba para devolver falso si los elementos no están en la lista
    it('debería devolver falso si los elementos no están en la lista', () => {
      lista.add(alimento1);  // Solo Bacon está en la lista principal
      listaParaVerificar.add(alimento2);  // Solo Egg está en la lista para verificar
  
      const resultado = lista.checkAvailabilityToList(listaParaVerificar);
      expect(resultado).toBe(false);  // Espera que Egg no esté en la lista principal
    });
  
    // Prueba para verificar múltiples elementos en la lista
    it('debería devolver verdadero para múltiples elementos en la lista', () => {
      lista.add(alimento1);
      lista.add(alimento2);  // Añade Bacon y Egg a la lista principal
      listaParaVerificar.add(alimento1);
      listaParaVerificar.add(alimento2);  // Añade ambos a la lista para verificar
  
      const resultado = lista.checkAvailabilityToList(listaParaVerificar);
      expect(resultado).toBe(true);  // Espera que ambos alimentos estén en la lista
    });
});
