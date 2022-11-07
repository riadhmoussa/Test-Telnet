package com.moussa.componentdata.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Town(
    val name: String,
    @PrimaryKey val id: Int? = null
)

class InvalidNoteException(message: String): Exception(message)