package com.example.pagingdemo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.pagingdemo.data.api.QuoteAPI
import com.example.pagingdemo.data.paging.QuotePagingSource
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val quoteAPI: QuoteAPI) {
    fun getQuotes() = Pager(PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { QuotePagingSource(quoteAPI) }).liveData
}