package com.mnishiguchi.weatherapp.extensions

import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder

/**
 * Parses a query result that is expected to be a collection, using the passed-in parser.
 */
fun <T : Any> SelectQueryBuilder.parseList(parser: (Map<String, Any?>) -> T): List<T> {

    // MapRowParser converts the Cursor into a list of objects, using the column names as keys.
    return parseList(object: MapRowParser<T> {
        override fun parseRow(columns: Map<String, Any?>): T {
            return parser(columns)
        }
    })
}

/**
 * Parses a query result that is expected to be a single object or null, using the passed-in parser.
 */
fun <T: Any> SelectQueryBuilder.parseOpt(parser: (Map<String, Any?>) -> T): T? {

    return parseOpt(object: MapRowParser<T> {
        override fun parseRow(columns: Map<String, Any?>): T {
            return parser(columns)
        }
    })
}

fun SelectQueryBuilder.byId(id: Long): SelectQueryBuilder {
    return whereSimple("_id = ?", id.toString())
}

fun SQLiteDatabase.clear(tableName: String) {
    execSQL("delete from $tableName")
}

