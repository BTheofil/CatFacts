package hu.tb.catfacts.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.tb.catfacts.data.datasource.api.FactAPISource
import hu.tb.catfacts.data.repository.FactRepositoryImpl
import hu.tb.catfacts.domain.repository.FactRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitApi(): FactAPISource =
        Retrofit.Builder()
            .baseUrl("https://catfact.ninja")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FactAPISource::class.java)

    @Provides
    @Singleton
    fun provideFactRepository(api: FactAPISource): FactRepository =
        FactRepositoryImpl(api = api)
}