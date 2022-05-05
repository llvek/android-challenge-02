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

        binding.newQuoteButton.setOnClickListener{newQuoteClicked(repositoryModel.quoteData,quoteModel,repositoryModel)}
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
            if(!repositoryModel.dataLoaded){
                quoteModel.getRandomQuote(data)
            }
        }

        if(intent.getStringExtra("QUOTE")!=null){
            quoteModel.observableQuote.postValue(intent.getStringExtra("QUOTE").toString())
        }
        if(intent.getStringExtra("AUTHOR")!=null){
            quoteModel.observablePhilosopher.postValue(intent.getStringExtra("AUTHOR").toString())
        }

        if(quoteModel.observableQuote.value=="Loading..."){
            Log.d("Debug","Entered auto load")
            lifecycleScope.launch {
                newQuoteClicked(repositoryModel.quoteData, quoteModel, repositoryModel)
            }
        }

    }

    suspend fun loadQuoteFromApi(repositoryModel: RepositoryModel, quoteModel : QuoteModel){
        repositoryModel.loadQuotes()
        quoteModel.getRandomQuote(repositoryModel.quoteData)
    }

    fun newQuoteClicked(quoteData: MutableLiveData<Data>, quoteModel: QuoteModel, repositoryModel: RepositoryModel){
        if(repositoryModel.dataLoaded){
            quoteModel.getRandomQuote(quoteData)
            Log.v("Debug","Data loaded")
        } else {
            lifecycleScope.launch {
                repositoryModel.loadQuotes()
                quoteModel.getRandomQuote(quoteData)
            }
        }
    }
}