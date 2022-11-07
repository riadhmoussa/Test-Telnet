package com.moussa.componentdata.domain.use_case.town



import com.moussa.componentdata.domain.model.Town
import com.moussa.componentdata.domain.repository.TownRepository
import kotlinx.coroutines.flow.Flow

class GetTowns(
    private val repository: TownRepository
) {

    operator fun invoke(): Flow<List<Town>> {
        return repository.getTowns()
    }
}