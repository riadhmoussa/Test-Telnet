package com.moussa.componentdata.domain.repository

import com.moussa.componentdata.domain.model.Town
import kotlinx.coroutines.flow.Flow

interface TownRepository {

    fun getTowns(): Flow<List<Town>>

    suspend fun insertTown(town: Town)

}