# Word Hierarchy Analyzer

This repository contains two projects for analyzing word hierarchy.

## CLI

The CLI project is a maven project (made in Java 21 - OpenJDK 21), this program runs in the terminal and receives the following parameters:

syntax: ```java -jar cli.jar analyze –depth <n> –verbose (optional) “{phrase}”```


Analyzes the given phrase and displays a table with the word count at the specified depth level.

Parameters:
  - –depth <n>: Tree depth level for which to display the count
  - “{phrase}” text to be analyzed
  - –verbose: If informed, it should display a table on stdout with the following metrics:
  - Parameter loading time (ms)
  - Phrase verification time (ms)

Example:

comando: java -jar cli.jar analyze --depth 3 "Eu vi gorilas e papagaios"
output: Pássaros = 1; Primatas = 1;

Note:

In the dicts folder there should be a file called data.json, this file will be read by the program to perform the analysis

## Fronted

The frontend project is a web interface using Next.js and TypeScript that allows the user to create a word hierarchy similar to the CLI in Java
