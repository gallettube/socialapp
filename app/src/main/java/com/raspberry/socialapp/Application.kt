package com.raspberry.socialapp

import android.app.Application
import com.raspberry.socialapp.di.ApplicationComponent
import com.raspberry.socialapp.di.ApplicationModule
import com.raspberry.socialapp.di.DaggerApplicationComponent
/**
 * Created by gallet on 2/8/17.
 */
class Application : Application() {

    lateinit var applicationComponent : ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        initializeDependencyInjections()

    }

    fun initializeDependencyInjections() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }


}