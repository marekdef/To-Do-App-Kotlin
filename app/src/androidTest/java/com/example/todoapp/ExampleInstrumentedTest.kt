package com.example.todoapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.todoapp.data.ToDoDatabase

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Ignore
import kotlin.concurrent.thread

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun testIfCallsOnTwoThreadsReturnSameInstance() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.todoapp", appContext.packageName)

        var instance1: ToDoDatabase? = null
        var instance2: ToDoDatabase? = null
        val thread1 = thread {
            /*
            Add sleep to make this test pass.
            This adds necessary time so threads do not execute
            `if (tempInstance != null)` at the same time
            Thread.sleep(5_000)
             */
            instance1 = ToDoDatabase.getDatabase(appContext)
        }

        val thread2 = thread {
            instance2 = ToDoDatabase.getDatabase(appContext)
        }

        thread2.join()
        thread1.join()

        assertSame(instance1, instance2)
    }


    /*
    If you remove @Ignore this test will pass because here singleton is initialized properly
     */
    @Ignore
    @Test
    fun testIfCallsInSequenceReturnSameInstance() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.todoapp", appContext.packageName)

        val instance1: ToDoDatabase = ToDoDatabase.getDatabase(appContext)
        val instance2: ToDoDatabase  = ToDoDatabase.getDatabase(appContext)

        assertSame(instance1, instance2)
    }
}