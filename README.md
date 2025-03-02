# UI Testing with Selenide and Page Object Model

This project demonstrates UI automation using Selenide and the Page Object Model (POM) design pattern on the Swag Labs demo application. Swag Labs is a mock e-commerce site, which provides an ideal environment for learning UI automation and implementing design patterns effectively.

## Tech Stack

- **Programming Language**: Java (JDK 17)
- **Build Tool**: Gradle

## Learning Objectives
This project aims to teach the following concepts:

- Element Selection: How to identify the correct selectors for web elements in UI automation.
- Selenide Test Structure: Understanding how to structure UI tests effectively using Selenide.
- Selenide Configuration: Setting up Selenide to suit your testing needs.
- Page Object Model (POM): Implementing the Page Object Model design pattern for more maintainable and readable UI tests.
- UI Test Writing: How to leverage page objects to write clean and efficient UI tests.

## Prerequisites

- **Selenide**:
Selenide is a framework for automated UI testing that simplifies browser interactions. It abstracts common Selenium functionality, making test development easier and more efficient.

- **Page Object Model (POM)**:
The Page Object Model is a design pattern that promotes the separation of test code and the UI code. Each page of the web application is represented by a separate class, with methods to interact with the page's elements. This makes the tests more maintainable and reusable.

How the Project is Structured
1. Test Setup:
Selenide configuration is set up to allow easy interaction with browser elements.
2. Page Objects:
Each page of the application (e.g., login page, products page) has a corresponding Page Object class.
Page objects encapsulate the UI interactions for each page and provide methods for actions like clicking buttons, entering text, etc.
3. Tests:
Tests are written using page objects, which helps ensure that test code remains clear and easy to maintain.

## Application under test

Demo application called [Swag Labs](https://www.saucedemo.com).

## Additional resources:
- [Selenide Quick Start](https://selenide.org/quick-start.html)
- [Selenide Documentation](https://selenide.org/documentation.html)
- [PageObject by Martin Fowler](https://martinfowler.com/bliki/PageObject.html)
- [Page Object Model (POM) | Design Pattern](https://medium.com/tech-tajawal/page-object-model-pom-design-pattern-f9588630800b)
