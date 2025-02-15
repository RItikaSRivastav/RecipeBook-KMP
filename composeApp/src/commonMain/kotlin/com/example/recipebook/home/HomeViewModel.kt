package com.example.recipebook.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipebook.Graph
import com.example.recipebook.data.network.models.Meal
import com.example.recipebook.data.repository.Repository
import com.example.recipebook.utlis.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: Repository = Graph.repository
): ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.fetchMeals().collect{result ->
                when(result){
                    is Response.Loading -> {
                        _homeState.update {
                            it.copy(
                                isdLoading = true, error = null
                            )
                        }
                    }

                    is Response.Success -> {
                      _homeState.update {
                          it.copy(
                              isdLoading = false, error = null,
                              meals = result.data.meals
                          )
                      }
                    }
                    is Response.Error -> {
                        _homeState.update {
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

data class HomeState(
    val meals:List<Meal> = emptyList(),
    val isdLoading:Boolean = false,
    val error:String? = null
)