package com.example.android.architecture.blueprints.todoapp.tasks


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer


import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.Event
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4::class)
class TasksViewModelTest{

    //Executes eace task synchronously using Architexture Components.
    @get:Rule
    var instantExecutorRule=InstantTaskExecutorRule()

    //Subject under test
    private lateinit var tasksViewModel:TasksViewModel

    @Before
    fun setupViewModel(){
        //Given a fresh ViewModel
        tasksViewModel= TasksViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun addNewTask_setsNewTaskEvent(){

        //when adding a new task
        tasksViewModel.addNewTask()

        // Then the new task event is triggered
        val value=tasksViewModel.newTaskEvent.getOrAwaitValue()

        assertThat(value.getContentIfNotHandled(),not(nullValue()))


        /*
        //Create observer - no need for it to do anything
        val observer=Observer<Event<Unit>>{}

        try{

            //Observe the LiveData forever
            taskViewModel.newTaskEvent.observeForever(observer)

            //When adding a new task
            taskViewModel.addNewTask()

            //Then the new tak event is triggered
            val value=taskViewModel.newTaskEvent.value
            assertThat(value?.getContentIfNotHandled(),(not(nullValue())))


        }finally {
            //Whatever happens, don't forget to remove the observer!
            taskViewModel.newTaskEvent.removeObserver(observer)

        }*/
    }

    @Test
    fun setFilterAllTasks_tasksAddViewVisible(){

        //When the filter type is ALL_TASKS
        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        //Then the "Add task" action is visible
        assertThat(tasksViewModel.tasksAddViewVisible.getOrAwaitValue(),`is`(true))

    }

    @Test
    fun getTasksAddViewVisible(){
        //When the filter type is ALL_TASKS
        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        //Then the "Add task" action is visible
        assertThat(tasksViewModel.tasksAddViewVisible.getOrAwaitValue(),`is`(true))
    }
}