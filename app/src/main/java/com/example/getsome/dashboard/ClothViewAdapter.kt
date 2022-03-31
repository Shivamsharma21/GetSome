package com.example.getsome.dashboard

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.getsome.R
import com.example.getsome.model.ApiData
import com.example.getsome.model.ApiDataItem



class ClothViewAdapter(private val list: ArrayList<ApiData>): RecyclerView.Adapter<ClothViewAdapter.ViewHolder>() {
     var arrayList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.clothsview_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         // setting up the data in the holder
        var model = arrayList.get(position) as ApiDataItem // casting the arraylist to
                                                           // " ApiDataItem so that
                                                          // fetched the data in the model
        holder.textView2.setText(model.brand)
        Log.d("DATA _>",model.brand)

    }

    override fun getItemCount(): Int {
        Log.d("ArrayList Inside ->",arrayList.toString())
        return arrayList.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var textView2 = itemView.findViewById<TextView>(R.id.text2)
    }

}