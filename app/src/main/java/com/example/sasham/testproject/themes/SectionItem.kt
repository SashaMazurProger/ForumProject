package com.example.sasham.testproject.themes

import com.example.sasham.testproject.R
import com.example.sasham.testproject.model.Section
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.section_item.view.*

class SectionItem(val section: Section) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.sectionTitle.text = section.title
        viewHolder.itemView.sectionDescription.text = section.description
    }

    override fun getLayout(): Int {
        return R.layout.section_item
    }
}

