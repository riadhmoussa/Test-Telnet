package com.moussa.componentdata.data.local.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moussa.componentdata.domain.model.Town


@Database(
    entities = [Town::class],
    version = 1
)
abstract class TownDatabase: RoomDatabase() {

    abstract val townDao: TownDao

    companion object {
        const val DATABASE_NAME = "towns_db"
    }
}