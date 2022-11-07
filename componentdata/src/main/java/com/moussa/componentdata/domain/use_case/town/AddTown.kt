package com.moussa.componentdata.domain.use_case.town

import com.moussa.componentdata.domain.model.InvalidNoteException
import com.moussa.componentdata.domain.model.Town
import com.moussa.componentdata.domain.repository.TownRepository


class AddTown(
    private val repository: TownRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(town: Town) {
        if(town.name.isBlank()) {
            throw InvalidNoteException("The name cant be empty.")
        }
        repository.insertTown(town)
    }
}