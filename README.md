# WeatherApp

This app is an exercise from [Kotlin for Android Developers by Antonio Leiva](https://leanpub.com/kotlin-for-android-developers)

- [antoniolg/Kotlin-for-Android-Developers](https://github.com/antoniolg/Kotlin-for-Android-Developers)
- [OpenWeatherMap API](http://openweathermap.org/)

## Android Studio
- [Android Studio notes](https://gist.github.com/mnishiguchi/58776be507b704653c16aa099604f08c)

## [Kotlin/anko](https://github.com/Kotlin/anko)

- main purpose: the generation of UI layouts by using code instead of XML
- provides helpful functions and properties that reduce boilerplate.
    + [Anko SQLite](https://github.com/Kotlin/anko/wiki/Anko-SQLite)

## Kotlin Android Extensions

- The plugin automatically creates a set of properties that give direct access to all the views in the XML.
    + The name of a property is taken from the id so make sure that ids are appropriate for a Kotlin variable.
    + The type of a property is also taken from the XML tag.
- [https://kotlinlang.org/docs/tutorials/android-plugin.html](https://kotlinlang.org/docs/tutorials/android-plugin.html)
- [https://antonioleiva.com/kotlin-android-extensions/](https://antonioleiva.com/kotlin-android-extensions/)


## RecyclerView

- [RecyclerView in Android: The basics](https://antonioleiva.com/recyclerview/)

## Retrieving data from API

> ... you could use any library you want, such as Retrofit, for server requests. However, as
we are just performing a simple API request, we can easily achieve our goal much
easier without adding another third party library.

> ...Kotlin provides some extension functions that will make requests much simpler.

- [square/retrofit](https://github.com/square/retrofit): Type-safe HTTP client for Android and Java by Square, Inc.

## Parsing data

- 1. Convert Json to data classes.
- 2. Convert data classes to domain models.
- Use the names of the properties that are exactly the same as those in the json response.
- [gson](https://github.com/google/gson): A Java serialization/deserialization library to convert Java Objects into JSON and back

## Loading images

> The simplest way to load an image is by making use of an image loader library.

- [square/picasso](http://square.github.io/picasso/): A powerful image downloading and caching library for Android
__
## Log

```
Log.d(javaClass.simpleName, url)
```

## Reified type paramerters
- [https://kotlinlang.org/docs/reference/inline-functions.html#reified-type-parameters](https://kotlinlang.org/docs/reference/inline-functions.html#reified-type-parameters)

> ... We qualified the type parameter with the reified modifier, now it’s accessible inside the function, almost as if it were a normal class. ...

## Interfaces
- [https://kotlinlang.org/docs/reference/interfaces.html](https://kotlinlang.org/docs/reference/interfaces.html)
- Can declare abstract methods
- Can implement methods
- Stateless properties:
  + abstract property declaration
  + accessor implementation

## Generics

```kotlin
// Any types including nullable
class TypedClass<T>(parameter: T) {
  val value: T = parameter
}
```

```kotlin
// Any non-nullable types
class TypedClass<T : Any>(parameter: T) {
    val value: T = parameter
}
```

## Variance
- `out` - to less restrictive (covariance)
- `in` - to more restrictive (contravariance) 

## Using a tool bar instead of ActionBar
- Extend `Theme.AppCompat.Light.NoActionBar` in `app/src/main/res/values/styles.xml`.
- Create a toolbar layout in `app/src/main/res/layout/`.
  + theme 
  + popupTheme
  + background 
  + etc
- Add the toolbar layout to activity layouts.
- Create a ToolbarManager interface.
- Implement the ToolbarManager interface in activities.

## SharedPreferences
- A key-value store
- [https://developer.android.com/training/basics/data-storage/shared-preferences.html](https://developer.android.com/training/basics/data-storage/shared-preferences.html)

## Unit testing
- Use JUnit library
- Put test files in `app/src/test/java/`
- `Ctrl` + `shift` + `R` to run tests
