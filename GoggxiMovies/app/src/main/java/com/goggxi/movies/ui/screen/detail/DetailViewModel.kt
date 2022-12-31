package com.goggxi.movies.ui.screen.detail

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
class DetailViewModel @Inject constructor(
    private val repository: Repository,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
) : ViewModel() {
    private val _movieUiState = MutableStateFlow<PlatformsUiState<Model>>(PlatformsUiState.Empty())
    val movieUiState: StateFlow<PlatformsUiState<Model>> = _movieUiState

    fun fetchMovie(id: Long) {
        _movieUiState.value = PlatformsUiState.Loading()
        viewModelScope.launch(coroutineDispatcherProvider.io()) {
            try {
                val response = repository.getDetailMovie(id = id)
                _movieUiState.value = PlatformsUiState.Success(response)
            } catch (e: HttpException) {
                if (e.code() == 401) {
                    _movieUiState.value =
                        PlatformsUiState.Error("Invalid API key: You must be granted a valid key.")
                } else if (e.code() == 404) {
                    _movieUiState.value =
                        PlatformsUiState.Error("The resource you requested could not be found.")
                } else {
                    _movieUiState.value =
                        PlatformsUiState.Error("Something went wrong: ${e.localizedMessage.orEmpty()}")
                }
            } catch (e: Exception) {
                _movieUiState.value =
                    PlatformsUiState.Error("Something went wrong: ${e.localizedMessage.orEmpty()}")
            }
        }
    }

    private val _tvUiState = MutableStateFlow<PlatformsUiState<Model>>(PlatformsUiState.Empty())
    val tvUiState: StateFlow<PlatformsUiState<Model>> = _tvUiState

    fun fetchTv(id: Long) {
        _tvUiState.value = PlatformsUiState.Loading()
        viewModelScope.launch(coroutineDispatcherProvider.io()) {
            try {
                val response = repository.getDetailTV(id = id)
                _tvUiState.value = PlatformsUiState.Success(response)
            } catch (e: HttpException) {
                if (e.code() == 401) {
                    _tvUiState.value =
                        PlatformsUiState.Error("Invalid API key: You must be granted a valid key.")
                } else if (e.code() == 404) {
                    _tvUiState.value =
                        PlatformsUiState.Error("The resource you requested could not be found.")
                } else {
                    _tvUiState.value =
                        PlatformsUiState.Error("Something went wrong: ${e.localizedMessage.orEmpty()}")
                }
            } catch (e: Exception) {
                _tvUiState.value =
                    PlatformsUiState.Error("Something went wrong: ${e.localizedMessage.orEmpty()}")
            }
        }
    }
}