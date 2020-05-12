package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import junit.framework.Assert.assertEquals
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*

import org.junit.Test

class StatisticsUtilsTest{

    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero(){


        val tasks= listOf<Task>(Task("title","desc",isCompleted = false))

        val result= getActiveAndCompletedStats(tasks)

        assertThat(result.activeTasksPercent,`is`(100f))

        assertThat(result.completedTasksPercent ,`is`(0f))


    }

    @Test
    fun getActiveAndCompletedStats_noActive_returnsZeroHundred(){
        val tasks= listOf<Task>(Task("title","desc",isCompleted = true))

        val result= getActiveAndCompletedStats(tasks)

        assertThat(result.activeTasksPercent,`is`(0f))

        assertThat(result.completedTasksPercent ,`is`(100f))
    }

    @Test
    fun getActiveAndCompletedStats_both_returnsFortySixty(){
        val tasks= listOf<Task>(
                Task("title","desc",isCompleted = true),
                Task("title","desc",isCompleted = true),
                Task("title","desc",isCompleted = true),
                Task("title","desc",isCompleted = false),
                Task("title","desc",isCompleted = false)
        )

        val result=getActiveAndCompletedStats(tasks)

        assertThat(result.activeTasksPercent,`is`(40f))
        assertThat(result.completedTasksPercent,`is`(60f))
    }

    @Test
    fun getActiveAndCompletedStats_error_returnsZeros(){

        val result= getActiveAndCompletedStats(null)

        assertThat(result.activeTasksPercent,`is`(0f))
        assertThat(result.completedTasksPercent,`is`(0f))

    }

    @Test
    fun getActiveAndCompletedStats_empty_returnZeros(){
        val result= getActiveAndCompletedStats(emptyList())

        assertThat(result.activeTasksPercent,`is`(0f))
        assertThat(result.completedTasksPercent,`is`(0f))
    }
}