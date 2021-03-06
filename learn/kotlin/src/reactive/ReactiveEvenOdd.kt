package reactive

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject


fun main(args: Array<String>) {
    val subject: Subject<Int> = PublishSubject.create()
    subject.map { it % 2 == 0 }.subscribe { println("The number is ${if (it) "Even" else "Odd"}") }

    subject.onNext(4)
    subject.onNext(9)

}