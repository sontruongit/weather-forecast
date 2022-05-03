# weather-forecast
Retrieving weather information based on their searching criteria and render the searched results on dashboard screen.

App Architecture
----------------
The app implements MVVM app architecture:

![MVVM App Architecture](https://miro.medium.com/max/1360/1*tO9RsrblUPOv_u0loUM97g.png)

Package Structure
-----------------
There are 4 layers of organization for classes
* `data`: The data package contains any classes that are directly related to any kind of data or used within the app
  * `data.local`: The local package contains any classes that related to local data, such as memory, sharedpreferences, entity, database, Room etc.
  * `data.remote`: The remote package contains any classes that related to remote data, such as dto, Retrofit, etc.
  * `data.models`: The model package contains any classes that used by ui layer
  * `data.repositories`: The repositories package contains all classes that used to get date from local or remote
* `di`: The di package contains all classes that related to dependency injection
* `ui`: The ui package contains all classes that related to UI components, such as Activity, Adapter, ViewModel, etc.
  * `ui.main`: The main package contains all classes about main feature
* `utils`: The utils package contains any kind of helper or utility class

Libraries Used
--------------
* [Foundation][0] - Components for core system capabilities, Kotlin extensions and support for
  multidex and automated testing.
  * [AppCompat][1] - Degrade gracefully on older versions of Android.
  * [Android KTX][2] - Write more concise, idiomatic Kotlin code.
  * [Test][3] - An Android testing framework for unit and runtime UI tests.
* [Architecture][10] - A collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
  * [View Binding][11] - A feature that allows you to more easily write code that interacts with views
  * [ViewModel][12] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
     asynchronous tasks for optimal execution.
  * [LiveData][13] - Build data objects that notify views when the underlying database changes.   
  * [Lifecycles][14] - Create a UI that automatically responds to lifecycle events.  
* Third party libraries
  * [Retrofit][21] - A type-safe HTTP client for Android and Java.
  * [GSON][22] - Gson is a Java library that can be used to convert Java Objects into their JSON representation and vice versa.
  * [Hilt][23] - Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
  * [Kotlin Coroutines][24] - Coroutines manages background threads with simplified code and reducing needs for callbacks.
     
Android Studio Setup
--------------------
* Check out the code from github
* Import the source code into Android Studio
* Run the app

Checklist of items that has been done
-------------------------------------
* Programming language: Kotlin
* Using MVVM app architecture
* Apply LiveData mechanism
* UI should look like design
* Write Unit Tests
* Exception Handling
* Caching Handling
* Accessibility for disability supports
 
[0]: https://developer.android.com/jetpack/components
[1]: https://developer.android.com/topic/libraries/support-library/packages#v7-appcompat
[2]: https://developer.android.com/kotlin/ktx
[3]: https://developer.android.com/training/testing/
[10]: https://developer.android.com/jetpack/arch/
[11]: https://developer.android.com/topic/libraries/view-binding
[12]: https://developer.android.com/topic/libraries/architecture/viewmodel
[13]: https://developer.android.com/topic/libraries/architecture/livedata
[14]: https://developer.android.com/topic/libraries/architecture/lifecycle
[21]: https://github.com/square/retrofit
[22]: https://github.com/google/gson
[23]: https://developer.android.com/training/dependency-injection/hilt-android
[24]: https://kotlinlang.org/docs/reference/coroutines-overview.html
