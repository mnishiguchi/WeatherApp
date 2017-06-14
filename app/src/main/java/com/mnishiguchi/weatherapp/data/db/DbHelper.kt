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

        // The object won’t be created until it’s used.
        val instance by lazy { DbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
                ForecastCityTable.NAME, true,
                ForecastCityTable.ID to INTEGER + PRIMARY_KEY,
                ForecastCityTable.CITY to TEXT,
                ForecastCityTable.COUNTRY to TEXT)

        db.createTable(
                ForecastDayTable.NAME, true,
                ForecastDayTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                ForecastDayTable.DATE to INTEGER,
                ForecastDayTable.DESCRIPTION to TEXT,
                ForecastDayTable.HIGH to INTEGER,
                ForecastDayTable.LOW to INTEGER,
                ForecastDayTable.ICON_URL to TEXT,
                ForecastDayTable.CITY_ID to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(ForecastCityTable.NAME, true)
        db.dropTable(ForecastDayTable.NAME, true)
        onCreate(db)
    }
}