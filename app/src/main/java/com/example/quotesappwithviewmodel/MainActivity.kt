package com.example.quotesappwithviewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel : MainViewModel

    private val quoteText : TextView get() = findViewById(R.id.quoteText)
    private val quoteAuthor: TextView get() = findViewById(R.id.quoteAuthor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)

        setQuote(mainViewModel.getQuote())

    }

    fun setQuote(quote: Quote){
        quoteText.text = quote.text
        quoteAuthor.text = quote.text
    }

    fun onPrevious(view: View) {
        if(mainViewModel.index <=0){
            Toast.makeText(this, "No more Quotes", Toast.LENGTH_SHORT).show()
        }else{
            setQuote(mainViewModel.previousQuote())
        }
    }
    fun onNext(view: View) {
        if(mainViewModel.index >=15){
            Toast.makeText(this, "No more Quotes", Toast.LENGTH_SHORT).show()
        }else{
            setQuote(mainViewModel.nextQuote())
        }

    }
    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
        startActivity(intent)
    }
}