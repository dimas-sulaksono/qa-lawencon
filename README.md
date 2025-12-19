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
Automate UI testing for **DemoQA Automation Practice Form** using Katalon Studio with a focus on:
- Page Object Model (POM)
- Data Driven Test (internal Katalon Data Files)
- Clear and conditional assertions for positive and negative scenarios

Target URL:  
https://demoqa.com/automation-practice-form

---

### Tools & Environment
- **Katalon Studio**: 8.6.8  
- **Browser**: Google Chrome  
- **Language**: Groovy  
- **Approach**: WebUI + POM + Data Binding  

---

### Mandatory Fields
Based on the form specification:
- First Name
- Last Name
- Gender
- Mobile Number

Additional field used for negative testing:
- Email

---

### Test Design
UI automation is implemented as **one test case executed using Data Driven Test**.  
Each row in the test data represents one scenario.

Total scenarios executed: **8**
- 1 Positive
- 7 Negative

Test data source:
- `Data Files/TD_PracticeForm`

---

### Covered Scenarios
**Positive**
- Submit form with all mandatory fields filled using valid data

**Negative**
- Mobile number is empty
- Mobile number less than 10 digits
- First name is empty
- Last name is empty
- Gender is not selected
- Email format is invalid
- Submit form without filling any mandatory field

---

### Project Structure
- **Test Case**  
  `Test Cases/TC_PracticeForm`

- **Test Suite**  
  `Test Suites/TS_PracticeForm`  
  (Data Binding: `TD_PracticeForm`, Iteration: All)

- **Object Repository**  
  `Object Repository/PracticeForm/`  
  - txt_FirstName  
  - txt_LastName  
  - lbl_Gender_Male / Female / Other  
  - txt_Email  
  - txt_Mobile  
  - btn_Submit  
  - txt_ModalTitle  

- **Page Object Model**  
  `Keywords/pages/PracticeFormPage.groovy`

- **Utilities**  
  `Keywords/utils/TestData.groovy`

---

### Data Driven Implementation
The Test Suite binds variables in `TC_PracticeForm` to columns in `TD_PracticeForm`.

Main columns used:
- `firstName`
- `lastName`
- `gender`
- `email`
- `mobile`
- `expectSuccess`
- `expectMobileInvalid`
- `expectEmailInvalid`

Each iteration runs the same test logic with different input data.

---

### Assertion Strategy
#### Positive Scenario (`expectSuccess = true`)
- Verify success modal is visible
- Verify submitted data (Name, Gender, Mobile) matches input

Implemented via:
- `verifySuccessModalVisible()`
- `verifySubmittedData()`

#### Negative Scenario (`expectSuccess = false`)
- Verify success modal does **not** appear

Conditional assertions:
- **Mobile validation**  
  Executed only when `expectMobileInvalid = true`  
  Uses HTML5 validation via:
  ```groovy
  checkValidity() == false
  ```
- **Email validation**
  Executed only when expectEmailInvalid = true
  Uses regex-based format validation:
  ```groovy
  TestDataUtil.isValidEmailFormat(email) == false
  ```
  This approach avoids false failures caused by inconsistent browser validation behavior on the DemoQA site.

---

### Execution
1. Open the project in Katalon Studio
2. Open Test Suites/TS_PracticeForm
3. Ensure TD_PracticeForm is correctly bound
4. Run the Test Suite

---

### Reports
After execution, reports are generated automatically under:
```
Reports/<timestamp>/TS_PracticeForm/<timestamp>/
```
Default Katalon outputs:
- HTML report
- XML (JUnit-style)

---

### Notes
- DemoQA relies on HTML5 validation (no explicit error messages).
- Validation is verified using browser-level checks (checkValidity) and utility-based format validation.
- This approach ensures stable automation while maintaining correct QA validation coverage.
