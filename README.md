| CS-665       | Software Design & Patterns |
| ------------ | -------------------------- |
| Name         | Dingnan Hsu                |
| Date         | 09/21/2023                 |
| Course       | 2023 Fall                  |
| Assignment # | 1                          |

# Assignment Overview

The system starts with a user welcome and loads beverage and condiment data from CSV files, handling errors. Users interact with the vending machine through an intuitive interface.

# GitHub Repository Link:

https://github.com/dddingnan/cs-665-assignment-1

# Implementation Description

For each assignment, please answer the following:

- Explain the level of flexibility in your implementation, including how new object types can
  be easily added or removed in the future.
- Discuss the simplicity and understandability of your implementation, ensuring that it is
  easy for others to read and maintain.
- Describe how you have avoided duplicated code and why it is important.
- If applicable, mention any design patterns you have used and explain why they were
  chosen.

### Task

---

- 1.  Refactoring the Main class could involve several steps to improve clarity, maintainability, and potentially performance. Here are some steps:

Extract Method: The main method does a lot. You could extract methods to handle specific parts of the initialization process. For example, loading beverages and condiments could be extracted into their own methods.

Use Meaningful Variable Names: The names sugar and milk for lists of condiments could be more descriptive. They should reflect that they are collections of items, like sugars and milks, or even more descriptively, sugarOptions and milkOptions.

Handle Exceptions More Gracefully: Instead of throwing InvalidDataException from the main method, you could catch it and display an error message, possibly instructing the user on how to rectify the issue.

Remove Redundant Initialization: The lists are initialized twice: once with new ArrayList<>() and again with the results from loader. The initial empty list creation is unnecessary.

Improve Resource Management: Ensure that any resources (like file streams opened in FileLoader) are properly closed, potentially using try-with-resources statements.

Separate Concerns: Main is doing a bit of everything - consider whether some responsibilities could be moved to other classes to adhere to the Single Responsibility Principle.

Improve User Feedback: When starting up, the system could give more feedback about what's happening, such as "Loading beverages...", "Loading condiments...", etc.

---

- 2.

Refactoring the FileLoader class can improve its clarity, robustness, and maintainability. Here are some suggestions for refactoring this class:

Use Constants for Fixed Values: The line and splitBy variables seem to be constants, but they're not declared as such. You should declare them as private static final.

Remove Public Modifiers from Internal Variables: The line and splitBy variables should be private if they're not intended for use outside the class.

Use Try-With-Resources for Better Resource Management: You're already doing this, which is good practice for managing resources like file streams.

Extract Common Code: The code for reading from the CSV file and splitting lines is repeated in both loadBeverageFile and loadCondimentFile. This could be refactored into a common method.

Centralize Error Handling: The error handling could be refactored to reduce redundancy and possibly to centralize logging or error reporting.

Improve Feedback Mechanism: Rather than printing to the console, consider using a logging framework or throwing exceptions that can be logged/handled by the caller.

Enhance File Path Handling: Use Path and Files.newBufferedReader instead of FileReader for better path handling and charset management.

---

- 3.

Refactoring the UserInterface class should improve its structure, readability, and maintainability, as well as potentially simplify the control flow. Here are some suggestions:

Encapsulate Scanner: The Scanner is a resource that should be managed properly. You could encapsulate its usage in a method that also handles closing it when done.

Refactor Large Methods: The start() method is quite lengthy and handles multiple responsibilities. Breaking it down into smaller methods would improve readability and maintainability.

Simplify Validation: The input validation logic is repeated multiple times. This could be extracted into separate methods to reduce duplication.

Use Enums for Choices: Instead of using strings for user choices, consider using an enum type for clarity and to avoid typos.

Improve Exception Handling: Throwing exceptions for user input errors might be overkill and can be replaced with simpler control flow logic.

Externalize User Interaction: Consider creating separate methods or classes for user interactions to make the UserInterface class more focused on processing those interactions rather than handling them directly.

Refactor Total Price Calculation: The current approach calculates and adds prices in multiple places. Centralize this to ensure consistency and reduce errors.

Improve Loop Control: The while (true) loop makes it hard to understand the termination condition. Refactoring this to a more explicit control structure could improve understanding.

---

### `Answer`

1. `Flexibility`
   - `Generic Objects`: Beverage and Condiment objects are designed to be versatile. Adding new types of beverages or condiments is as easy as creating new instances of these objects or updating CSV files.
   - `CSV Data Storage`: Using CSV files for data storage makes it simple to add, modify, or remove beverages and condiments without changing the core code.
2. `Simplicity & Understandability`
   - The main interaction point, the UserInterface, lead the user through the system, just like the logical flow that expect from an actual vending machine.
3. `Avoidance of Duplicated Code`
   - By separating classes for different functionalities. For instance, FileLoader takes care of all file-loading operations, ensuring that there's no repetition of file reading logic.
   - The use of generic classes like Beverage and Condiment prevents the need to create separate classes for each type of beverage or condiment, minimizing redundancy.
   - Avoiding duplicated code is essential for maintaining the application efficiently. When changes or bug fixes are required, you'll only have to make them in one place, reducing the risk of errors and inconsistencies.
4. `Design patterns`
   - `Factory Pattern`: The FileLoader class abstracts away the details of object creation from CSV files, providing an easy and consistent way to generate data objects. [This is to deal with the problem of creating objects without having to specify the exact class of the object that will be created.](https://en.wikipedia.org/wiki/Factory_method_pattern)

## UML Diagram

![UML Diagram](UML.svg)

# Maven Commands

We'll use Apache Maven to compile and run this project. You'll need to install Apache Maven (https://maven.apache.org/) on your system.

Apache Maven is a build automation tool and a project management tool for Java-based projects. Maven provides a standardized way to build, package, and deploy Java applications.

## Compile

Type on the command line:

```bash
mvn clean compile
```

## Run

Type on the command line:

```bash
mvn exec:java
```

## JUnit Tests

JUnit is a popular testing framework for Java. JUnit tests are automated tests that are written to verify that the behavior of a piece of code is as expected.

In JUnit, tests are written as methods within a test class. Each test method tests a specific aspect of the code and is annotated with the @Test annotation. JUnit provides a range of assertions that can be used to verify the behavior of the code being tested.

To run, use the following command:

```bash
mvn clean test
```

## Spotbugs

SpotBugs is a static code analysis tool for Java that detects potential bugs in your code. It is an open-source tool that can be used as a standalone application or integrated into development tools such as Eclipse, IntelliJ, and Gradle.

SpotBugs performs an analysis of the bytecode generated from your Java source code and reports on any potential problems or issues that it finds. This includes things like null pointer exceptions, resource leaks, misused collections, and other common bugs.

Use the following command:

```bash
mvn spotbugs:gui
```

For more info see
https://spotbugs.readthedocs.io/en/latest/maven.html

SpotBugs https://spotbugs.github.io/ is the spiritual successor of FindBugs.

## Checkstyle

Checkstyle is a development tool for checking Java source code against a set of coding standards. It is an open-source tool that can be integrated into various integrated development environments (IDEs), such as Eclipse and IntelliJ, as well as build tools like Maven and Gradle.

Checkstyle performs static code analysis, which means it examines the source code without executing it, and reports on any issues or violations of the coding standards defined in its configuration. This includes issues like code style, code indentation, naming conventions, code structure, and many others.

The following command will generate a report in HTML format that you can open in a web browser.

```bash
mvn checkstyle:checkstyle
```

The HTML page will be found at the following location:
`target/site/checkstyle.html`
