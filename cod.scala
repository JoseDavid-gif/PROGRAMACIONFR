ThisBuild / version := "0.1.0-SNAPSHOT"  

ThisBuild / scalaVersion := "3.1.3"  

lazy val root = (project in file("."))  
  .settings(
    name := "InvestigacionPFR",  

    // Agrega la dependencia de "akka-actor" de Typesafe Akka en la versión "2.8.0".
    libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.8.0",

    // Agrega la dependencia de "akka-stream" de Typesafe Akka en la versión "2.8.0".
    libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.8.0"
  )
-------------------------------------------------------------------------------------------------------------------------------------

import akka.actor.ActorSystem  // Importa la clase ActorSystem de Akka, que se utiliza para crear sistemas de actores.
import akka.stream.scaladsl.{Sink, Source}  // Importa clases y métodos específicos de Akka Streams para la construcción de flujos.

object cod {
  def main(args: Array[String]): Unit = {

    // Crea un sistema de actores de Akka con el nombre "EjemploReactiveScala"
    implicit val system: ActorSystem = ActorSystem("EjemploReactiveScala")

    // Crea un flujo fuente (Source) de números del 1 al 10
    val numbers: Source[Int, _] = Source(1 to 10)

    // Aplica una transformación al flujo de números calculando el cuadrado de cada número
    val numbersSquare = numbers.map(x => x * x)

    // Define un sink que imprime cada elemento del flujo
    val printNums = Sink.foreach[Int](println)

    // Ejecuta el flujo transformado y dirigir los elementos al "sink" de impresión
    numbersSquare.runWith(printNums)

    // Hace una pausa de 1 segundo para permitir que el flujo se ejecute antes de terminar el sistema de actores
    Thread.sleep(1000)

    // Termina el sistema de actores de Akka
    system.terminate()
  }
}



