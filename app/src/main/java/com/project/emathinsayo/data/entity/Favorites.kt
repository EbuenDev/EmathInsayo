package com.project.emathinsayo.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorites(
    @PrimaryKey(autoGenerate = true) val bookId: Long? = null,
    val title: String? = null
)
