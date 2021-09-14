package com.example.heybooks.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heybooks.model.BookItem
import com.example.heybooks.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class MainViewModel: ViewModel() {

    private val viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val books = viewState.asStateFlow()

    //Helps to format the json
    val format = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
    }

    // get all the data from the Book.json
    fun getAllBooks(context: Context) = viewModelScope.launch {
        try {

            // Read Json file

            val myJson = context.assets.open("books.json").bufferedReader().use{
                it.readText()
            }

            //format Json file

            val bookList = format.decodeFromString<List<BookItem>>(myJson)
            viewState.value = ViewState.Success(bookList)

        } catch (e: Exception){
            viewState.value = ViewState.Error(e)

        }
    }

}