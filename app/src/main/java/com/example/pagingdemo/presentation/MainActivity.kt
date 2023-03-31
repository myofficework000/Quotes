package com.example.pagingdemo.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagingdemo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: QuoteViewModel by viewModels()
    private lateinit var quotesAdapter: QuotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        observeData()
    }

    private fun setUpRecyclerView() {
        quotesAdapter = QuotesAdapter()
        binding.quoteRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = quotesAdapter
        }
    }

    private fun observeData() {
        viewModel.list.observe(this) {
            quotesAdapter.submitData(lifecycle, it)
        }
    }
}