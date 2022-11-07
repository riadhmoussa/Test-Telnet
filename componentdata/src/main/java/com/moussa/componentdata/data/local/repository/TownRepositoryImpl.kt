package com.moussa.componentdata.data.local.repository


import com.moussa.componentdata.data.local.data_source.TownDao
import com.moussa.componentdata.domain.model.Town
import com.moussa.componentdata.domain.repository.TownRepository
import kotlinx.coroutines.flow.Flow


class TownRepositoryImpl(
    private val dao: TownDao
) : TownRepository {

    override fun getTowns(): Flow<List<Town>> {
        return dao.getNotes()
    }

    override suspend fun insertTown(note: Town) {
        dao.insertNote(note)
    }


}