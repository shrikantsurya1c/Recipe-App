package com.surya.recipeapp


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter



@Composable
fun RecipeScreen(
    viewstate: MainViewModel.RecipeState,
    navigateToDetail: (Category) -> Unit,
                 ){
    val recipeViewModel: MainViewModel = viewModel()

    Box(modifier = Modifier.fillMaxSize()){
        when{
            viewstate.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            viewstate.error != null -> {
                Text(text = " ERROR OCCURED")
            }

            else -> {
                // Display Categories
                CategoryScreen(categories = viewstate.list,
                    navigateToDetail)
            }
        }
    }
}


@Composable
fun CategoryScreen(categories: List<Category>,
                   navigateToDetail: (Category) -> Unit){
    LazyVerticalGrid(GridCells.Fixed(count = 2), modifier = Modifier.fillMaxSize()){
        items(categories){
            category ->
            CategoryItem(category = category, navigateToDetail)
        }
    }
}

// How each items looks like
@Composable
fun CategoryItem(category: Category,
                 navigateToDetail: (Category) -> Unit){
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize().clickable { navigateToDetail(category) },

        horizontalAlignment = Alignment.CenterHorizontally){

        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )



        Text(
            text = category.strCategory,
            color = Color.Black,
            style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )

    }
}
