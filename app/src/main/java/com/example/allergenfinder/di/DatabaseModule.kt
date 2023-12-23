package com.example.allergenfinder.di

import androidx.room.Room
import com.example.allergenfinder.common.DATABASE_FILE
import com.example.allergenfinder.data.database.AllergenFinderDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = AllergenFinderDatabase::class.java,
            name = DATABASE_FILE
        ).build()
    }

    factory { get<AllergenFinderDatabase>().productDao() }
}
