package com.nadarm.boardmvvmrx.presentation.viewModel

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.nadarm.boardmvvmrx.domain.model.Article
import com.nadarm.boardmvvmrx.domain.useCase.GetArticle
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


interface DetailViewModel {

    interface Inputs {
        fun intent(intent: Intent)
        fun editClicked()
    }

    interface Outputs {
        fun article(): Flowable<Article>
        fun startEditActivity(): Observable<Long>
    }

    class ViewModelImpl @Inject constructor(
        private val getArticleUseCase: GetArticle
    ) : ViewModel(), Inputs, Outputs {

        private val intent: PublishSubject<Intent> = PublishSubject.create()
        private val editClicked: PublishSubject<Unit> = PublishSubject.create()

        private val article: BehaviorProcessor<Article> = BehaviorProcessor.create()
        private val startEditActivity: BehaviorSubject<Long> = BehaviorSubject.create()

        val inputs: Inputs = this
        val outputs: Outputs = this

        init {
            val articleIdObservable = this.intent
                .map { intent -> intent.getLongExtra("articleId", 1) }
                .subscribeOn(Schedulers.io())

            articleIdObservable
                .toFlowable(BackpressureStrategy.LATEST)
                .flatMap(getArticleUseCase::execute)
                .subscribe(this.article)

            this.editClicked
                .withLatestFrom(articleIdObservable, BiFunction<Unit, Long, Long> { _, articleId -> articleId })
                .subscribe(this.startEditActivity)

        }

        override fun article(): Flowable<Article> = this.article
        override fun startEditActivity(): Observable<Long> = this.startEditActivity

        override fun intent(intent: Intent) {
            this.intent.onNext(intent)
        }

        override fun editClicked() {
            this.editClicked.onNext(Unit)
        }
    }
}
