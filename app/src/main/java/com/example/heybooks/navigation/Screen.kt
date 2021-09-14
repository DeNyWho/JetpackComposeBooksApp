package com.example.heybooks.navigation

import androidx.annotation.StringRes
import com.example.heybooks.R

sealed class Screen(val route: String, @StringRes val resourceId: Int ){
    object Booklist: Screen("Book_list", R.string.text_bookList)
    object Details: Screen("Book_details", R.string.text_bookDetails)
}

// Screen.route.Booklist -> screen.route.bookdetails.number
