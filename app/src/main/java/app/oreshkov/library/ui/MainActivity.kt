package app.oreshkov.library.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import app.oreshkov.library.R
import app.oreshkov.library.model.Book
import app.oreshkov.library.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBooksEvent)
    }

    private fun subscribeObservers(){
        viewModel.dataState.observe(this, Observer { dataState ->
            when(dataState){
                is DataState.Success<List<Book>> -> {
                    displayProgressBar(false)
                    appendBookNames(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?){
        if(message != null) text.text = message else text.text = "Unknown error."
    }

    private fun appendBookNames(books: List<Book>){
        val sb = StringBuilder()
        for(book in books){
            sb.append(book.name + "\n")
        }
        text.text = sb.toString()
    }

    private fun displayProgressBar(isDisplayed: Boolean){
        progress_bar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

}