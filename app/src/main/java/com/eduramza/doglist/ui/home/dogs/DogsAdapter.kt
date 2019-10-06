package com.eduramza.doglist.ui.home.dogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.eduramza.doglist.R
import kotlinx.android.synthetic.main.item_list_dogs.view.*

class DogsAdapter(private val listDogs: MutableList<String>,
                  private val context: Context,
                  private val listener: DogsAdapterListener): BaseAdapter(){

    override fun getCount() = listDogs.size
    override fun getItem(position: Int): Any{
        return listDogs[position]
    }
    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convrtView: View?, viewGroup: ViewGroup?): View {

        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listView = inflator.inflate(R.layout.item_list_dogs, null)

        onBindView(listView, position)

        return listView
    }

    fun onBindView(holder: View, position: Int) {
        val url = listDogs.get(position)

        Glide.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.img_dog)

        holder.img_dog.setOnClickListener { listener.doPreview(url) }
    }


}

interface DogsAdapterListener{
    fun doPreview(url: String)
}