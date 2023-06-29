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
