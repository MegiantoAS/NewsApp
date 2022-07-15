package com.megi.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsModel (
    val title: String,
    val name: String,
    val description: String,
    val image: String,
    val publishAt: String,
    val favoriteNews: Boolean
): Parcelable