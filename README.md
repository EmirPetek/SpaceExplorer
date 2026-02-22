# Space Explorer - Kotlin Multiplatform Case Study

A modern Kotlin Multiplatform (KMP) application designed to browse SpaceX launches and rocket details. This project demonstrates the power of sharing both business logic and user interface across Android and iOS platforms while maintaining a clean, scalable architecture.

## App Overview
Space Explorer consumes the [SpaceX API v4](https://github.com/r-spacex/SpaceX-API) to provide users with up-to-date information regarding space missions.

### Key Features
* **Launch List:** View all historical and upcoming SpaceX launches, including mission names, dates, rocket names, and success/failure statuses.
* **Launch Detail:** Deep dive into specific launches with comprehensive rocket descriptions.
* **Offline Support:** Browse previously loaded launches even without an active internet connection.
* **External Links:** Watch launch videos or read articles directly via the platform's native web browser.

---

## Architectural Decisions

The application is built heavily relying on **Clean Architecture** principles and the **MVVM (Model-View-ViewModel)** pattern. The primary goal is to achieve maximum code sharing while keeping the modules loosely coupled and highly testable.

### 1. UI Approach (Shared UI)
I chose to implement a **Shared UI using Compose Multiplatform**. By doing so, ~95% of the codebase (including the UI layer) resides in the `commonMain` module. This drastically reduces development time and ensures a consistent user experience across Android and iOS.

### 2. Layered Architecture
The `commonMain` module is strictly divided into the following layers:
* **Domain:** Contains the core business logic (`UseCases`), business models, and repository interfaces. This layer has zero dependencies on external frameworks like Ktor or Room.
* **Data:** Responsible for data operations. It implements the repository interfaces and coordinates between the remote API (`Ktor`) and local cache (`Room`). It also contains DTOs and Mappers.
* **UI:** Contains Compose screens, components, and `ViewModels`.

### 3. State Handling
UI states are managed using **Sealed Classes** (e.g., `LaunchListState`, `LaunchDetailState`). This ensures exhaustive handling of `Loading`, `Success`, and `Error` states, preventing impossible UI states and providing a robust user experience.

---

## Tech Stack & Libraries

The project utilizes modern, industry-standard KMP libraries:

* **Compose Multiplatform:** For declarative, shared UI.
* **Kotlin Coroutines & Flow:** For asynchronous programming and reactive data streams.
* **Koin:** For Dependency Injection. It seamlessly injects ViewModels, UseCases, and Repositories across the KMP boundaries.
* **Ktor:** As the highly performant, multiplatform HTTP networking client.
* **Kotlinx Serialization:** For JSON parsing and serialization.
* **Room Database:** Newly introduced to KMP, used for reliable **Offline Caching**.
* **Coil 3:** For asynchronous image loading in Compose Multiplatform.
* **Navigation Compose:** For type-safe routing between screens.

---

## Platform-Specific Implementations (Expect/Actual)

While most of the code is shared, certain features require native platform APIs. I utilized Kotlin's `expect/actual` mechanism for a clean platform abstraction:

* **Database Builder:** Room requires platform-specific file system access (`Context` for Android, `NSDocumentDirectory` for iOS) to build the SQLite database.
* **Date Formatter:** Parsing and formatting ISO-8601 dates to user-friendly formats using native platform date utilities.
* **Open Browser:** Launching external URLs (YouTube videos, articles) securely using `Intent.ACTION_VIEW` on Android and `UIApplication.sharedApplication.openURL` on iOS.

---

## Project Structure

```text
├── composeApp
│   ├── src
│   │   ├── androidMain        # Android specific implementations (expect/actual)
│   │   ├── iosMain            # iOS specific implementations (expect/actual)
│   │   └── commonMain         # Shared Codebase (95% of the app)
│   │       ├── core
│   │       │   ├── di         # Koin modules
│   │       │   └── util       # Formatters, Platform Abstractions
│   │       ├── data
│   │       │   ├── local      # Room DAO, Database, Entities
│   │       │   ├── mapper     # DTO to Domain Model Mappers
│   │       │   ├── network    # Ktor ApiService
│   │       │   └── repo       # Repository Implementations
│   │       ├── domain
│   │       │   ├── model      # Pure business models
│   │       │   ├── repo       # Repository Interfaces
│   │       │   └── usecase    # Business Logic UseCases
│   │       └── ui
│   │           ├── component  # Reusable Compose widgets
│   │           ├── nav        # Navigation graph
│   │           ├── screen     # Main screens (Home, Detail)
│   │           ├── state      # Sealed classes for UI States
│   │           └── viewmodel  # Shared ViewModels
```

---

## Screenshots

<p align="center">
  <img src="https://github.com/user-attachments/assets/5cf280a7-1a27-47f8-9f1a-2febe440f1b4" width="280" alt="Launch List Screen" />
  <img src="https://github.com/user-attachments/assets/cb19ded0-5836-4bc8-b8cd-0d4cd0210540" width="280" alt="Launch Detail Screen 1" />
  <img src="https://github.com/user-attachments/assets/00c2f41b-8b32-46b9-b84e-71d5c9e82180" width="280" alt="Launch Detail Screen 2" />
</p>
