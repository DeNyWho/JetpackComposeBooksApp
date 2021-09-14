package com.example.heybooks.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.heybooks.view.BookDetailsScreen
import com.example.heybooks.view.BookListScreen
import com.example.heybooks.viewmodel.MainViewModel

object EndPoints {
    const val ID = "id"
}
@ExperimentalComposeUiApi
@Composable
fun NavGraph(){
    val navController = rememberNavController()
    val actions = remember(navController){ MainActions(navController)}
    val context = LocalContext.current

    NavHost(navController, startDestination = Screen.Booklist.route){
        //Home
        composable(Screen.Booklist.route){
            val viewModel: MainViewModel = viewModel(
                factory = HiltViewModelFactory(LocalContext.current, it)
            )
            viewModel.getAllBooks(context = context)
            BookListScreen(viewModel, actions)
        }


        //Search
        /*
        composable(Screen.Details.route){

        }*/

        //Task Details
        composable(
            "${Screen.Details.route}/{id}",
            arguments = listOf(navArgument(EndPoints.ID) { type = NavType.StringType})
        ){
            val viewModel = hiltViewModel<MainViewModel>(it)
            val isbnNo = it.arguments?.getString(EndPoints.ID)
                ?: throw IllegalStateException("'Book ISBN No' shouldn't be null")

            BookDetailsScreen(viewModel, actions)
        }
    }
}

class MainActions(navController: NavController){

    val upPress: () -> Unit = {
        navController.navigateUp()
    }

    // dribble.com
    val gotoBookDetails: (String) -> Unit = { isbnNo ->
        navController.navigate("${Screen.Details.route}/$isbnNo")
    }

    val gotoBookList: () -> Unit = {
        navController.navigate(Screen.Booklist.route)
    }
}





