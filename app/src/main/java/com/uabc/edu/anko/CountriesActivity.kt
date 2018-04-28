package com.uabc.edu.anko

import android.app.ListActivity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import com.uabc.edu.anko.util.ListItem
import com.uabc.edu.anko.util.ListItemAdapter
import com.uabc.edu.anko.util.TextListItem
import org.jetbrains.anko.*


class CountriesActivity : ListActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val items = listOf(
                "America" to listOf("Brazil", "Canada", "United States"),
                "Asia" to listOf("China", "India", "Japan"),
                "Europe" to listOf("France", "Germany", "Spain", "United Kingdom"))

        val listItems = items.flatMap {
            listOf(ContinentItem(it.first)) + it.second.map { CountryItem(it) }
        }

        listAdapter = CountriesAdapter(this, listItems)
    }
}

internal class CountriesAdapter(ctx: Context, items: List<ListItem>) : ListItemAdapter(ctx, items) {
    // Implementacion de todos los items de la l
    override val listItemClasses = listOf(ContinentItem::class.java, CountryItem::class.java)
}

// Implementacion por default
// Los complemento de vista previa de DSL requieren que los que heredan de AnkoComponent tengan un constructor vacío
internal class CountryItem(text: String = "") : TextListItem(text)

// Custom implementation
internal class ContinentItem(text: String = "") : TextListItem(text) {
    override fun createView(ui: AnkoContext<ListItemAdapter>) = createTextView(ui) {
        gravity = Gravity.CENTER_VERTICAL
        horizontalPadding = ui.dip(20)
        verticalPadding = ui.dip(10)
        backgroundColor = 0x99CCCCCC.toInt()
        textSize = 17f
        textColor = Color.BLUE
    }
}