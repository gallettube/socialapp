package com.raspberry.socialapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by gallet on 2/8/17.
 */
@Module
class ApplicationModule(private val application: Application) {
    @Singleton @Provides
    fun provideApplication(): Application = application
}