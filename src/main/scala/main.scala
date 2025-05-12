case class Vehiculo(
                     id: Int,
                     marca: String,
                     modelo: String,
                     año: Int,
                     precio: Double
                   )

case class Venta(
                  id: Int,
                  vehiculo: Vehiculo,
                  vendedor: String,
                  cliente: String
                )

val ventas: List[Venta] = List(
  Venta(
    1,
    Vehiculo(101, "Toyota", "Corolla Cross", 2025, 7000),
    vendedor = "Juan",
    cliente  = "Gui"
  ),
  Venta(
    2,
    Vehiculo(102, "Honda", "Civic", 2023, 25000),
    vendedor = "Luis",
    cliente  = "Perez"
  ),
  Venta(
    3,
    Vehiculo(103, "Ford", "Mustang", 2021, 45000),
    vendedor = "Sara",
    cliente  = "Falta"
  ),
  Venta(
    4,
    Vehiculo(104, "Tesla", "Model 3", 2022, 60000),
    vendedor = "Sofia",
    cliente  = "Ana"
  ),
  Venta(
    5,
    Vehiculo(105, "Chevrolet", "Camaro", 2020, 38000),
    vendedor = "Diego",
    cliente  = "Roberto"
  )
)



@main
def main(): Unit = {



  def filtrarMayores(lista: List[Venta], valor: Int): List[Vehiculo] = {
    lista.filter(_.vehiculo.precio >= valor).map(venta => venta.vehiculo).toList

  }

  def totalVentas(ventas: List[Venta]): List[String] = {
    ventas.groupBy(_.vehiculo.marca).map { case (marca, ventasmarca) =>
      val total = ventasmarca.map(_.vehiculo.precio).sum
      marca + ":" + total
    }.toList

  }



  }

