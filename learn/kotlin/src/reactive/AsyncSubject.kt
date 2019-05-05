package reactive

import io.reactivex.Observable
import io.reactivex.subjects.AsyncSubject

fun main(args: Array<String>) {
    val observable = Observable.just(1, 2, 3, 4)
    val subject = AsyncSubject.create<Int>()
    observable.subscribe(subject)
    subject.subscribe(
            { println(it) },
            { it.printStackTrace() },
            { println("Complete") }
    )
    subject.onComplete()
}