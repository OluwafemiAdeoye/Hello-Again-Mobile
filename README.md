# Hello-Again-Mobile

## Overview

**Hello-Again-Mobile** is a mobile automation testing project designed using Appium and TestNG. The project is structured to automate the login functionality for a mobile application. It utilizes various utilities and configurations to handle different aspects of mobile testing, such as element interactions, logging, and JSON data handling.

## Project Structure

```
Hello-Again-Mobile/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/
│   │   │       ├── pages/               # Page Object Model classes for different screens
│   │   │       ├── utils/               # Utility classes (ElementUtil, LoggerUtil, etc.)
│   │   └── resources/
│   │       └── log4j2.xml               # Log4j2 configuration file
│   ├── test/
│   │   ├── java/
│   │   │   └── org/
│   │   │       ├── base/                # BaseTest class for setup and teardown
│   │   │       └── tests/               # Test classes (e.g., LoginTest)
│   │   └── resources/
│   │       └── test-data/               # JSON test data files
└── pom.xml                              # Maven configuration file
```

## Prerequisites

Before you begin, ensure you have the following installed on your system:

- **Java JDK 11+**
- **Maven**
- **Appium**
- **Node.js** (for running Appium server)
- **Android SDK** (if testing on Android)
- **Xcode** (if testing on iOS)

## Setup

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/OluwafemiAdeoye/Hello-Again-Mobile.git
   cd Hello-Again-Mobile
   ```

2. **Install Dependencies:**

   Ensure all Maven dependencies are installed by running:

   ```bash
   mvn clean install
   ```

3. **Appium Setup:**

   - Install Appium globally using npm:

     ```bash
     npm install -g appium
     ```

   - Start the Appium server:

     ```bash
     appium
     ```

## Configuration

### Property File (`src/main/resources/config.properties`)

This file contains various configuration settings for the Appium server, device capabilities, and logging. Make sure to adjust these settings according to your environment.

```properties
# General Configuration
platformName=Android
deviceName=Android Emulator

# Appium Server Configuration
appium.ipAddress=127.0.0.1
appium.port=4723
appium.basePath=/wd/hub

# Android Specific Configuration
android.appPackage=com.android.vending
android.appActivity=com.google.android.finsky.unauthenticated.activity.UnauthenticatedMainActivity

# iOS Specific Configuration
ios.bundleId=com.example.iosApp
ios.platformVersion=14.4
ios.deviceName=iPhone Simulator

# Logging Configuration
log.level=info
log.file.path=logs/app.log
log.pattern=%d{ISO8601} [%t] %-5p %c - %m%n
```

### Log4j Configuration (`src/main/resources/log4j2.xml`)

This file is used to configure logging behavior across the project. The logging settings are defined in `config.properties` and referenced here.

## Running the Tests

1. **Run All Tests:**

   To execute all tests in the project:

   ```bash
   mvn test
   ```

2. **Specific Test:**

   To run a specific test class:

   ```bash
   mvn -Dtest=LoginTest test
   ```

## Troubleshooting

- Ensure the Appium server is running and accessible via the configured IP and port.
- Make sure that the Android or iOS emulator/device is properly configured and running.

## Contributing

If you wish to contribute to this project, please fork the repository and submit a pull request. Any contributions are welcome!

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
