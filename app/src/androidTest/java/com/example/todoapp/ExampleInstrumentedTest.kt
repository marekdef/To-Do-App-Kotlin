package com.example.todoapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.todoapp.data.ToDoDatabase

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import kotlin.concurrent.thread

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.todoapp", appContext.packageName)

        var instance1: ToDoDatabase? = null
        var instance2: ToDoDatabase? = null
        val thread1 = thread {
            instance1 = ToDoDatabase.getDatabase(appContext)
        }

        val thread2 = thread {
            instance2 = ToDoDatabase.getDatabase(appContext)
        }

        thread2.join()
        thread1.join()

        assertSame(instance1, instance2)

    }
}