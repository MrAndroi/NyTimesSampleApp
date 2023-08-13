package com.sami.core.formatter.di

import com.sami.core.formatter.Formatter
import com.sami.core.formatter.date.DateFormatter
import com.sami.core.formatter.date.DateToLongFormatter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
interface FormatterModule {

    @Binds
    @BindDateFormatter
    fun bindDateFormatter(impl: DateFormatter): Formatter

    @Binds
    @BindDateToLongFormatter
    fun bindDateToLongFormatter(impl: DateToLongFormatter): Formatter

    @Qualifier
    @Retention
    annotation class BindDateFormatter

    @Qualifier
    @Retention
    annotation class BindDateToLongFormatter
}