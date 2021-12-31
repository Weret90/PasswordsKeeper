package com.umbrella.passwordskeeper.di

import com.umbrella.passwordskeeper.domain.usecases.*
import org.koin.dsl.module

val useCasesModule = module {
    factory { AddPasswordUseCase(repository = get()) }
    factory { CheckAuthPasswordUseCase(repository = get()) }
    factory { CreateAuthPasswordUseCase(repository = get()) }
    factory { DeletePasswordUseCase(repository = get()) }
    factory { GetPasswordsListUseCase(repository = get()) }
    factory { GetAuthPasswordUseCase(repository = get()) }
}