package com.example.boardmvp

import com.example.boardmvp.data.Article
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface ArticleDataSource {

    fun getAllArticles(): Flowable<List<Article>>

    fun getArticle(articleId: Long): Single<Article>

    fun insertArticle(article: Article): Completable
}