package com.nadarm.boardmvvmrx.domain.useCase

import com.nadarm.boardmvvmrx.domain.model.Article
import com.nadarm.boardmvvmrx.domain.repository.ArticleRepository
import io.reactivex.Single
import javax.inject.Inject

class InsertArticle @Inject constructor(private val repository: ArticleRepository) : SingleUseCase<Article, Long> {
    override fun execute(params: Article): Single<Long> {
        return repository.insertArticle(params)
    }
}