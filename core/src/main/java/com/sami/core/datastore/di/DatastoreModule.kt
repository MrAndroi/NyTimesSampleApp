package com.sami.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.sami.core.datastore.models.AppDataModel
import com.sami.core.datastore.serializers.AppDataSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule {

    private const val SETTINGS_STORE_FILE_NAME = "app_data_file.pb"

    @Provides
    @Singleton
    fun provideSettingsDataStore(@ApplicationContext context: Context): DataStore<AppDataModel> {
        return DataStoreFactory.create(
            serializer = AppDataSerializer(),
            produceFile = { context.dataStoreFile(SETTINGS_STORE_FILE_NAME) },
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        )
    }

}