package hu.tb.catfacts.data.repository

import android.util.Log
import hu.tb.catfacts.data.datasource.api.FactAPISource
import hu.tb.catfacts.data.datasource.api.entity.toFact
import hu.tb.catfacts.domain.model.Fact
import hu.tb.catfacts.domain.repository.FactRepository
import java.lang.Exception
import javax.inject.Inject

class FactRepositoryImpl @Inject constructor(
    private val api: FactAPISource
) : FactRepository {

    override suspend fun getFact(): Fact? =
        try {
            val response = api.getFact()
            response.body()?.toFact()
        } catch (e: Exception) {
            Log.e("API_CALL", "GetFact - ${e.message}")
            throw e
        }
}