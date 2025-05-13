import play.api.libs.json._

// Definición de case classes
case class Vehiculo(id: Int, marca: String, modelo: String, ano: Int, precio: Double)
case class Venta(id: Int, vehiculo: Vehiculo, vendedor: String, cliente: String)

// Datos de ejemplo
val ventas = List(
  Venta(1, Vehiculo(101, "Toyota", "Corolla Cross", 2025, 70000), "Juan", "Gui"),
  Venta(2, Vehiculo(102, "Honda", "Civic", 2023, 25000), "Luis", "P"),
  Venta(3, Vehiculo(103, "Ford", "Mustang", 2021, 45000), "Sara", "Falta"),
  Venta(4, Vehiculo(104, "Tesla", "Model 3", 2022, 60000), "Sofi", "a"),
  Venta(5, Vehiculo(105, "Chevrolet", "Camaro", 2020, 38000), "Diego", "Roberto"),
  Venta(6, Vehiculo(106, "Toyota", "RAV4", 2023, 33000), "Maria", "Jose"),
  Venta(7, Vehiculo(107, "Ford", "Explorer", 2024, 45000), "Andres", "Felipe")
)

// Generación de estadísticas
val totalVentasPorMarca = ventas.groupBy(_.vehiculo.marca).map {
  case (marca, lista) => marca -> lista.size
}

val ventasMayor30M = ventas.filter(_.vehiculo.precio > 30000)

val vehiculoMasCaro = ventas.reduce((v1, v2) => if (v1.vehiculo.precio > v2.vehiculo.precio) v1 else v2)

val ventasOrdenadasPorAno = ventas.sortBy(_.vehiculo.ano)(Ordering[Int].reverse)

// Generación del reporte JSON
val reporteJson = Json.obj(
  "totalVentasPorMarca" -> totalVentasPorMarca,
  "ventasMayor30M" -> ventasMayor30M.map(v => Json.obj(
    "marca" -> v.vehiculo.marca,
    "modelo" -> v.vehiculo.modelo,
    "precio" -> v.vehiculo.precio,
    "anio" -> v.vehiculo.ano
  )),
  "vehiculoMasCaro" -> Json.obj(
    "marca" -> vehiculoMasCaro.vehiculo.marca,
    "modelo" -> vehiculoMasCaro.vehiculo.modelo,
    "precio" -> vehiculoMasCaro.vehiculo.precio
  ),
  "ventasOrdenadasPorAnio" -> ventasOrdenadasPorAno.map(v => Json.obj(
    "marca" -> v.vehiculo.marca,
    "modelo" -> v.vehiculo.modelo,
    "anio" -> v.vehiculo.ano
  ))
)

@main
def main(): Unit = {
  // Imprimir el JSON
  println(Json.prettyPrint(reporteJson))
}