package com.submision.mygames.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game (
    val gameId: Int,
    val name: String,
    val description: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val isFavorite: Boolean = false,
    val imageUrl: String
): Parcelable