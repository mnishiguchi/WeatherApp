package com.mnishiguchi.weatherapp.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.mnishiguchi.weatherapp.ui.App
import org.jetbrains.anko.db.*

/**
 * A db helper that is powered by Anko.
 * ---
 * Usage:
 *     val result = dbHelper.use {
 *         // - Inside the block, we can access the db.
 *         // - We can return any value including Unit.
 *         val queriedObject = ...
 *         queriedObject
 *     }
 */
class DbHelper(ctx: Context = App.instance)
        : ManagedSQLiteOpenHelper(ctx, DbHelper.DB_NAME, null, DbHelper.DB_VERSION) {

    companion object {
        val DB_NAME = "WeatherApp.db"
        val DB_VERSION = 1

        // The object won't be created until it's used.
        val instance by lazy { DbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(LocationEntity.TABLE_NAME, true,
                LocationEntity.ID      to INTEGER + PRIMARY_KEY,
                LocationEntity.CITY    to TEXT,
                LocationEntity.COUNTRY to TEXT)

        db.createTable(ForecastEntity.TABLE_NAME, true,
                ForecastEntity.ID          to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                ForecastEntity.DATE        to INTEGER,
                ForecastEntity.DESCRIPTION to TEXT,
                ForecastEntity.HIGH        to INTEGER,
                ForecastEntity.LOW         to INTEGER,
                ForecastEntity.ICON_URL    to TEXT,
                ForecastEntity.LOCATION_ID to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(LocationEntity.TABLE_NAME, true)
        db.dropTable(ForecastEntity.TABLE_NAME, true)
        onCreate(db)
    }
}