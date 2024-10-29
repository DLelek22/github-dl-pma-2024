package com.example.myapp008bfragmentsexample1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

class ListFragment : Fragment() {

    private lateinit var listView: ListView
    private val books = listOf(
        "Margherita" to "Ingredients: \n -Tomatoe sauce \n -Cheese \n -Tomatoes \n -Basil",
        "Mexicana" to "Ingredients: \n -Tomatoe sauce \n -Cheese \n -Red onion \n -Meat \n -Pepper",
        "Quatro Formagi" to "Ingredients: \n -Cream sauce \n -Niva \n -Eidam \n -Parmesan \n -Cheddar"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_1, container, false)
        listView = view.findViewById(R.id.listPizza)

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            books.map { it.first }
        )
        listView.adapter = adapter

        // Při kliknutí na položku zavoláme metodu aktivity
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedBook = books[position]
            (activity as? MainActivity)?.onBookSelected(selectedBook.first, selectedBook.second)
        }
        return view
    }
}