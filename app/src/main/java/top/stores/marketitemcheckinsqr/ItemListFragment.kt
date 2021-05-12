package top.stores.marketitemcheckinsqr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import top.stores.marketitemcheckinsqr.adapter.HomeAdapter
import top.stores.marketitemcheckinsqr.databinding.FragmentItemListBinding

class ItemListFragment : Fragment() {

    private lateinit var homeInvesterAdapter: HomeAdapter
    private lateinit var itemRecyclerView: RecyclerView
    private lateinit var binding: FragmentItemListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentItemListBinding.inflate(inflater, container, false)

        itemRecyclerView = binding.listRecyclerView

        return inflater.inflate(R.layout.fragment_item_list, container, false)
    }

    companion object {
        fun newInstance(): ItemListFragment = ItemListFragment()
        const val ACTIVITY_NOT_FOUND = "Activity Not Found"

    }


    fun setUpAdapterWithList(recyclerView: RecyclerView, arrayList: ArrayList<itemData>){
//        Thread.sleep(1000)
        homeInvesterAdapter = HomeAdapter(activity)
        if (arrayList != null) {
            homeInvesterAdapter.setProjectList(arrayList)
        }
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.stackFromEnd = true
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = homeInvesterAdapter
    }
}