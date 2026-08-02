# language: es

@busqueda @regression
Característica: Busqueda de productos en el catalogo
  Como cliente de la tienda Toolshop
  Quiero buscar productos por su nombre
  Para encontrar rapidamente la herramienta que necesito

  Antecedentes:
    Dado que Carlos quiere comprar herramientas
    Cuando abre la pagina principal de la tienda

  @smoke
  Esquema del escenario: Busqueda de herramientas existentes
    Cuando busca el producto "<termino>"
    Entonces  deberia ver resultados que contienen "<esperado>"

    Ejemplos:
      | termino     | esperado    |
      | Pliers      | Pliers      |
      | Hammer      | Hammer      |
      | Screwdriver | Screwdriver |

    Escenario: Bsuqueda sin coincidencias
      Cuando busca el producto "asodunbasoucnoubnac"
      Entonces deberia ver el mensaje no hay resultados
