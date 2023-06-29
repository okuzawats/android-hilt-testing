# Android Hilt Testing

Hiltを単体テストに活用するための実験用プロジェクト。

## setup Hilt

通常通りにHiltをセットアップ

## setup test

テスト用にHiltの依存、TestRunner、Robolectricを追加

```groovy
 # use Hilt in tests
 testImplementation 'com.google.dagger:hilt-android-testing:2.46.1'
 kaptTest 'com.google.dagger:hilt-android-compiler:2.46.1'
 # TestRunner
 testImplementation 'androidx.test:runner:1.5.2'
 # Robolectric
 testImplementation 'org.robolectric:robolectric:4.10.3'
```

## JUnit4

`@Config` に `HiltTestApplication::class` を、`@RunWith` に `RobolectricTestRunner::class)` を指定する。
テストルールとして `HiltAndroidRule` を適用した上で、setup内で `inject` を呼び出す。

```kotlin
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
```

## Module

`test` 内でHiltによって解決される依存関係を差し替える。
`@TestInstallIn` にComponentと置き換え対象となるModuleを指定する。

```kotlin
package com.okuzawats.android.hilt.testing

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.every
import io.mockk.mockk

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AnimalModule::class]
)
class FakeAnimalModule {
    @Provides
    fun provideAnimal(): Animal {
        return mockk<Animal>().also {
            every { it.bow() } returns 100
        }
    }
}
```
