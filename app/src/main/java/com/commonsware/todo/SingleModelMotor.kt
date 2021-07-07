package com.commonsware.todo

import androidx.lifecycle.ViewModel

class SingleModelMotor(val repo: ToDoRepository, val modelId: String?) : ViewModel() {
    fun getModel() = repo.find(modelId)


    fun save(model: ToDoModel) {
        repo.save(model)
    }

    fun delete(model: ToDoModel) {
        repo.delete(model)
    }
}