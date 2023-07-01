package hu.tb.catfacts.data.datasource.api

import hu.tb.catfacts.data.datasource.api.entity.FactEntity
import retrofit2.Response
import retrofit2.http.GET

interface FactAPISource{

    @GET("/fact")
    suspend fun getFact(): Response<FactEntity>

}