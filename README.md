# Compose Architecture Sample

A modern Android application built using **Jetpack Compose**, following clean and scalable architecture principles.

This project demonstrates how to structure a production-ready Android app using:

- Single Activity Architecture
- Jetpack Compose
- Compose Navigation
- Bottom Navigation
- MVVM
- StateFlow
- Feature-based modular structure
- Reactive UI patterns

---

# Tech Stack

- Kotlin
- Jetpack Compose
- Navigation Compose
- ViewModel
- Kotlin Coroutines
- StateFlow
- Material 3

---

# Architecture

This project follows:

```text
MVVM + Clean Architecture
```

## App Flow

```text
Home Screen
    ↓
Details Screen
```

---

# Project Structure

```text
composearchitecture/
│
├── app/
│   └── navigation/
│
├── core/
│
├── feature-home/
│   ├── presentation/
│   │   ├── HomeScreen.kt
│   │   ├── HomeViewModel.kt
│   │   ├── HomeUiState.kt
│   │   └── HomeUiEvent.kt
│   │
│   ├── domain/
│   │
│   └── data/
│
├── feature-details/
│
└── feature-profile/
```

---

# Features Implemented

## UI
- Jetpack Compose UI
- LazyColumn list rendering
- Reusable composables
- Material 3 design

## Navigation
- Single Activity Navigation
- Compose Navigation
- Bottom Navigation
- Home → Details navigation

## State Management
- ViewModel
- StateFlow
- Reactive UI updates
- UiState pattern

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
private val _uiState = MutableStateFlow(HomeUiState())

val uiState = _uiState.asStateFlow()
```

Observed in Compose using:

```kotlin
val uiState by viewModel.uiState.collectAsState()
```

---

# Future Improvements

Planned next steps:

- Hilt Dependency Injection
- Retrofit API integration
- Room Database
- Repository pattern
- Pagination
- Error handling
- Loading states
- Unit testing
- Dark mode support
- Modularization

---

# Learning Goals

This project is built to practice and demonstrate:

- Modern Android development
- Clean architecture principles
- Compose UI development
- Reactive state management
- Scalable project structure

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

3. Run the app

---

# Author

Pratik Behera

Android Developer focused on modern Android architecture and Jetpack Compose.
