package com.moussa.componentdata.data.local.data_source


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moussa.componentdata.domain.model.Town
import kotlinx.coroutines.flow.Flow

@Dao
interface TownDao {

    @Query("SELECT * FROM town")
    fun getNotes(): Flow<List<Town>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Town)


}