package com.example.android.architecture.blueprints.todoapp.tasks


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer


import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.Event
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4::class)
class TasksViewModelTest{

    @get:Rule
    var instantExecutorRule=InstantTaskExecutorRule()

    @Test
    fun addNewTask_setsNewTaskEvent(){

        //Given a fresh ViewModel
        val taskViewModel=TasksViewModel(ApplicationProvider.getApplicationContext())

        //when adding a new task
        taskViewModel.addNewTask()

        // Then the new task event is triggered
        val value=taskViewModel.newTaskEvent.getOrAwaitValue()

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
}