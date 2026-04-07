# REST Assured API Automation Framework

A robust API test automation framework built with Java and REST Assured, covering full CRUD operations, auth token flows, schema validation, and CI/CD integration via Jenkins.

---

## Tech Stack

| Layer | Tools |
| Language | Java (JDK 22+) |
| API Automation | REST Assured |
| Test Framework | TestNG |
| Build Tool | Maven |
| Assertions | AssertJ |
| Serialization | Jackson API, GSON |
| Test Data | Apache POI (Excel) |
| Logging | Log4j |
| Reporting | Allure Reports |
| CI/CD | Jenkins |

---

## Framework Architecture

```
src/
├── main/java/
│   ├── api/            # Reusable request builders (BookingApi, AuthApi)
│   ├── models/         # POJO classes for request/response (BookingPojo, TokenPojo)
│   ├── utils/          # ConfigReader, ExcelUtils, JsonUtils
│   └── base/           # BaseTest — base URI, request spec setup
└── test/java/
    └── tests/          # TestNG test classes (CRUD, Integration, Auth)
```

---

## Key Features

- Full CRUD coverage — Create, Read, Update, Delete on the [Restful Booker API](https://restful-booker.herokuapp.com)
- Auth token flows — Token creation, injection into headers, and expiry handling
- Schema validation — JSON response schema validated using JSON Schema files
- AssertJ assertions — Fluent, readable assertions on status codes, response bodies, and headers
- POJO-based serialization — Jackson API for clean request/response deserialization
- Data-driven tests — TestNG `@DataProvider` with Excel input via Apache POI
- Integration scenarios — End-to-end flows: Create → Update → Verify → Delete
- Allure Reports — Step-level HTML reports with request/response logging
- Jenkins CI/CD — Jenkinsfile for automated regression on every build

---

## API Coverage

| Endpoint | Method | Test Scenario |
| /auth | POST | Generate auth token |
| /booking | POST | Create booking |
| /booking/{id} | GET | Get booking by ID |
| /booking/{id} | PUT | Full update with auth |
| /booking/{id} | PATCH | Partial update with auth |
| /booking/{id} | DELETE | Delete booking with auth |

---

## Getting Started

### Prerequisites

- Java 22+
- Maven
- Allure CLI (`brew install allure` or see [Allure docs](https://docs.qameta.io/allure/))

### Clone & Install

bash
git clone https://github.com/vinayrk77/APIAutomationFrameworkVinay.git
cd APIAutomationFrameworkVinay
mvn clean install -DskipTests


### Run Tests

bash
# Full CRUD suite
mvn clean test -DsuiteXmlFile=testng.xml

# Integration scenarios (Create → Update → Delete)
mvn clean test -DsuiteXmlFile=testng-integration.xml

# End-to-end e2e suite
mvn clean test -DsuiteXmlFile=testing_e2e1.xml

### Generate Allure Report

bash
allure serve allure-results/

---

## Integration Test Flow

The integration suite tests real end-to-end booking scenarios:

1. POST /auth          → Get token
2. POST /booking       → Create booking, capture ID
3. PUT  /booking/{id}  → Update name/dates with token
4. GET  /booking/{id}  → Assert updates persisted
5. DELETE /booking/{id}→ Delete with token
6. GET  /booking/{id}  → Assert 404 (booking gone)

---

## CI/CD with Jenkins

The Jenkinsfile defines a pipeline that:

1. Pulls the latest code from GitHub
2. Runs mvn clean test against the full suite
3. Publishes Allure Reports as a build artifact
4. Marks build as failed if any test fails

bash
# Trigger manually
mvn clean test -DsuiteXmlFile=testng.xml


## Parallel Execution

Configure parallel execution in testng.xml:

xml
<suite name="API Suite" parallel="methods" thread-count="3">


---

## Author

**Vinay Karanjavkar** — QA Analyst | Automation Engineer
- [LinkedIn](https://www.linkedin.com/in/vinaykaranjavkar/)
- GitHub(https://github.com/vinayrk77)
- vinayrk26@gmail.com