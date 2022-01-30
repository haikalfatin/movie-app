package com.rcyn.made.movie_app

import android.app.Application
import com.rcyn.made.movie_app.di.AppComponent
import com.rcyn.made.movie_app.di.DaggerAppComponent
import com.rcyn.made.core.di.CoreComponent
import com.rcyn.made.core.di.DaggerCoreComponent

open class MyApplication : Application() {

  private val coreComponent: CoreComponent by lazy {
    DaggerCoreComponent.factory().create(applicationContext)
  }

  val appComponent: AppComponent by lazy {
    DaggerAppComponent.factory().create(coreComponent)
  }
}