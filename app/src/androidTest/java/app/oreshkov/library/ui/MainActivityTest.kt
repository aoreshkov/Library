package app.oreshkov.library.ui

import androidx.hilt.work.HiltWorkerFactory
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.impl.utils.SynchronousExecutor
import androidx.work.testing.WorkManagerTestInitHelper
import app.oreshkov.library.di.LocalDataModule
import app.oreshkov.library.di.RepositoryModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@UninstallModules(LocalDataModule::class, RepositoryModule::class)
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MainActivityTest {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()

        WorkManagerTestInitHelper.initializeTestWorkManager(
            InstrumentationRegistry.getInstrumentation().targetContext,
            Configuration.Builder()
                .setExecutor(SynchronousExecutor())
                .setWorkerFactory(workerFactory)
                .build())
    }

    @Test
    fun mainActivityLaunchTest(){
        val scenario = launchActivity<MainActivity>()
    }
}