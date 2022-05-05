package com.example.android_challenge_02

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.android_challenge_02.databinding.ActivityMainBinding
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch

//Single Quote Activity Class
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Starts ViewModel
        val quoteModel: QuoteModel by viewModels()
        val repositoryModel: RepositoryModel by viewModels()

        binding.newQuoteButton.setOnClickListener{newQuoteClicked(quoteModel,repositoryModel)}
        binding.quoteListButton.setOnClickListener{
            val intent = Intent(this, QuoteList::class.java)
            startActivity(intent)
        }

        quoteModel.observableQuote.observe(this){ quote ->
            Log.d("Captura", "Frase: ${quote}")
            binding.randomQuote.text = quote
        }

        quoteModel.observablePhilosopher.observe(this){ philosopher ->
            Log.d("Captura", "Filosofo: ${philosopher}")
            binding.philosopher.text = philosopher
        }

        repositoryModel.quoteData.observe(this) { data ->
            Log.d("Captura", "Meu Dado ${data}")
            if(quoteModel.observableQuote.value=="Loading..."||quoteModel.observableQuote.value=="Load Failed"){
                quoteModel.getRandomQuote(data)
            }
        }

        if(intent.getStringExtra("QUOTE")!=null&&!quoteModel.alreadyEnteredScreen){
            quoteModel.observableQuote.postValue(intent.getStringExtra("QUOTE").toString())
            Log.d("Intent Quote",intent.getStringExtra("QUOTE").toString())
        }
        if(intent.getStringExtra("AUTHOR")!=null&&!quoteModel.alreadyEnteredScreen){
            quoteModel.observablePhilosopher.postValue(intent.getStringExtra("AUTHOR").toString())
        }

        if(quoteModel.observableQuote.value=="Loading..."){
            Log.d("Debug","Entered auto load")
            lifecycleScope.launch {
                newQuoteClicked(quoteModel, repositoryModel)
            }
        }

        quoteModel.alreadyEnteredScreen = true

    }

    suspend fun loadQuoteFromApi(repositoryModel: RepositoryModel, quoteModel : QuoteModel){
        repositoryModel.loadQuotes()
        quoteModel.getRandomQuote(repositoryModel.quoteData)
    }

    fun newQuoteClicked(quoteModel: QuoteModel, repositoryModel: RepositoryModel){
        var quoteData = repositoryModel.quoteData
        if(repositoryModel.dataLoaded){
            quoteModel.getRandomQuote(quoteData)
            Log.v("Debug","Data loaded")
        } else {
            lifecycleScope.launch {
                repositoryModel.loadQuotes()
            }
        }
    }
}