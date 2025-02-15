## RecipeBook Application (KMP)

This is a Kotlin Multiplatform project targeting Android, iOS, Desktop. RecipeBook is a ios, desktop, android application designed to help users discover recipes. The app allows users to browse through a collection of recipes and details of every particular recipe to cook dishes.

## Technical Requirements
Programming Language:  Kotlin + jetpackCompose using KMP, MVVM architecture 
API: using Ktor for API calls to fetch recipe data
Design: Material Design used in this KMP-project
Images: coil for images loading







This is a Kotlin Multiplatform project targeting Android, iOS, Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
