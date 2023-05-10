package com.criticaltechworks.topheadlines

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Rule

abstract class MainDispatcherTest(dispatcher: TestDispatcher = UnconfinedTestDispatcher()) {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule(dispatcher)
}