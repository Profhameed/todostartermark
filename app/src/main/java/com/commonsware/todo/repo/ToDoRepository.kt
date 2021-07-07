package com.commonsware.todo.repo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ToDoRepository(private val store: ToDoEntity.Store, private val appScope: CoroutineScope) {

    fun items(): Flow<List<ToDoModel>> = store.all().map { all ->
        all.map { it.toModel() }
    }

    fun find(modelId:String?):Flow<ToDoModel?> = store.find(modelId).map { it?.toModel() }

    suspend fun save(model:ToDoModel){
        withContext(appScope.coroutineContext){
            store.save(ToDoEntity(model))
        }
    }

    suspend fun delete(model: ToDoModel) {
        withContext(appScope.coroutineContext){
            store.delete(ToDoEntity(model))
        }
    }



}


//var items = emptyList<ToDoModel>()

//    fun save(model: ToDoModel) {
//        items = if (items.any { it.id == model.id }) {
//            items.map { if (it.id == model.id) model else it }
//        } else {
//            items + model
//        }
//    }
//
//    fun find(modelId: String?): ToDoModel? {
//        return items.find { it.id == modelId }
//    }
//
//    fun delete(model: ToDoModel) {
//        items = items.filter { it.id != model.id }
//    }
