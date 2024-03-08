package eu.mokrzycki.synthriders.customsongsmanager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.mokrzycki.synthriders.customsongsmanager.api.ApiService

class MainViewModelFactory(
    private val repository: MusicRepository,
    private val apiService: ApiService,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository, apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}