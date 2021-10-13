package com.nikitakonshin.testdeleagateadapter.presentation.delegates

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.nikitakonshin.testdeleagateadapter.data.Championship
import com.nikitakonshin.testdeleagateadapter.data.DisplayableItem
import com.nikitakonshin.testdeleagateadapter.data.KindOfSport
import com.nikitakonshin.testdeleagateadapter.databinding.ItemChempionshipBinding
import com.nikitakonshin.testdeleagateadapter.databinding.ItemKindOfSportBinding

fun kindOfSportsAdapterDelegate(itemClickListener: (KindOfSport, Int) -> Unit) =
    adapterDelegateViewBinding<KindOfSport, DisplayableItem, ItemKindOfSportBinding>({ layoutInflater, root ->
        ItemKindOfSportBinding.inflate(
            layoutInflater,
            root,
            false
        )
    }) {

        bind {
            with(binding) {
                tvName.setOnClickListener { itemClickListener(item, layoutPosition) }
                tvName.text = item.name
            }
        }
    }

fun championshipAdapterDelegate() =
    adapterDelegateViewBinding<Championship, DisplayableItem, ItemChempionshipBinding>({ layoutInflater, parent ->
        ItemChempionshipBinding.inflate(layoutInflater, parent, false)
    }) {
        bind {
            with(binding) {
                tvName.text = item.name
            }
        }
    }