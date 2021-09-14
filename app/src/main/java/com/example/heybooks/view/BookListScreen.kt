package com.example.heybooks.view

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.example.heybooks.R
import com.example.heybooks.components.ItemBookList
import com.example.heybooks.components.TextInputField
import com.example.heybooks.model.BookItem
import com.example.heybooks.navigation.MainActions
import com.example.heybooks.utils.ViewState
import com.example.heybooks.viewmodel.MainViewModel

@ExperimentalComposeUiApi
@Composable
fun BookListScreen(viewModel: MainViewModel, actions: MainActions) {

    val context = LocalContext.current
    viewModel.getAllBooks(context = context)

    when(val result = viewModel.books.value){
        ViewState.Empty -> Text("No results found")
        is ViewState.Error -> Text("Something went wrong... Error:${result.exception} ")
        ViewState.Loading -> Text("Loading...")
        is ViewState.Success -> {
            BookList(result.data, actions)
        }
    }
}

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@Composable
fun BookList(bookList: List<BookItem>, actions: MainActions) {

    val input = remember {
        mutableStateOf("")
    }

    val listState = rememberLazyListState()

    LazyColumn(state = listState, contentPadding = PaddingValues(top = 24.dp, bottom = 24.dp)){

        // title
        item{
            Text(
                text = "Explore thousands of \nbooks in go",
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Start,
                color = colors.primaryVariant,
                maxLines = 2,
                modifier = Modifier.padding(start = 16.dp, end = 24.dp, bottom = 24.dp))
        }

        // search
        item{
            TextInputField(label = stringResource(R.string.text_search), value = input.value, onValueChanged = {
                input.value = it
            })
        }

        // search results title
        item{
            Text(
                text = "Famous book",
                style = MaterialTheme.typography.subtitle1,
                color = colors.onPrimary,
                textAlign = TextAlign.Start)
        }

        // all books list view
        items(bookList){ book ->
            Log.d("books","books are ${book.title }" )
            ItemBookList(book.title, book.authors.toString(), book.thumbnailUrl, book.categories, onItemClick = {
                actions.gotoBookDetails.invoke(book.isbn)
            })
        }
    }
}
