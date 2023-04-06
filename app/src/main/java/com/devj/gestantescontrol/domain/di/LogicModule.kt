package com.devj.gestantescontrol.domain.di

import com.devj.gestantescontrol.domain.DateCalculator
import com.devj.gestantescontrol.presenter.AndroidDateCalculator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LogicModule {
    @Binds
    abstract fun bindDateCalculator(impl: AndroidDateCalculator): DateCalculator
}