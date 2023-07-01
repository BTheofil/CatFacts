package hu.tb.catfacts.domain.repository

import hu.tb.catfacts.domain.model.Fact

interface FactRepository {

    suspend fun getFact(): Fact?
}