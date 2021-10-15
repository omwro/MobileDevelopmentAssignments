package nl.OmerErdem.madlvl5t2.utils

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Calendar? = value?.let { _ ->
        GregorianCalendar().also { calendar ->
            calendar.timeInMillis = value
        }
    }

    @TypeConverter
    fun toTimestamp(calendar: Calendar?): Long? {
        return calendar?.timeInMillis
    }
}