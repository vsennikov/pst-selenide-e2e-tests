# Test Automation Framework for Practice Software Testing

This project is a demonstration E2E (End-to-End) framework for test automation of the [Practice Software Testing](https://practicesoftwaretesting.com/) website.

The goal of the project is to demonstrate the skills required to build a scalable and maintainable test framework architecture using modern technologies and best practices in automation.

## 🚀 Technology Stack

| Technology         | Purpose                                                 |
| :----------------- | :------------------------------------------------------ |
| **Java 17** | Primary programming language                            |
| **Selenide** | A framework for concise and stable web UI interaction     |
| **Maven** | Build automation tool and dependency management         |
| **JUnit 5** | Test runner for executing tests and managing their lifecycle |
| **Allure Framework** | Generation of detailed and interactive test execution reports |
| **JavaFaker** | Generation of realistic test data                       |
| **Owner** | Convenient management of configuration files (.properties) |
| **AssertJ** | Library for writing fluent assertions                   |


## 🧪 Implemented Test Scenarios

This project automates the following business scenarios:

- [ ] **New User Registration:**
    - Successful registration with valid data.
    - Verification of form field validation.
- [ ] **Authentication:**
    - Successful login for an existing user.
    - Data-Driven tests for login verification with various datasets (e.g., invalid password, locked-out user).
- [ ] **Product Catalog Interaction:**
    - Sorting products by price.
    - Filtering products by category/brand.
- [ ] **Full Product Purchase Cycle (E2E):**
    1. User login.
    2. Adding a product to the cart.
    3. Navigating to the cart and verifying its contents.
    4. Proceeding to checkout (filling out the shipping form).
    5. Verifying the successful order confirmation message.

## ⚙️ How to Run Tests
### Prerequisites
* Java 17
* Apache Maven

### Execution Commands
1. Clone the repository to your local machine:
```bash
   git clone git@github.com:vsennikov/pst-selenide-e2e-tests.git
```

2. Navigate to the project directory:
```bash
cd pst-selenide-e2e-tests
```

3. Run the tests using Maven:
```bash
	mvn clean test
```

### 📊 Allure Report
To generate and view the detailed test execution report, follow these steps.

The Allure results are generated during the test run (this happens automatically with the mvn clean test command).
Serve the generated report to open it in a browser:
```bash
mvn allure:serve
```




