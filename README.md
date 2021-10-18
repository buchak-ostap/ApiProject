# ApiProject
#To run tests need to install: 
- JDK 11+, 
- Maven
- Allure

#To generate Allure report need to install: 
- Allure (https://docs.qameta.io/allure/)
- run next command: allure serve ${path to allure-results folder}  (allure serve /Users/ostapbuchak/Desktop/AQA_Middle/API_Project/ApiProject/allure-results)

#To run Smoke tests need to run next command:
- mvn -Dtests=smoke test

#To run Functional tests need to run next command:
- mvn -Dtests=functional test