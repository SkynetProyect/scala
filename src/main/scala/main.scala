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
      marca + ": " + total
    }.toList
  }

  def vehiculoMasCaroEnUltimoAño(ventas: List[Venta]): Vehiculo =
    val añoMax = ventas.map(_.vehiculo.año).max
    ventas
      .filter(_.vehiculo.año == añoMax)
      .map(_.vehiculo)
      .reduce((venta1, venta2) => if venta1.precio >= venta2.precio then venta1 else venta2)

  def ordenarPorAñoDesc(ventas: List[Venta]): List[Venta] =
    ventas.sortBy(_.vehiculo.año).reverse

  println("Vehiculos con precio mayor o igual a 30 mil (COP):")
  filtrarMayores(ventas, 30000).foreach(venta =>
    println(s"${venta.marca}, ${venta.modelo}: ${venta.precio}")
  )

  println("\nTotal de ventas por marca:")
  totalVentas(ventas).foreach(println)

  println("\nVehiculo mas caro vendido en el ultimo año vigente:")
  val vehiculoMasCaro = vehiculoMasCaroEnUltimoAño(ventas)
  println(s"${vehiculoMasCaro.marca} ${vehiculoMasCaro.modelo} (${vehiculoMasCaro.año}): ${vehiculoMasCaro.precio}")

  println("\nVentas ordenadas por año (De mas recientes a mas antiguos):")
  ordenarPorAñoDesc(ventas).foreach { venta =>
    val vehiculo = venta.vehiculo
    println(s"${vehiculo.año} - ${vehiculo.marca} ${vehiculo.modelo}: ${vehiculo.precio}")
  }

}