import akka.actor.ActorSystem
import akka.stream.scaladsl.{Source, Sink, Flow}

object OperadoresEjemplo extends App {
  // Crear un sistema de actores (necesario para Akka Streams)
  implicit val system: ActorSystem = ActorSystem("OperadoresEjemplo")

  // Fuente de números del 1 al 10
  val numerosFuente = Source(1 to 10)

  // Definir un flujo que filtra y duplica los números pares
  val flujoOperadores = Flow[Int]
    .filter(numero => numero % 2 == 0) // Filtra los números pares
    .map(numero => numero * 2)         // Duplica los números

  // Unir la fuente y el flujo de operadores, y conectarlos a un sumidero (sink)
  val resultado = numerosFuente.via(flujoOperadores).runWith(Sink.foreach(println))

  // Esperar a que el flujo se complete antes de cerrar el sistema de actores
  resultado.onComplete(_ => system.terminate())
}


