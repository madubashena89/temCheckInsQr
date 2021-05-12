package top.stores.marketitemcheckinsqr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import top.stores.marketitemcheckinsqr.adapter.HomeAdapter
import top.stores.marketitemcheckinsqr.databinding.ActivityItemListBinding
import java.io.Serializable


class ItemListActivity (): AppCompatActivity(),Serializable {

    private lateinit var homeInvesterAdapter: HomeAdapter
    private lateinit var itemRecyclerView: RecyclerView
    private lateinit var binding: ActivityItemListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  binding = FragmentItemListBinding.inflate(inflater, container, false)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_item_list)
        itemRecyclerView = binding.listRecyclerView


        val intent = intent
        val args = intent.getBundleExtra("BUNDLE")
        val itemlist =
            args!!.getSerializable("ARRAYLIST") as ArrayList<ItemData>?
        setUpAdapterWithList(itemRecyclerView,itemlist)

    }

    companion object {
        fun newInstance(): ItemListActivity = ItemListActivity()
        const val ACTIVITY_NOT_FOUND = "Activity Not Found"

    }


    fun setUpAdapterWithList(recyclerView: RecyclerView, arrayList: ArrayList<ItemData>?){
//        Thread.sleep(1000)
        homeInvesterAdapter = HomeAdapter(this)
        if (arrayList != null) {
            homeInvesterAdapter.setProjectList(arrayList)
        }
        val layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = homeInvesterAdapter
    }
}