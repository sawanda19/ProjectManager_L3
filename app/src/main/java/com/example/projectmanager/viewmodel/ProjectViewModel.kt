package com.example.projectmanager.viewmodel

import androidx.lifecycle.ViewModel
import com.example.projectmanager.model.Project
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProjectViewModel : ViewModel() {

    private val _projects = MutableStateFlow(
        listOf(
            Project(id = 1, name = "Мобільний додаток", description = "iOS/Android розробка", progress = 0.75f),
            Project(id = 2, name = "Веб-сайт", description = "Корпоративний", progress = 0.40f),
            Project(id = 3, name = "База даних", description = "Дизайн та реалізація", progress = 0.60f)
        )
    )
    val projects: StateFlow<List<Project>> = _projects.asStateFlow()

    private var nextId = 4

    fun addProject(name: String, description: String) {
        val newProject = Project(id = nextId++, name = name, description = description, progress = 0f)
        _projects.value = _projects.value + newProject
    }

    fun deleteProject(id: Int) {
        _projects.value = _projects.value.filter { it.id != id }
    }

    fun updateProgress(id: Int, progress: Float) {
        _projects.value = _projects.value.map {
            if (it.id == id) it.copy(progress = progress) else it
        }
    }

    fun getProjectById(id: Int): Project? = _projects.value.find { it.id == id }
}