package com.commonsware.todo.ui

import androidx.lifecycle.ViewModel
import com.commonsware.todo.repo.ToDoModel
import com.commonsware.todo.repo.ToDoRepository

class SingleModelMotor(val repo: ToDoRepository, val modelId: String?) : ViewModel() {
    fun getModel() = repo.find(modelId)


    fun save(model: ToDoModel) {
        repo.save(model)
    }

    fun delete(model: ToDoModel) {
        repo.delete(model)
    }
}