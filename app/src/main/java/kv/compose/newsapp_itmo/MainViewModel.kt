package kv.compose.newsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kv.compose.newsapp.domain.repository.PreferenceRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    preferenceRepository: PreferenceRepository
) : ViewModel() {
    val theme = preferenceRepository.getTheme()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0,
        )
}