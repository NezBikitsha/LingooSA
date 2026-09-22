Lingo SA

Project Overview

**Lingo SA** is an innovative Android mobile application designed to help South Africans learn the country's official languages and connect with people from different cultural backgrounds. The app combines structured, gamified learning modules with a social platform for real-time practice, creating an ecosystem for authentic language and cultural exchange.

South Africa has 11 official languages, yet many South Africans only speak one or two. Lingo SA aims to bridge this language gap by providing accessible, culturally-relevant language learning and fostering authentic connections between native speakers and learners.

---

Purpose

The primary purpose of Lingo SA is to:

- **Bridge the language gap** by providing accessible, culturally-relevant language learning modules
- **Foster cultural understanding** through authentic connections with native speakers
- **Celebrate diversity** by showcasing the rich linguistic heritage of South Africa
- **Make learning practical** with real-world scenarios like "Ordering at a Shisanyama"

---

Key Features

| Feature | Status | Description |
|-----------|------------|---------------|
**User Authentication** | Complete | Secure registration & login with encrypted passwords (bcrypt) |
|  **User Profile** | Complete | Customisable settings, bio, and language preferences |
|  **Learning Modules** |  Complete | Curated language content with "Learn Local" focus |
|  **REST API Integration** | Complete | Backend API with Django REST Framework |
|  **Password Encryption** | Complete | Passwords hashed using bcrypt on the backend |
|  **Material Design UI** | Complete | Modern, intuitive user interface |
|  **Error Handling** | Complete | Graceful handling of invalid inputs and network errors |
|  **Logging** | Complete | Comprehensive logging throughout the app |
|  **Unit Testing** | Complete | Automated tests with JUnit & MockK |
|  **CI/CD** | Complete | GitHub Actions for automated builds and tests |
|  **Demo Video** | Complete | Full walkthrough of app features |
|  **Offline Mode** | Planned (PoE) | Full sync capability with Room DB |
|  **Notifications** | Planned (PoE) | Real-time push notifications via FCM |
|  **Multi-language** | Planned (PoE) | isiZulu & Afrikaans UI support |
|  **SSO** | Planned (PoE) | Google Sign-In integration |

---

 Technical Architecture

Technology Stack

| Component | Technology | Justification |
|-----------|------------|---------------|
| **Language** | Kotlin | Modern, concise, official Android language |
| **UI Framework** | XML + Material Design | Robust, well-supported UI framework |
| **Architecture** | MVVM + Repository Pattern | Clean separation of concerns, testable |
| **Local Database** | Room (SQLite) | Type-safe database abstraction |
| **Network** | Retrofit + OkHttp | Industry-standard HTTP client |
| **Authentication** | JWT (Django Simple JWT) | Secure, stateless authentication |
| **Backend** | Django REST Framework | Rapid API development with Python |
| **Database** | PostgreSQL | Reliable, scalable relational database |
| **Testing** | JUnit, MockK, Espresso | Comprehensive testing framework |
| **CI/CD** | GitHub Actions | Automated build and test pipeline |

