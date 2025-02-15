package com.example.recipebook.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipebook.Graph
import com.example.recipebook.data.network.models.Meal
import com.example.recipebook.data.network.models.MealX
import com.example.recipebook.data.repository.Repository
import com.example.recipebook.utlis.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: Repository = Graph.repository
):ViewModel() {
    private val _detailState = MutableStateFlow(DetailState())
    val detailState = _detailState.asStateFlow()

    fun fetchMealById(id:String){
        viewModelScope.launch {
            repository.fetchMealById(id).collect{result ->
                when (result) {
                    is Response.Loading -> {
                        _detailState.update {
                            it.copy(
                               isdLoading = true, error = null
                            )
                        }
                    }

                    is Response.Success -> {
                        _detailState.update {
                            it.copy(
                                isdLoading = false, error = null,
                                meals = result.data.meals[0]
                            )
                        }
                    }

                    is Response.Error -> {
                        _detailState.update {
                            it.copy(
                                isdLoading = false, error = result.error?.message
                            )
                        }
                    }
                }
            }
        }
    }

}

data class DetailState(
    val meals:MealX? =  null,
    val isdLoading:Boolean = false,
    val error:String? = null
)