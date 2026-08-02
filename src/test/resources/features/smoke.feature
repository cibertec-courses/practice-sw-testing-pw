# language: es

@smoke
Característica: Verificacion del arranque de la suite
  Como equipo de automatizacion
  Quiero validar que Cucumber + Serenity + Playwright funciona
  Para poder construir los escenarios de negocio sobre una base solida


  Escenario: La tienda Toolshop carga correctamente
    Dado que Carlos quiere comprar herramientas
    Cuando abre la pagina principal de la tienda
    Entonces deberia ver productos disponinles en el catalogo

