package com.example.globotour.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.globotour.R
import com.example.globotour.city.City


class FavoriteAdapter(val context: Context, var favCityList : ArrayList<City>) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
       val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_favorite,parent,false)
        return FavoriteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val city  = favCityList[position]
        holder.setData(city,position)
    }

    override fun getItemCount(): Int {
       return favCityList.size
    }

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var currentPosition : Int = -1
        private var currentCity : City? = null

        private val textCityName = itemView.findViewById<TextView>(R.id.txv_city_name)
        private val imvCityImage = itemView.findViewById<ImageView>(R.id.imv_city)
        fun setData(city: City, position: Int) {
            textCityName.text = city.name
            imvCityImage.setImageResource(city.imageId)

            this.currentPosition = position
            this.currentCity = city
        }


    }

}

