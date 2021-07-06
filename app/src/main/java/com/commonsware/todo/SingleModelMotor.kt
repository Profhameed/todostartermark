package com.commonsware.todo

import androidx.lifecycle.ViewModel

class SingleModelMotor(val repo: ToDoRepository, val modelId: String) : ViewModel() {
    fun getModel() = repo.find(modelId)
}