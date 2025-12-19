# QA Engineer Take Home Test – Lawencon

## Overview
This project demonstrates API and UI testing skills as part of a QA Engineer take-home assignment.  
It covers API automation using Postman & Newman, and UI automation using Katalon Studio, with a focus on maintainability, clear test design, and proper QA mindset.

---

## Part 1: API Automation – Swagger Petstore

### Scope
API testing for Swagger Petstore, covering both positive and negative scenarios.

### Tools
- Postman
- Newman
- JavaScript assertions

### Test Coverage
- Add new pet
- Find pet by ID
- Find pet by status
- Update pet (JSON body & form-data)
- Upload pet image
- Delete pet
- Negative cases:
  - Invalid request body structure
  - Missing mandatory fields
  - Invalid status parameter
  - Invalid / missing file upload

### Variable & Chaining Strategy
- Environment variables are used for:
  - `baseURL`
  - `petId`
- `petId` is dynamically generated and reused across requests to support request chaining and data consistency.

### Assertions
- HTTP status code validation
- Response body structure validation
- Field value comparison between request and response
- Conditional assertions for error responses

### How to Run (Newman)
```bash
newman run dimas-sulaksono.postman_collection.json \  
-e dimas-sulaksono.postman_environment.json \  
-r htmlextra --reporter-htmlextra-export report.html
-e dimas-sulaksono.postman_environment.json \
-r htmlextra --reporter-htmlextra-export report.html
```

## Part 2: UI Automation – Katalon Studio

### Objective
Validate the core functionality of the Practice Form using UI automation, focusing on mandatory field validation and successful form submission.

### Scope
- Access the Practice Form page
- Fill mandatory fields:
  - First Name
  - Last Name
  - Gender
  - Mobile Number
- Submit the form
- Verify success submission and validation behavior

### Tools & Framework
- Katalon Studio
- Groovy
- Selenium-based WebUI keywords

### Test Scenarios
**Positive**
- Submit form with all mandatory fields filled with valid data

**Negative**
- Submit form with missing mobile number
- Submit form with invalid mobile number
- Submit form without filling mandatory fields

### Mandatory Fields
- First Name
- Last Name
- Gender
- Mobile Number

### Locator Strategy
- Prefer stable attributes such as `id` and `name`
- Avoid dynamic XPath unless necessary
- Centralize all UI element locators in the Object Repository to improve maintainability and reduce duplication

### Project Structure
- **Test Cases**: Contain detailed test steps and validation logic
- **Test Suites**: Group related test cases for execution
- **Object Repository**: Centralized UI element definitions
- **Profiles**: Store environment configuration such as base URL and browser

### Execution
UI automation tests can be executed directly from **Katalon Studio** by running the designated Test Suite.

### Validation & Assertions
- Verify successful submission modal is displayed for valid input
- Verify form is not submitted when mandatory fields are missing or invalid
- Validate error or browser-level validation behavior for negative scenarios

### Notes
The UI automation scope is intentionally limited to critical functional flows to align with the take-home assignment requirements and to demonstrate effective, maintainable test automation practices.

