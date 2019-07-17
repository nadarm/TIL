package com.example.boardmvvmrx.data.local

import com.example.boardmvvmrx.ArticleDataSource
import com.example.boardmvvmrx.data.Article
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

class ArticleLocalDataSource private constructor(
    private val articleDao: ArticleDao
) : ArticleDataSource {

    override fun getAllArticles(): Flowable<List<Article>> {
        return articleDao.getAllArticles()
    }

    override fun getArticle(articleId: Long): Observable<Article> {
        return articleDao.getArticle(articleId)
    }

    override fun insertArticle(article: Article): Single<Long> {
        return articleDao.insertArticle(article)
    }

    override fun updateArticle(article: Article): Single<Int> {
        return articleDao.updateArticle(article)
    }

    companion object {
        private var INSTANCE: ArticleLocalDataSource? = null

        fun getInstance(articleDao: ArticleDao): ArticleLocalDataSource {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = ArticleLocalDataSource(articleDao)
                }
            }
            return INSTANCE!!
        }
    }
}