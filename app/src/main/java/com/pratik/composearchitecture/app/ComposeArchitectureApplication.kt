package com.pratik.composearchitecture.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * The [Application] class for the app, initialized with Hilt for dependency injection.
 *
 * @author Pratik Behera
 */
@HiltAndroidApp
class ComposeArchitectureApplication : Application()
