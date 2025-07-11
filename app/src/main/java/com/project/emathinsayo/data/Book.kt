package com.project.emathinsayo.data

import android.graphics.Bitmap
import androidx.annotation.DrawableRes

data class Book(
    val name: String,
    val pages: List<Page>
)

data class Page(
    val name: String,
    val image: Bitmap?,
    val text: String
)

