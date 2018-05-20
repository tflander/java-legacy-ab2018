# java-legacy-ab2018

This is sample code for a refactoring exercise.

Forked from neopragma on github, as given in an Agile and Beyond workshop in Michigan in 2018.

## Baby Step Refactor:
Assume we have a story that has something to do with the City, and we want a pristine starting point for the new story that doesn't involve fixing every code smell.
This starting point should involve extending a clean class extracted from JobApplicant.  
As we add functionality going forward, we want JobApplicant to get simpler, not more complex.

## Full Code Rescue:
Continue the baby step refactor.  Assume we want to release version 2.0 of JobApplicant, but we are not allowed to break the original contracts for Job Applicant's public methods.  This includes the Spanish parameter names.
Version 2.0 will be open-sourced, and we want to impress the developer community with our high coding standards. 

## Pristine Code Release:
Starting from the Full Code Rescue, release version 3.0 with all of the deprecated methods removed.
The idea is to encourage new customers to user version 3.0, and encourage existing customers to migrate to version 2.0, which is
full backward-compatible with the original code.

## Hints
- Avoid spending too much time on the code that does the city / state lookup on a zip code.  This code is fundamentally flawed and needs to be replaced (e.g. Zip Code 48473).  There is no need to dress the prisoner in a suit before strapping him to the electric chair.
- Run tests often.
- When all tests are green, think about cleaning them as well as cleaning the code.
- Refactor in small chunks, then commit to git when tests are green.
- Run the main program often to make sure nothing is broken that was not covered in a test.
- Run code coverage to see what code isn't tested.
- Don't trust code coverage.  Code that is green MIGHT be tested.  Review the tests to make sure they are honest.

