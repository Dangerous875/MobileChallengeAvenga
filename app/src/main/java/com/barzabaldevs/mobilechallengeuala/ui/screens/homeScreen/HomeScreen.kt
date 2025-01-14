package com.barzabaldevs.mobilechallengeuala.ui.screens.homeScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.barzabaldevs.mobilechallengeuala.ui.screens.homeScreen.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = hiltViewModel()){
    val homeState by viewModel.homeState.collectAsState()
    if (homeState.isLoading){
        CircularProgressIndicator()
    }else{
        LazyColumn(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            items(homeState.countries){ country ->
                Text("${country.name},${country.country}")
        }
    }

}
}