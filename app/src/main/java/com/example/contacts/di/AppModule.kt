package com.example.contacts.di

import com.example.contacts.data.ContactRepository
import com.example.contacts.data.ContactRepositoryImpl
import com.example.contacts.data.local.ContactLocalSource
import com.example.contacts.domain.ContactInteractor
import com.example.contacts.feature.mainscreen.ui.MainScreenViewModel
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<Realm> {
        Realm.init(androidApplication())

        val configuration = RealmConfiguration.Builder()
            .name("contacts.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()

        Realm.setDefaultConfiguration(configuration)

        Realm.getDefaultInstance()
    }

    single<ContactLocalSource> {
        ContactLocalSource(realm = get<Realm>())
    }

    single<ContactRepository> {
        ContactRepositoryImpl(contactLocalSource = get<ContactLocalSource>())
    }

    single<ContactInteractor> {
        ContactInteractor(contactRepository = get<ContactRepository>())
    }

    viewModel {
        MainScreenViewModel(interactor = get<ContactInteractor>())
    }
}