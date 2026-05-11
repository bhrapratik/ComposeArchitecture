# ComposeArchitecture

A modern Android application built using **Jetpack Compose**, following scalable and production-style Android architecture principles.

## GitHub Repository

https://github.com/bhrapratik/ComposeArchitecture

This project demonstrates modern Android development using:

- Single Activity Architecture
- Jetpack Compose
- Compose Navigation
- Bottom Navigation
- MVVM
- StateFlow + Flow
- Hilt Dependency Injection
- Retrofit Networking
- Room Database
- Offline-first Architecture
- Repository Pattern
- Feature-based Package Structure
- Modularization (Core modules)
- Reactive UI

---

# Tech Stack

- **UI**: Jetpack Compose, Material 3, Navigation Compose
- **Asynchronous**: Kotlin Coroutines, Flow, StateFlow
- **Dependency Injection**: Hilt
- **Networking**: Retrofit, OkHttp
- **Database**: Room
- **Code Generation**: KSP

---

# Architecture

This project follows modern Android architecture principles:

- **MVVM (Model-View-ViewModel)**: Decouples UI logic from business logic.
- **Clean Architecture Principles**: Separation of concerns across layers.
- **Repository Pattern**: Centralized data access strategy.
- **Offline-first Architecture**: Room database acts as the Single Source of Truth (SSOT).
- **Reactive UI**: UI automatically updates in response to state changes.

---

# Architecture Highlights

- Offline-first architecture
- Reactive UI using Flow + StateFlow
- Modular core layers
- Clean separation of DTO, Entity, and Domain models
- Single Source Of Truth (SSOT)
- Scalable feature-based structure
- Dependency Injection using Hilt

---

# App Architecture Flow

```text
Compose UI
      ↓
ViewModel
      ↓
Repository
      ↓
Room Database (Single Source Of Truth)
      ↑
Retrofit API
```

---

# Data Flow

The app follows a unidirectional data flow for state and a reactive stream for data:

```text
Remote API (JsonPlaceholder)
      ↓
PostDto (Network Model)
      ↓
Mapper (toPost, toPostEntity)
      ↓
Post (Domain Model) ↔ PostEntity (Database Model)
      ↓
Room Database (Local Storage)
      ↓
Flow<List<Post>> (Reactive Stream)
      ↓
StateFlow<HomeUiState> (UI State)
      ↓
Compose UI (UI Layer)
```

---

# Modular Architecture

The project is structured into functional modules to promote reusability and separation of concerns:

- `:app`: Main entry point, contains features and navigation.
- `:core-network`: Handles all network-related operations and Retrofit configurations.
- `:core-database`: Manages local storage, Room database, and DAOs.

---

# Project Structure

```text
composearchitecture/
│
├── app/
│   └── src/main/java/com/pratik/composearchitecture/
│       ├── feature/           # Feature-based packages
│       │   ├── home/          # UI, ViewModel, Repository for Home
│       │   ├── details/       # UI for Details
│       │   └── profile/       # UI for Profile
│       ├── navigation/        # AppNavHost and Screen definitions
│       └── core/              # App-level core components
│
├── core-network/
│   └── src/main/java/com/pratik/corenetwork/
│       ├── api/               # Retrofit service interfaces
│       ├── di/                # Hilt modules for network layer
│       └── model/             # Network DTOs
│
├── core-database/
│   └── src/main/java/com/pratik/coredatabase/database/
│       ├── dao/               # Room DAOs
│       ├── datasource/        # Local data sources
│       ├── di/                # Hilt modules for database layer
│       ├── entity/            # Room entities
│       └── AppDatabase.kt     # Room database definition
```

---

# Features Implemented

## UI & Navigation

- **Jetpack Compose**: Modern declarative UI toolkit.
- **Material 3**: Latest Material Design components and styling.
- **Navigation Compose**: Navigation using a `Screen` sealed class.
- **Bottom Navigation**: Persistent navigation between top-level destinations.
- **Single Activity Architecture**: Entire app runs within `MainActivity`.

---

## Data Management

- **Offline-first**: API data is synchronized into local Room database storage.
- **Repository Pattern**: `HomeRepository` abstracts local and remote data sources.
- **Mapping Layer**: Clear separation between DTOs, Entities, and Domain Models.
- **Single Source Of Truth**: UI observes local database instead of direct API responses.

---

## State Management

- **UiState Pattern**: Encapsulates loading, success, and error states.
- **UiEvent Pattern**: Handles user interactions and navigation events.
- **StateFlow & Flow**: Reactive state updates using Kotlin Flows.
- **Lifecycle-aware UI**: Uses `collectAsStateWithLifecycle()`.

---

## Dependency Injection

- **Hilt**: Dependency injection for ViewModels, repositories, Retrofit, Room, and data sources.

---

## Networking

- **Retrofit**: REST API integration.
- **OkHttp**: Network logging and request handling.
- **Coroutines**: Asynchronous API calls.
- **Modular Network Layer**: Network logic isolated inside `core-network`.

---

## Database

- **Room Database**: Local persistent storage.
- **DAO Architecture**: Structured database access layer.
- **Flow Queries**: Reactive database observation.
- **Offline Caching**: Cached data survives app restarts and network loss.

---

# Why Flow + StateFlow?

This project uses:

- `Flow` for reactive database streams.
- `StateFlow` for reactive UI state management.

Benefits:

- automatic UI updates
- lifecycle awareness
- coroutine support
- scalable architecture
- reactive programming model

---

# Example Flow Architecture

```kotlin
fun getPosts(): Flow<List<Post>>
```

Observed in ViewModel:

```kotlin
repository.getPosts().collectLatest { posts ->
    // update ui state
}
```

---

# Example StateFlow Pattern

```kotlin
private val _uiState = MutableStateFlow(HomeUiState())

val uiState = _uiState.asStateFlow()
```

Observed in Compose:

```kotlin
val uiState by viewModel.uiState.collectAsStateWithLifecycle()
```

---

# Offline-first Architecture

The app follows an offline-first approach:

1. API data is fetched using Retrofit.
2. Data is cached into Room Database.
3. UI observes Room using Flow.
4. Database updates automatically refresh the UI.

Benefits:

- cached startup
- offline support
- reactive updates
- resilient architecture
- improved user experience

---

# Documentation

The project follows Kotlin KDoc documentation practices.

Example:

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

# Future Roadmap

- [ ] Resource wrapper architecture
- [ ] Unit testing for repositories and ViewModels
- [ ] UI testing using Compose Test Rule
- [ ] Pagination / Paging 3 integration
- [ ] Pull-to-refresh support
- [ ] Dark mode improvements and Dynamic Color
- [ ] Move features into dedicated Gradle modules
- [ ] Search and filter functionality
- [ ] Network monitoring
- [ ] CI/CD pipeline setup

---

# Getting Started

1. Clone the repository

```bash
git clone https://github.com/bhrapratik/ComposeArchitecture.git
```

2. Open in Android Studio

Use the latest Android Studio version for best Compose support.

3. Sync Gradle

Allow dependencies to download and index.

4. Run the app

Deploy to an emulator or physical device.

---

# Author

**Pratik Behera**  
Android Developer focused on modern Android architecture and Jetpack Compose.