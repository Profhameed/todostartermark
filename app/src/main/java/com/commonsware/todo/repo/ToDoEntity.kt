package com.commonsware.todo.repo

import android.app.SyncNotedAppOp
import android.graphics.ColorSpace
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.koin.core.definition.indexKey
import java.time.Instant
import java.util.*


//This class has the same properties as ToDoModel. You might wonder why we did not
//just use ToDoModel. Mostly, that is for realism: there is no guarantee that your
//entities will have a 1:1 relationship with models. Room puts restrictions on how
//entities can be constructed, particularly when it comes to relationships with other
//entities. Things that you might do in model objects (e.g., a category object holding a
//collection of item objects) wind up having to be implemented significantly
//differently using Room entities. Those details will get hidden by your repositories. A
//repository exists in part to convert specialized forms of your data (Room entities,
//Web service responses, etc.) into the model objects that your UI is set up to use.
@Entity(tableName = "todos", indices = [Index(value = ["id"])])
data class ToDoEntity(
    val description: String,
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val notes: String = "",
    val createdOn: Instant = Instant.now(),
    val isCompleted: Boolean = false
) {
    constructor(model: ToDoModel) : this(
        id = model.id,
        description = model.description,
        notes = model.notes,
        createdOn = model.createdOn,
        isCompleted = model.isCompleted
    )

    fun toModel(): ToDoModel {
        return ToDoModel(
            id = id,
            description = description,
            notes = notes,
            createdOn = createdOn,
            isCompleted = isCompleted
        )
    }


    @Dao
    interface Store {
        @Query("SELECT * FROM todos ORDER BY description")
        fun all(): Flow<List<ToDoEntity>>

        @Query("SELECT * FROM todos WHERE id = :modelId")
        fun find(modelId: String?): Flow<ToDoEntity?>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun save(vararg entities: ToDoEntity)

        @Delete
        suspend fun delete(vararg entities: ToDoEntity)
    }
}