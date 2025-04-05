import { Person } from './person';

describe('Person', () => {
  it('should create an instance', () => {
    expect(new Person()).toBeTruthy();
  });
});

describe("Pruebas parametrizadas", () => {
  let component: Person;
  beforeEach(() => {
    component = new Person();
  });
  it('Casos de prueba para validar que la edad es asignada correctamente',
    () => {
      [
        { age: 0 },
        { age: 5 },
        { age: 17 },
      ].forEach(({ age }) => {
        component.setAge(age);
        expect(component.getAge()).toBe(age);
      });
    });

  it('Casos de prueba para validar que la persona puede conducir', () => {
    [
      { age: 18 },
      { age: 21 },
      { age: 99 },
    ].forEach(({ age }) => {
      component.setAge(age);
      expect(component.canDrive()).toBe(true);
    });
  });
  
  it('Casos de prueba para validar que la persona NO puede conducir', () => {
    [
      { age: -1 },
      { age: 10 },
      { age: 17 },
    ].forEach(({ age }) => {
      component.setAge(age);
      expect(component.canDrive()).toBe(false);
    });
  });
});
