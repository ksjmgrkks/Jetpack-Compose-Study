package com.kks.readerapp.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kks.readerapp.data.Resource
import com.kks.readerapp.model.Item
import com.kks.readerapp.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: BookRepository)

    : ViewModel(){
        suspend fun getBookInfo(bookId: String): Resource<Item> {
            return repository.getBookInfo(bookId)
        }
    }