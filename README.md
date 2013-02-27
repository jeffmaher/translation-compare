# Translation Compare

Compares translation key files like PO files or .properties files (Resource Bundle or i18n).

## Dev Setup

You must have Java 7 and Apache Maven 3.x

## Building

mvn clean package

This places an exectuable JAR in the *target* directory (ex: translation-compare-1.0-SNAPSHOT.jar).

## Running

The files being compared are referred to as *A* and *B*.

1. Double click translation-compare.jar (the executable JAR built in *Building* section)
2. Enter or Browse to a path for File *A*
3. Enter or Browse to a path for File *B*
4. Click Compare

## Tabs
* Unique Keys (A) – Keys that appear in *A*, but not in *B*
* Unique Keys (B) – Keys that appear in *B*, but not in *A*
* Common Key Differences – Shows differences between the values for keys that appear both in *A* and *B*. If a common key has the same value in *A* and *B*, it is not displayed in the table.