package com.moussa.componentdata.domain.use_case.get_town_weather


import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException
import com.moussa.componentdata.common.Constants
import com.moussa.componentdata.common.Resource
import com.moussa.componentdata.data.remote.dto.toWeatherDetail
import com.moussa.componentdata.domain.model.WeatherDetail
import com.moussa.componentdata.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


class GetTownWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(name: String): Flow<Resource<WeatherDetail>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getTownWeather(name, Constants.APP_ID).toWeatherDetail()
            emit(Resource.Success(data))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }catch (e:Exception){
            emit(Resource.Error("Error !!! "))
        }
    }
}