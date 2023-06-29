package com.okuzawats.android.hilt.testing

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.amshove.kluent.shouldBe
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject lateinit var animal: Animal

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun addition_isCorrect() {
        // Productionは42を返すが、テスト用は100を返す
        animal.bow() shouldBe 100
        // animal.bow() shouldBe 42
    }
}
