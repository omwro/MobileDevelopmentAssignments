package nl.omererdem.madlevel4task2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

// Database table object model
@Entity(tableName = "gameTable")
class Game(
    @ColumnInfo(name = "answer_user")
    var answerUser: Int,

    @ColumnInfo(name = "answer_pc")
    var answerPc: Int,

    @ColumnInfo(name = "created_on")
    var createdOn: Date,

    @ColumnInfo(name = "result")
    var result: Result,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,
) {
    override fun toString(): String {
        return "Game(id=$id, answerUser=$answerUser, answerPc=$answerPc, createdOn=$createdOn, result=$result)"
    }
}