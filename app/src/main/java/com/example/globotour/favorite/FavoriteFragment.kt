package com.example.globotour.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.globotour.R
import com.example.globotour.city.City
import com.example.globotour.city.VacationSpots
import java.util.*
import kotlin.collections.ArrayList


class FavoriteFragment : Fragment() {
    private lateinit var favoriteCityList : ArrayList<City>
    private lateinit var FavoriteAdapter : FavoriteAdapter
    private lateinit var recyclerView : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        setupRecyclerView(view)

        return view
    }

    private fun setupRecyclerView(view: View?) {
        val context = requireContext()

        val FavoriteAdapter = FavoriteAdapter(context,
            VacationSpots.favoriteCityList as ArrayList<City>
        )

        val recyclerView = view?.findViewById<RecyclerView>(R.id.favorite_recycler_view)
        recyclerView?.adapter = FavoriteAdapter
        recyclerView?.setHasFixedSize(true)


        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView?.layoutManager = layoutManager

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private val itemTouchHelper = ItemTouchHelper(object :ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN
        ,ItemTouchHelper.RIGHT){
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            val fromPosition = viewHolder.adapterPosition
            val toPosition = target.adapterPosition

            Collections.swap(favoriteCityList,fromPosition,toPosition)
            recyclerView.adapter?.notifyItemMoved(fromPosition,toPosition)

            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val deletedCity : City = favoriteCityList.get(position)
            deleteItem(position)

        }

    })

    private fun deleteItem(position: Int) {
        favoriteCityList.removeAt(position)
        FavoriteAdapter.notifyItemRemoved(position)
        FavoriteAdapter.notifyItemRangeChanged(position,favoriteCityList.size)
    }

}
