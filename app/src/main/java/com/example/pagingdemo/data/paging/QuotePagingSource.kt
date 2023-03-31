package com.example.pagingdemo.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingdemo.data.api.QuoteAPI
import com.example.pagingdemo.data.models.Quote

class QuotePagingSource(private val quoteAPI: QuoteAPI) : PagingSource<Int, Quote>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Quote> {
        return try {
            val position = params.key ?: 1
            val response = quoteAPI.getQuotes(position)

            return LoadResult.Page(
                data = response.results,
                prevKey = if (position == 1) null else position.minus(1),
                nextKey = if (position == response.totalPages) null else position.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Quote>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}