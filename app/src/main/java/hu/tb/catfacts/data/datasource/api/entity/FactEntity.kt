package hu.tb.catfacts.data.datasource.api.entity

import hu.tb.catfacts.domain.model.Fact

data class FactEntity(
    val fact: String,
    val length: Int
)

fun FactEntity.toFact(): Fact =
    Fact(
        fact = fact
    )
