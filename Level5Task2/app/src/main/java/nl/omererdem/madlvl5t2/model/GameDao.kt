package nl.OmerErdem.madlvl5t2.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GameDao {
    @Query("SELECT * FROM gameTable")
    fun getAllGames(): LiveData<List<Game>>

    @Insert
    fun insertGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("DELETE FROM gameTable")
    suspend fun deleteAllGames()
}