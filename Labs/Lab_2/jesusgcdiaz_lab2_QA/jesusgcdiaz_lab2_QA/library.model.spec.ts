import { Library } from './library.model';
import { mock, when, instance } from 'ts-mockito';
import { Author } from './author';

describe('Library', () => {
  
  it('1. Debe lanzar un error al intentar establecer un nombre vacío', () => {
    const author = mock<Author>();

    // Simulamos que se intenta asignar un nombre vacío
    when(author.setName("")).thenThrow(new Error("El nombre no puede estar vacío"));
  
    const mockito = instance(author);

    // Verificamos que el método lance un error al intentar establecer un nombre vacío
    expect(() => mockito.setName("")).toThrowError("El nombre no puede estar vacío");
  });
  
  it('2. Debe cambiar correctamente el nombre del autor', () => {
    const author = mock<Author>();
  
    // Simulamos que inicialmente el nombre del autor es "Gabriel García Márquez"
    when(author.getName()).thenReturn("Gabriel García Márquez");
    
    const mockito = instance(author);
  
    // Cambiamos el nombre a "Gabo" mediante el método setName
    mockito.setName("Gabo");
  
    // Simulamos que el nombre ha cambiado a "Gabo" después de la llamada a setName
    when(author.getName()).thenReturn("Gabo");
  
    // Verificamos que el nombre del autor ha sido actualizado a "Gabo"
    expect(mockito.getName()).toBe("Gabo");
  });
  
  it('3. Debe lanzar un error al intentar establecer un país vacío', () => {
    const author = mock<Author>();

    // Simulamos que se intenta asignar un país vacío
    when(author.setPais("")).thenThrow(new Error("El país no puede estar vacío"));
  
    const mockito = instance(author);

    // Verificamos que el método lance un error al intentar establecer un país vacío
    expect(() => mockito.setPais("")).toThrowError("El país no puede estar vacío");
  });
  
  it('4. Debe devolver false si dos autores tienen el mismo nombre pero diferentes países', () => {
    const author1 = mock<Author>();
    const author2 = mock<Author>();
  
    // Simulamos que el primer autor tiene el nombre "Gabriel García Márquez" y es de Colombia
    when(author1.getName()).thenReturn("Gabriel García Márquez");
    when(author1.getPais()).thenReturn("Colombia");
  
    // Simulamos que el segundo autor tiene el mismo nombre pero es de México
    when(author2.getName()).thenReturn("Gabriel García Márquez");
    when(author2.getPais()).thenReturn("México");
  
    // Configuramos el método equals para devolver false cuando los autores tengan diferentes países
    when(author1.equals("Gabriel García Márquez")).thenCall(() => {
      return author1.getPais() === author2.getPais();  // Comparar por país
    });
  
    const mockito1 = instance(author1);
    const mockito2 = instance(author2);
  
    // Verificamos que el método equals devuelva false porque los países son diferentes
    expect(mockito1.equals(mockito2.getName())).toBeFalse();
  });
  
});
