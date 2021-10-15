package nl.omererdem.madlevel3task2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// Model for the portals consisted over a title and url
@Parcelize
data class Portal (
    var title : String,
    var url : String
) : Parcelable