package com.example.recipebook.data.repository

import com.example.recipebook.data.network.models.MealItem
import com.example.recipebook.data.network.models.Meals
import com.example.recipebook.utlis.K
import com.example.recipebook.utlis.Response
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.URLProtocol
import io.ktor.http.path
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class Repository(
    private val client: HttpClient
) {
   fun fetchMeals(location:String = "British"): Flow<Response<Meals>> = flow {
       emit(Response.Loading())
       val mealDto = client.get {
          url {
              protocol = URLProtocol.HTTPS
              host = K.Host
              path(K.Path)
              parameters.append("a", location)
          }
       }.body<Meals>()
       emit(Response.Success(mealDto))
   }.catch { error ->
       emit(Response.Error(error))
   }
    fun fetchMealById(id: String): Flow<Response<MealItem>> = flow {
        emit(Response.Loading())
        val mealDto = client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = K.Host
                path(K.LookUpPath)
                parameters.append("i", id)
            }
        }.body<MealItem>()
        emit(Response.Success(mealDto))
    }.catch { error ->
        emit(Response.Error(error))
    }
}