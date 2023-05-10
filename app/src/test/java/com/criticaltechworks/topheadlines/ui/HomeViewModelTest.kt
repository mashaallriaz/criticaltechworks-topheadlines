package com.criticaltechworks.topheadlines.ui

import com.criticaltechworks.topheadlines.MainDispatcherTest
import com.criticaltechworks.topheadlines.data.TestConstants.ARTICLE
import com.criticaltechworks.topheadlines.data.TestConstants.ERROR_MESSAGE
import com.criticaltechworks.topheadlines.data.TestConstants.ERROR_RESULT
import com.criticaltechworks.topheadlines.data.TestConstants.GET_TOP_HEADLINES_RESPONSE
import com.criticaltechworks.topheadlines.data.TestConstants.GET_TOP_HEADLINES_RESULT
import com.criticaltechworks.topheadlines.data.TestConstants.NEWS_SOURCE
import com.criticaltechworks.topheadlines.core.delegate.value
import com.criticaltechworks.topheadlines.core.network.Result
import com.criticaltechworks.topheadlines.domain.GetTopHeadlines
import com.criticaltechworks.topheadlines.ui.home.HomeViewModel
import com.criticaltechworks.topheadlines.ui.home.HomeViewModel.HomeNavigation.HeadlineDetail
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flowOf
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

class HomeViewModelTest : MainDispatcherTest() {

    @RelaxedMockK
    private lateinit var getTopHeadlines: GetTopHeadlines

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getTopHeadlines when loading should emit loading state`() {
        every { getTopHeadlines(any()) } returns flowOf(Result.loading(null))

        val viewModel = initViewModel()

        val loadingState = viewModel.currentState().isLoading
        loadingState shouldBeEqualTo true
    }

    @Test
    fun `getTopHeadlines when success should emit success state`() {
        every { getTopHeadlines(any()) } returns flowOf(GET_TOP_HEADLINES_RESULT)
        val expectedData = GET_TOP_HEADLINES_RESPONSE.articles

        val viewModel = initViewModel()

        val actualData = viewModel.currentState().articles
        actualData shouldBeEqualTo expectedData
    }

    @Test
    fun `getTopHeadlines when error should emit error state`() {
        every { getTopHeadlines(any()) } returns flowOf(ERROR_RESULT)

        val viewModel = initViewModel()

        val error = viewModel.error.value()
        error shouldBeEqualTo ERROR_MESSAGE
    }

    @Test
    fun `navigateToHeadlineDetail should navigate to HeadlineDetail`() {
        val viewModel = initViewModel()

        viewModel.navigateToHeadlineDetail(ARTICLE)

        viewModel.navigation.value() shouldBeEqualTo HeadlineDetail(ARTICLE)
    }

    private fun initViewModel() = HomeViewModel(getTopHeadlines, NEWS_SOURCE)
}