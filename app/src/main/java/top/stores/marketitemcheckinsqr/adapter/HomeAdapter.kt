package top.stores.marketitemcheckinsqr.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import top.stores.marketitemcheckinsqr.R
import top.stores.marketitemcheckinsqr.ItemData

class HomeAdapter(private val context: Context?) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private var arrayList: ArrayList<ItemData>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, index: Int): ViewHolder {
        val rootView = LayoutInflater.from(viewGroup.context).inflate(R.layout.activity_card, viewGroup, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return arrayList?.size!!
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemName.text = arrayList?.get(position)?.itemName
        viewHolder.itemPrice.text = arrayList?.get(position)?.itemPrice.toString()

        viewHolder.btnRemoveItem.setOnClickListener {

        }

    }


    fun setProjectList(arrayList: ArrayList<ItemData>?) {

        this.arrayList = arrayList
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemName: TextView = itemView.findViewById(R.id.textViewItemName) as TextView
        var itemPrice: TextView = itemView.findViewById(R.id.textViewItemPrice) as TextView

        var btnRemoveItem: Button = itemView.findViewById(R.id.buttonRemoveItem) as Button

    }



}