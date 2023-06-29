package com.okuzawats.android.hilt.testing

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AnimalModule {
    @Provides
    fun provideAnimal(
        dog: Dog,
    ): Animal {
        return dog
    }
}
