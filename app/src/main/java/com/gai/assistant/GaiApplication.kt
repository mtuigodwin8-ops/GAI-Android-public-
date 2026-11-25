
package com.gai.assistant

import android.app.Application
import com.gai.assistant.data.GaiDatabase
import com.gai.assistant.data.TaskRepository

class GaiApplication : Application() {

    val database by lazy {
        GaiDatabase.getDatabase(this)
    }

    val taskRepository by lazy {
        TaskRepository(database.taskDao())
    }
}
