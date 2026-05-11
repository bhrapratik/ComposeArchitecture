# ComposeArchitecture

A modern Android application built using **Jetpack Compose**, following clean and scalable Android architecture principles.

This project demonstrates how to structure a modern Android application using:

- Single Activity Architecture
- Jetpack Compose
- Compose Navigation
- Bottom Navigation
- MVVM
- StateFlow
- Hilt Dependency Injection
- Retrofit API integration
- Repository Pattern
- Feature-based architecture
- Reactive UI patterns

---

# Tech Stack

- Kotlin
- Jetpack Compose
- Navigation Compose
- ViewModel
- Kotlin Coroutines
- StateFlow
- Hilt
- Retrofit
- OkHttp
- Material 3

---

# Architecture

This project follows modern Android architecture principles using:

```text
Single Activity
+ MVVM
+ StateFlow
+ Hilt DI
+ Retrofit
+ Repository Pattern
+ Compose Navigation
+ Feature-based architecture
+ Reactive UI
```

---

# App Flow

```text
Compose UI
      ↓
ViewModel
      ↓
Repository
      ↓
Retrofit API
      ↓
Remote Server
```

---

# Project Structure

```text
composearchitecture/
│
├── app/
│
├── core/
│   └── network/
│       ├── ApiService.kt
│       ├── RetrofitModule.kt
│       │
│       └── model/
│           └── response/
│               └── PostDto.kt
│
├── navigation/
│
└── feature/
    ├── home/
    │   ├── data/
    │   │   └── HomeRepository.kt
    │   │
    │   ├── presentation/
    │   │   ├── HomeScreen.kt
    │   │   ├── HomeViewModel.kt
    │   │   ├── HomeUiState.kt
    │   │   └── HomeUiEvent.kt
    │
    ├── details/
    │
    └── profile/
```

---

# Features Implemented

## UI
- Jetpack Compose UI
- LazyColumn list rendering
- Reactive UI rendering
- Material 3 design
- State-driven UI

## Navigation
- Single Activity Navigation
- Compose Navigation
- Bottom Navigation
- Navigation arguments
- Dynamic route navigation
- Home → Details navigation

## State Management
- ViewModel
- StateFlow
- UiState pattern
- UiEvent pattern
- Loading state handling
- Error state handling

## Networking
- Retrofit API integration
- DTO models
- OkHttp logging interceptor
- Coroutine-based API calls
- Repository-based networking

## Dependency Injection
- Hilt DI
- Injected repositories
- Hilt ViewModels
- Singleton Retrofit module

## Documentation
- Kotlin KDoc documentation
- Documented DTO models
- Repository documentation
- ViewModel documentation
- Improved code readability
- Better maintainability

---

# Why StateFlow Instead Of LiveData?

This project uses `StateFlow` because it:

- Works seamlessly with Kotlin Coroutines
- Integrates better with Compose
- Supports reactive programming
- Scales better for modern Android apps
- Is widely used in production Compose applications

---

# Example StateFlow Pattern

```kotlin
private val _uiState = MutableStateFlow(
    HomeUiState(
        isLoading = true
    )
)

val uiState = _uiState.asStateFlow()
```

Observed in Compose using:

```kotlin
val uiState by viewModel.uiState.collectAsState()
```

---

# Example Documentation

```kotlin
/**
 * Data Transfer Object (DTO) representing a post received from the API.
 *
 * @property id The unique identifier for the post.
 * @property title The title of the post.
 * @property body The content/body of the post.
 * @author Pratik Behera
 */
```

---

# Future Improvements

Planned next steps:

- Domain layer
- DTO → Domain mappers
- Room Database
- Offline caching
- Pagination
- Unit testing
- UI testing
- Dark mode improvements
- Multi-module modularization

---

# Learning Goals

This project is built to practice and demonstrate:

- Modern Android development
- Clean architecture principles
- Compose UI development
- Dependency Injection with Hilt
- Retrofit networking
- Reactive state management
- Scalable Android architecture

---

# Screens

- Home Screen
- Details Screen
- Profile Screen

---

# Getting Started

1. Clone the repository

```bash
git clone <your-repository-url>
```

2. Open in Android Studio

3. Sync Gradle

4. Run the app

---

# Author

Pratik Behera

Android Developer focused on modern Android architecture and Jetpack Compose.