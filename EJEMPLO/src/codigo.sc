import rx.lang.scala.{Observable, Subscriber}

object ReactiveExample extends App {

  // Crear un Observable emitiendo números del 1 al 5
  val observable: Observable[Int] = Observable.from(Seq(1, 2, 3, 4, 5))

  // Aplicar operadores: filtrar los números pares y luego mapearlos al cuadrado
  val resultObservable: Observable[Int] = observable
    .filter(_ % 2 == 0)
    .map(x => x * x)

  // Suscribirse al resultado y manejar los eventos
  val subscriber: Subscriber[Int] = resultObservable.subscribe(
    onNext = (value: Int) => println(s"Next: $value"),
    onError = (error: Throwable) => println(s"Error: $error"),
    onCompleted = () => println("Completed")
  )

  // Esperar a que el programa no termine inmediatamente
  Thread.sleep(1000)
}
