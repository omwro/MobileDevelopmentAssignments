package nl.OmerErdem.madlvl5t2.room

import android.content.Context
import androidx.room.*
import nl.OmerErdem.madlvl5t2.model.Game
import nl.OmerErdem.madlvl5t2.model.GameDao
import nl.OmerErdem.madlvl5t2.utils.Converters

@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameRoomDatabase: RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {
        private const val DATABASE_NAME = "GAME_DB"

        @Volatile
        private var gameRoomDatabase: GameRoomDatabase? = null

        fun getDatabase(context: Context): GameRoomDatabase? {
            if (gameRoomDatabase == null) {
                synchronized(GameRoomDatabase::class.java) {
                    if (gameRoomDatabase == null) {
                        gameRoomDatabase = Room.databaseBuilder(
                            context.applicationContext,
                            GameRoomDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return gameRoomDatabase
        }
    }
}