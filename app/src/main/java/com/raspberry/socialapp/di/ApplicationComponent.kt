package com.raspberry.socialapp.di

import android.app.Application
import dagger.Component
import javax.inject.Singleton

/**
 * Created by gallet on 2/8/17.
 */
@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class
))
interface ApplicationComponent: IApplicationProvider {
    fun inject(application: Application)
}