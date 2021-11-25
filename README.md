# CurrencyConversion

1) Please download and extract

2) Go to the folder \CurrencyConversion using cmd.exe

3) Execute the command - # mvn test clean     

3A) execution will starts in chrome

4) Execution Reports in form of Extent HTML report in the folder - \CurrencyConversion\TestReport

5) Also, Allure report available in the folder - \CurrencyConversion\allure-results

5A) To View Allure HTML Report - Open the \CurrencyConversion in cmd.exe

5B) And, execute the cmd allure serve

6) Note, CurrencyConversion\src\main\resources contains below specs
    * InputDataSets - Holds test data for the execution
    * chromedriver.exe - selenium chrome driver
    * config.properties - Holds url, browser, timeout parameters
    * Log4j - Sysout is being formatted
    * testng.xml - hold the test suite, listeners parameters
8) In default, verifyCurrencyConversionInputDataSet.xlsx is referred to execute verification of currency conversion

# Prereqs
1) TestNG - https://testng.org/doc/eclipse.html

2) Maven - https://maven.apache.org/download.cgi

3) Allure - https://docs.qameta.io/allure/#_installing_a_commandline

4) Please make sure \CurrencyConversion folder is not renamed while download or during the extraction. Otherwise, compilation will be failed
