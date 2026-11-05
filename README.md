Lingo SA

Project Overview

**Lingo SA** is an innovative Android mobile application designed to help South Africans learn the country's official languages and connect with people from different cultural backgrounds. The app combines structured, gamified learning modules with a social platform for real-time practice, creating an ecosystem for authentic language and cultural exchange.

South Africa has 11 official languages, yet many South Africans only speak one or two. Lingo SA aims to bridge this language gap by providing accessible, culturally-relevant language learning and fostering authentic connections between native speakers and learners.

---

Purpose

1. Purpose of the App
Lingo SA is a mobile application designed to bridge the gap between language learning and social connection, specifically tailored to the diverse South African context. The app aims to help South Africans learn the country's official languages (isiZulu and Afrikaans) and connect with people from different cultural backgrounds. It combines structured, gamified learning modules with a social platform for real-time practice, creating an ecosystem for authentic language and cultural exchange.

2. Design Considerations & Architecture
Architecture: The app strictly follows the MVVM (Model-View-ViewModel) architecture to ensure a clean separation of UI and business logic.

Local Storage: Room Database is used for caching data and handling offline capabilities.

Network: Retrofit is used to connect to a custom-hosted Django REST API.

UI/UX: Built using Material Design 3. The app features robust input validation (via TextInputLayout) to handle invalid user inputs gracefully without crashing, utilizing Snackbars for user feedback.

Security: User authentication is secure. Passwords are hashed on the backend (Django) using bcrypt, and the app utilizes JSON Web Tokens (JWT) for secure API communication over HTTPS.

3. Utilisation of GitHub and GitHub Actions
GitHub: The repository was initialized with this README.md file. Regular commits were made throughout the development lifecycle to track progress and maintain version control.

GitHub Actions: A CI/CD pipeline was implemented via a build.yml file located in .github/workflows/. This action automatically triggers on every push to the main branch. It sets up the Java environment, builds the Kotlin application, and runs the automated unit tests to ensure the code is stable and functional on environments other than my local machine.

4. Innovative Features (Added in Final Version)
As per the Part 1 planning, the following features have been successfully integrated into the final version:

Learn Local Modules: Culturally relevant lessons (e.g., "Ordering at a Shisanyama").

CultureConnect Badges: Gamified achievements for engaging with cultural content.

5. Release Notes (v2.0 - Final POE)
This section details all updates made since the initial Part 2 prototype.

New PoE Features Implemented:

Single Sign-On (SSO): Integrated Google Sign-In via Firebase Authentication for frictionless onboarding.

Offline Mode with Sync: Users can now complete lessons while offline. Actions are saved to RoomDB and automatically synced to the Django REST API via WorkManager when the device reconnects to the internet.

Real-time Notifications: Integrated Firebase Cloud Messaging (FCM) to send push notifications to users for real-time updates and alerts.

Multi-language Support: Full localization of the UI for English, isiZulu (
zu
), and Afrikaans (
af
). The app automatically adjusts to the device's language settings.

Improvements & Fixes:

App Icon & Assets: Added a custom adaptive app icon (dialogue bubble forming the SA map) and final UI image assets.

Error Handling: Fixed crashes related to invalid inputs on the Registration and Login screens.

Logging & Comments: Added comprehensive Log.d() statements throughout the codebase for debugging and added inline comments explaining complex logic (e.g., the SyncWorker).|
| **Database** | PostgreSQL | Reliable, scalable relational database |
| **Testing** | JUnit, MockK, Espresso | Comprehensive testing framework |
| **CI/CD** | GitHub Actions | Automated build and test pipeline |

