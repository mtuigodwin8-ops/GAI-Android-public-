
package com.gai.assistant.data

data class Task(
    val id: String,
    val title: String,
    val description: String = "",
    val category: String = "General",
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val dueAt: Long? = null
)
