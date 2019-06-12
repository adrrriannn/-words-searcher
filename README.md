# word-searcher

Java console application that searches given words into a given directory files content.

## Usage

In order to make easier the build and dependencies process Maven has been used. Also, for portability purposes, maven wrapper has been added to the project. To build the project his command needs to be executed in a terminal in the project's root folder:
- Unix
```
  ./mvnw clean package
```

- Windows
```
  ./mvnw.cmd clean package
```

Once the Jar file is built, the next command with one or two(optional) parameters needs to be executed:
```
  java -jar target/word-searcher.jar foo/directory/sample SEARCH_MODE
```
### Parameters
- First argument: directory to search into.
- Second argument: Search mode. Optional parameter. Default value is: **CASE_SENSITIVE**. Values can be:
  - **CASE_SENSITIVE**. Which performs a case sensitive search and whose complexity is O(n).
  - **CASE_INSENSITIVE**. Which performs a case insensitive search whose complexity is O(n^2) in the worst case. This could be improved though by using a binary search, by initially sorting the indexed list of words. This would improve the complexity to O(log n).
  
Note: In order to make performance better parallel streams have been used to index and search concurrently. This leverages Java 8 concurrent new features. Alternatively, threading may be implemented explicitly, which would be absolutely fine. An example of concurrent processing using threads, thread pools, latches and concurrent collections can be found in this repository: [text-analyser](https://github.com/adrrriannn/text-analyser)

Example of usage:

In the project a example(very simple one) directory with three files has been included for testing:

- **example/**

  - file_1
  ```
  Hello world I'm here
  ```

  - file_2
  ```
  Wonderful world
  ```

  - file_3
  ```
  Words are not here
  ```
  
  - CASE_SENSITIVE
  ```sh
  $ java -jar target/word-searcher.jar src/test/resources/example/
  3 files read in directory src/test/resources/example/
  search> Hello World
  file_1 50,00%
  ```
  
  - CASE_INSENSITIVE
  ```sh
  $ java -jar target/word-searcher.jar src/test/resources/example/ CASE_INSENSITIVE
  3 files read in directory src/test/resources/example/
  search> Hello World
  file_1 100,00%
  file_2 50,00%
  ```
  


  
