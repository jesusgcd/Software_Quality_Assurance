import '../../node_modules/cypress-xpath';

Cypress.on('uncaught:exception', (err, runnable) => {
    return false;
});

/**
 * Prueba: Búsqueda sin criterios
 * Objetivo: Verificar que al hacer clic en el botón de búsqueda sin seleccionar ningún criterio se desplieguen todas las propiedades disponibles.
 * Datos de prueba: No se especifican criterios de búsqueda.
 * Resultado esperado: Se muestra un listado de todas las propiedades en la página.
 */
describe('Prueba de búsqueda sin criterios', () => {

    it('Debe mostrar un listado de todas las propiedades cuando se hace clic en el botón de búsqueda sin especificar criterios', () => {
        cy.visit('https://www.bnventadebienes.com/properties/search');
        cy.wait(5000); // Espera para asegurar que la página está completamente cargada

        // Hacer clic en el botón de búsqueda usando XPath
        cy.xpath('//button[@type="submit" and contains(@class, "btn-primary-action")]').click();

        // Verificar que se muestre el listado de propiedades
        cy.xpath('//div[@id="property-index"]', { timeout: 10000 }).should('be.visible');
    });

});

/**
 * Prueba: Búsqueda con localización en Alajuela
 * Objetivo: Verificar que al seleccionar Alajuela en los campos de Provincia, Cantón y Distrito, se muestren propiedades en esa ubicación específica.
 * Datos de prueba: Selección de "Alajuela" en Provincia, Cantón y Distrito.
 * Resultado esperado: Aparece al menos una propiedad que coincida con la ubicación seleccionada.
 */
describe('Prueba de búsqueda con localización en Alajuela', () => {

    it('Debe mostrar una propiedad ubicada en Alajuela al seleccionar Alajuela en los tres campos de localización', () => {
        cy.visit('https://www.bnventadebienes.com/properties/search');
        cy.wait(5000); // Esperar a que la página se cargue completamente

        // Seleccionar "Alajuela" en los campos de Provincia, Cantón y Distrito usando XPath
        cy.xpath('//*[@id="ProvinceId"]').select('Alajuela', { force: true });
        cy.xpath('//*[@id="CantonId"]').select('Alajuela', { force: true });
        cy.xpath('//*[@id="DistrictId"]').select('Alajuela', { force: true });

        // Hacer clic en el botón de búsqueda
        cy.xpath('//button[@type="submit" and contains(@class, "btn-primary-action")]').click();

        // Verificar que solo aparece una propiedad con los detalles esperados
        cy.xpath('//div[contains(@class, "property-item-info")]').should('have.length', 1); // Confirma un único resultado

    });

});

/**
 * Prueba: Búsqueda con filtros en Alajuela y opciones adicionales
 * Objetivo: Verificar que al aplicar filtros de ubicación en Alajuela y opciones adicionales como "Se escuchan ofertas" y "Destacado", el sistema filtre correctamente.
 * Datos de prueba: Alajuela en ubicación, casillas de "Se escuchan ofertas" y "Destacado" activadas.
 * Resultado esperado: Muestra el mensaje "Lo sentimos, pero no hay propiedades que cumplan con los criterios de búsqueda ingresados." si no hay coincidencias.
 */
describe('Prueba de búsqueda con filtros en Alajuela y opciones adicionales', () => {

    it('Debe aplicar filtros de localización en Alajuela y marcar opciones adicionales', () => {
        cy.visit('https://www.bnventadebienes.com/properties/search');
        cy.wait(5000); // Esperar a que la página se cargue completamente

        // Seleccionar "Alajuela" en los campos de Provincia, Cantón y Distrito usando XPath
        cy.xpath('//*[@id="ProvinceId"]').select('Alajuela', { force: true });
        cy.xpath('//*[@id="CantonId"]').select('Alajuela', { force: true });
        cy.xpath('//*[@id="DistrictId"]').select('Alajuela', { force: true });

        // Marcar las opciones "Se escuchan ofertas" y "Destacado"
        cy.xpath('//*[@id="MustBeNegotiable"]').check({ force: true });
        cy.xpath('//*[@id="MustBeHighlighted"]').check({ force: true });

        // Hacer clic en el botón de búsqueda
        cy.xpath('//button[@type="submit" and contains(@class, "btn-primary-action")]').click();

        // Verificar que el mensaje de "sin resultados" aparezca en la página
        cy.contains('p.site-secondary-color4', 'Lo sentimos, pero no hay propiedades que cumplan con los criterios de búsqueda ingresados.')
            .should('be.visible', { timeout: 10000 });

    });

});

/**
 * Prueba: Búsqueda con rango de precio, tipo de venta y estado
 * Objetivo: Verificar que al aplicar un rango de precio y seleccionar el tipo de venta "En Concurso" y estado "Disponible", el sistema filtre propiedades dentro de estos parámetros.
 * Datos de prueba: Rango de precio de 1,000,000 a 10,000,000, tipo de venta "En Concurso", estado "Disponible".
 * Resultado esperado: Muestra una lista de propiedades que cumplen con los criterios especificados.
 */
describe('Prueba de búsqueda con rango de precio, tipo de venta y estado', () => {
    it('Debe aplicar un rango de precio y seleccionar tipo de venta y estado de propiedad', () => {
        // Visitar la página de búsqueda de propiedades
        cy.visit('https://www.bnventadebienes.com/properties/search');
        cy.wait(5000); // Esperar que la página cargue completamente

        // Ingresar el rango de precio
        cy.xpath('//*[@id="MinPrice"]').type('1000000', { force: true });
        cy.xpath('//*[@id="MaxPrice"]').type('10000000', { force: true });

        // Seleccionar "En Concurso" en el campo Tipo de venta
        cy.xpath('//*[@id="PropertySaleTypeId"]').select('En Concurso', { force: true });

        // Seleccionar "Disponible" en el campo Estado de la propiedad
        cy.xpath('//*[@id="PropertyStatusId"]').select('Disponible', { force: true });

        // Hacer clic en el botón de buscar
        cy.xpath('//button[@type="submit" and contains(@class, "btn-primary-action")]').click();

        // Verificar que aparezca la lista de resultados de propiedades en la página
        cy.xpath('//div[contains(@class, "property-item-info")]', { timeout: 10000 })
          .should('have.length.greaterThan', 0);
    });
});
