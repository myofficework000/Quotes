package com.example.pagingdemo.data.api

import com.example.pagingdemo.data.models.Quotes
import com.example.pagingdemo.util.Constants.END_POINT
import retrofit2.http.GET
import retrofit2.http.Query


interface QuoteAPI {

    @GET(END_POINT)
    suspend fun getQuotes(@Query("page") page:Int) :Quotes
}