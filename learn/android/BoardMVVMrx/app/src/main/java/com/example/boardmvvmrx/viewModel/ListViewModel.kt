package com.example.boardmvvmrx.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.boardmvvmrx.ArticleRepository
import com.example.boardmvvmrx.BasicApp
import com.example.boardmvvmrx.data.Article
import com.example.boardmvvmrx.view.adapter.ArticleAdapter
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

interface ListViewModel {
    interface Inputs : ArticleAdapter.Delegate {
        fun newArticleClicked()
    }

    interface Outputs {
        fun articles(): Flowable<List<Article>>
        fun startDetailActivity(): Observable<Article>
        fun startNewArticleActivity(): Observable<Boolean>
    }

    class ViewModel(application: Application) : AndroidViewModel(application), Inputs, Outputs {

        private val repository: ArticleRepository by lazy { (application as BasicApp).getRepository() }
        private val compositeDisposable = CompositeDisposable()

        private val articleClicked: PublishSubject<Article> = PublishSubject.create()
        private val newArticleClicked: PublishSubject<Boolean> = PublishSubject.create()

        private val articles: Flowable<List<Article>> = repository.getAllArticles()
        private val startDetailActivity: Observable<Article> =
            articleClicked.throttleFirst(500, TimeUnit.MILLISECONDS)
        private val startNewArticleActivity: Observable<Boolean> =
            newArticleClicked.throttleFirst(500, TimeUnit.MILLISECONDS)

        val inputs: Inputs = this
        val outputs: Outputs = this

        override fun articles(): Flowable<List<Article>> = this.articles
        override fun startDetailActivity(): Observable<Article> = this.startDetailActivity
        override fun startNewArticleActivity(): Observable<Boolean> = this.startNewArticleActivity

        override fun newArticleClicked() {
            this.newArticleClicked.onNext(true)
        }

        override fun articleClicked(article: Article) {
            this.articleClicked.onNext(article)
        }

        override fun onCleared() {
            compositeDisposable.dispose()
            super.onCleared()
        }

    }
}