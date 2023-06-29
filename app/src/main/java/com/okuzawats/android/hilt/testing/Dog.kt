package com.okuzawats.android.hilt.testing

import javax.inject.Inject

class Dog @Inject constructor() : Animal {
    override fun bow(): Int {
        return 42
    }
}
