package com.nikitakonshin.testdeleagateadapter.presentation

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.nikitakonshin.testdeleagateadapter.R
import com.nikitakonshin.testdeleagateadapter.data.Championship
import com.nikitakonshin.testdeleagateadapter.data.DisplayableItem
import com.nikitakonshin.testdeleagateadapter.data.KindOfSport
import com.nikitakonshin.testdeleagateadapter.databinding.FragmentRVBinding
import com.nikitakonshin.testdeleagateadapter.presentation.delegates.championshipAdapterDelegate
import com.nikitakonshin.testdeleagateadapter.presentation.delegates.kindOfSportsAdapterDelegate

class RVFragment : Fragment(R.layout.fragment_r_v) {

    private val binding: FragmentRVBinding by viewBinding()
    private val adapter = ListDelegationAdapter(
        kindOfSportsAdapterDelegate { data, pos ->
            listener(data, pos + 1)
        },
        championshipAdapterDelegate()
    )
    private val kindsOfSports = mutableListOf<DisplayableItem>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initItems()
        initAdapter()
    }

    private fun initItems() {
        for (i in 1..20) {
            kindsOfSports.add(KindOfSport("Sport $i"))
        }
    }

    private fun initAdapter() {
        with(binding) {
            rvTest.adapter = adapter
            rvTest.addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    super.getItemOffsets(outRect, view, parent, state)

                    outRect.top = 10
                    outRect.bottom = 10
                    outRect.left = 10
                    outRect.right = 10
                }
            })
            adapter.apply {
                items = kindsOfSports
            }
        }
    }

    private fun listener(kindOfSport: KindOfSport, pos: Int) {
        if (!kindOfSport.isChecked) {
            if (pos != RecyclerView.NO_POSITION) {
                addChampionship(kindOfSport, pos)
            }
        } else {
            removeItem(kindOfSport, pos)
        }
    }

    private fun addChampionship(kindOfSport: KindOfSport, pos: Int) {
        for (i in 1..4) {
            kindsOfSports.add(pos, Championship("Name: $i"))
            adapter.apply {
                items = kindsOfSports
                notifyItemInserted(pos)
            }
        }
        kindOfSport.isChecked = true
    }

    private fun removeItem(kindOfSport: KindOfSport, pos: Int) {
        while (kindsOfSports[pos] is Championship) {
            kindOfSport.isChecked = false
            kindsOfSports.removeAt(pos)
            adapter.apply {
                items = kindsOfSports
                notifyItemRemoved(pos)
            }
            if (pos == kindsOfSports.size) return
        }
    }
}