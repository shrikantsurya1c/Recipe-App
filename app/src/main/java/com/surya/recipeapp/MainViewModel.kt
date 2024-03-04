package com.surya.recipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _categorystate = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categorystate

    init {
        fetchCategories()
    }


    private fun fetchCategories(){
        viewModelScope.launch {
            try{
                val response = recipeService.getCategories()
                _categorystate.value = _categorystate.value.copy(
                    list = response.categories,
                    isLoading = false,
                    error = null
                )
            }catch(e: Exception){
                _categorystate.value = _categorystate.value.copy(
                    isLoading = false,
                    error = "Error while loading category ${e.message}"
                )
            }
        }
    }


    data class RecipeState(
        val isLoading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
}