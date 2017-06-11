# WeatherApp

This app is an exercise from [Kotlin for Android Developers by Antonio Leiva](https://leanpub.com/kotlin-for-android-developers)

- [antoniolg/Kotlin-for-Android-Developers](https://github.com/antoniolg/Kotlin-for-Android-Developers)
- [OpenWeatherMap API](http://openweathermap.org/)

## Android Studio
- [Android Studio notes](https://gist.github.com/mnishiguchi/58776be507b704653c16aa099604f08c)

## [Kotlin/anko](https://github.com/Kotlin/anko)

- main purpose: the generation of UI layouts by using code instead of XML
- provides helpful functions and properties that reduce boilerplate.

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