package com.goggxi.movies.ui.screen.home

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goggxi.movies.datasource.CoroutineDispatcherProvider
import com.goggxi.movies.datasource.Repository
import com.goggxi.movies.models.Model
import com.goggxi.movies.ui.utils.PlatformsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
@SuppressLint("StaticFieldLeak")
class HomeViewModel @Inject constructor(
    private val repository: Repository,
//    @ApplicationContext private val applicationContext : Context,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
) : ViewModel() {
    private val _popularUiState =
        MutableStateFlow<PlatformsUiState<List<Model>>>(PlatformsUiState.Empty())
    val popularUiState: StateFlow<PlatformsUiState<List<Model>>> = _popularUiState

    private val _nowPlaying =
        MutableStateFlow<PlatformsUiState<List<Model>>>(PlatformsUiState.Empty())
    val nowPlaying: StateFlow<PlatformsUiState<List<Model>>> = _nowPlaying

    private val _tvPopular =
        MutableStateFlow<PlatformsUiState<List<Model>>>(PlatformsUiState.Empty())
    val tvPopular: StateFlow<PlatformsUiState<List<Model>>> = _tvPopular

    private val _tvAiringToday =
        MutableStateFlow<PlatformsUiState<List<Model>>>(PlatformsUiState.Empty())
    val tvAiringToday: StateFlow<PlatformsUiState<List<Model>>> = _tvAiringToday

    init {
        fetchMovie()
        fetchNowPlaying()
        fetchTvPopular()
        fetchTvAiringToday()
    }

    private fun fetchMovie() {
        _popularUiState.value = PlatformsUiState.Loading()
        viewModelScope.launch(coroutineDispatcherProvider.io()) {
            try {
                val response = repository.getPopularMovies(page = 1)
                _popularUiState.value = PlatformsUiState.Success(response.results)
            } catch (e: HttpException) {
                if (e.code() == 401) {
                    _popularUiState.value =
                        PlatformsUiState.Error("Invalid API key: You must be granted a valid key.")
                } else if (e.code() == 404) {
                    _popularUiState.value =
                        PlatformsUiState.Error("The resource you requested could not be found.")
                } else {
                    _popularUiState.value =
                        PlatformsUiState.Error("Something went wrong: ${e.localizedMessage.orEmpty()}")
                }
            } catch (e: Exception) {
                _popularUiState.value =
                    PlatformsUiState.Error("Something went wrong: ${e.localizedMessage.orEmpty()}")
            }
        }
    }

    private fun fetchNowPlaying() {
        _nowPlaying.value = PlatformsUiState.Loading()
        viewModelScope.launch(coroutineDispatcherProvider.io()) {
            try {
                val response = repository.getNowPlayingMovies(page = 1)
                _nowPlaying.value = PlatformsUiState.Success(response.results)
            } catch (e: HttpException) {
                if (e.code() == 401) {
                    _nowPlaying.value =
                        PlatformsUiState.Error("Invalid API key: You must be granted a valid key.")
                } else if (e.code() == 404) {
                    _nowPlaying.value =
                        PlatformsUiState.Error("The resource you requested could not be found.")
                } else {
                    _nowPlaying.value =
                        PlatformsUiState.Error("Something went wrong: ${e.localizedMessage.orEmpty()}")
                }
            } catch (e: Exception) {
                _nowPlaying.value =
                    PlatformsUiState.Error("Something went wrong: ${e.localizedMessage.orEmpty()}")
            }
        }
    }

    private fun fetchTvPopular() {
        _tvPopular.value = PlatformsUiState.Loading()
        viewModelScope.launch(coroutineDispatcherProvider.io()) {
            try {
                val response = repository.getTVPopular(page = 1)
                _tvPopular.value = PlatformsUiState.Success(response.results)
            } catch (e: HttpException) {
                if (e.code() == 401) {
                    _tvPopular.value =
                        PlatformsUiState.Error("Invalid API key: You must be granted a valid key.")
                } else if (e.code() == 404) {
                    _tvPopular.value =
                        PlatformsUiState.Error("The resource you requested could not be found.")
                } else {
                    _tvPopular.value =
                        PlatformsUiState.Error("Something went wrong: ${e.localizedMessage.orEmpty()}")
                }
            } catch (e: Exception) {
                _tvPopular.value =
                    PlatformsUiState.Error("Something went wrong: ${e.localizedMessage.orEmpty()}")
            }
        }
    }

    private fun fetchTvAiringToday() {
        _tvAiringToday.value = PlatformsUiState.Loading()
        viewModelScope.launch(coroutineDispatcherProvider.io()) {
            try {
                val response = repository.getTVOnAir(page = 1)
                _tvAiringToday.value = PlatformsUiState.Success(response.results)
            } catch (e: HttpException) {
                if (e.code() == 401) {
                    _tvAiringToday.value =
                        PlatformsUiState.Error("Invalid API key: You must be granted a valid key.")
                } else if (e.code() == 404) {
                    _tvAiringToday.value =
                        PlatformsUiState.Error("The resource you requested could not be found.")
                } else {
                    _tvAiringToday.value =
                        PlatformsUiState.Error("Something went wrong: ${e.localizedMessage.orEmpty()}")
                }
            } catch (e: Exception) {
                _tvAiringToday.value =
                    PlatformsUiState.Error("Something went wrong: ${e.localizedMessage.orEmpty()}")
            }
        }
    }
}