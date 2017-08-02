package com.raspberry.socialapp.di

/**
 * Created by gallet on 2/8/17.
 */
import android.app.Application

interface IApplicationProvider {
    fun getApplication(): Application
}
