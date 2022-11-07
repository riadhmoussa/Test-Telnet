package com.moussa.telnettest.di

import android.app.Application
import androidx.room.Room
import com.moussa.componentdata.data.local.data_source.TownDatabase
import com.moussa.componentdata.data.local.repository.TownRepositoryImpl
import com.moussa.componentdata.data.remote.WeatherPaprikaApi
import com.moussa.componentdata.data.remote.repository.WeatherRepositoryImpl
import com.moussa.componentdata.domain.repository.TownRepository
import com.moussa.componentdata.domain.repository.WeatherRepository
import com.moussa.componentdata.domain.use_case.town.AddTown
import com.moussa.componentdata.domain.use_case.town.GetTowns
import com.moussa.componentdata.domain.use_case.town.TownUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): WeatherPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(com.moussa.componentdata.common.Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(api: WeatherPaprikaApi): WeatherRepository {
        return WeatherRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideTownDatabase(app: Application): TownDatabase {
        return Room.databaseBuilder(
            app,
            TownDatabase::class.java,
            TownDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTownRepository(db: TownDatabase): TownRepository {
        return TownRepositoryImpl(db.townDao)
    }

    @Provides
    @Singleton
    fun provideTownUseCases(repository: TownRepository): TownUseCases {
        return TownUseCases(
            getTowns = GetTowns(repository),
            addTown = AddTown(repository),

        )
    }
}