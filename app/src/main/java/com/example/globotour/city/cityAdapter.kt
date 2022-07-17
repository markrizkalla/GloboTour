package com.example.globotour.city

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.globotour.R

class cityAdapter(val context : Context, var cityList : ArrayList<City>) : RecyclerView.Adapter<cityAdapter.CityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_city,parent,false)
        return CityViewHolder(itemView)
    }

    override fun onBindViewHolder(cityViewHolder: CityViewHolder, position: Int) {
        val city = cityList[position]
        cityViewHolder.setData(city,position)
        cityViewHolder.setListener()
    }

    override fun getItemCount(): Int {
        return cityList.size
    }


  inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
      View.OnClickListener {
      private var currentPosition : Int = -1
      private var currentCity : City? = null

      private val textCityName = itemView.findViewById<TextView>(R.id.txv_city_name)
      private val imvCityImage = itemView.findViewById<ImageView>(R.id.imv_city)
      private val imvDelete = itemView.findViewById<ImageView>(R.id.imv_delete)
      private val imvFavorite = itemView.findViewById<ImageView>(R.id.imv_favorite)

      private val icFavoriteFilledImage = ResourcesCompat.getDrawable(context.resources,R.drawable.ic_favorite_filled,null)
      private val icFavoriteBorderedImage = ResourcesCompat.getDrawable(context.resources,R.drawable.ic_favorite_bordered,null)

      fun setData(city: City, position: Int) {

          textCityName.text = city.name
          imvCityImage.setImageResource(city.imageId)

          if (city.isFavorite){
              imvFavorite.setImageDrawable(icFavoriteFilledImage)
          }else{
              imvFavorite.setImageDrawable(icFavoriteBorderedImage)
          }


          this.currentCity = city
          this.currentPosition = position
      }

      fun setListener() {
          imvDelete.setOnClickListener(this@CityViewHolder)
          imvFavorite.setOnClickListener(this@CityViewHolder)
      }

      override fun onClick(v: View?) {
          when(v!!.id){
              R.id.imv_delete -> deleteItem()
              R.id.imv_favorite -> addToFavorite()
          }
      }

      private fun addToFavorite() {
          currentCity?.isFavorite = !(currentCity?.isFavorite!!)

          if (currentCity?.isFavorite!!){
              imvFavorite.setImageDrawable(icFavoriteFilledImage)
              VacationSpots.favoriteCityList.add(currentCity!!)
          }else{
              imvFavorite.setImageDrawable(icFavoriteBorderedImage)
              VacationSpots.favoriteCityList.remove(currentCity!!)

          }
      }

      private fun deleteItem() {
          cityList.removeAt(currentPosition)
          notifyItemRemoved(currentPosition)
          notifyItemRangeChanged(currentPosition,cityList.size)
          VacationSpots.favoriteCityList.remove(currentCity!!)

      }

  }

}
