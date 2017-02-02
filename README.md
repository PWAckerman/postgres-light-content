# postgres-light-content
A lightweight Swing-based client for PostgreSQL.
## Purpose
I built this as an exploratory project to learn Java Swing (even if it is considered out-dated) and how to work with the 
JDBC, as well as implement a classic MVC pattern without the aid of an opinionated framework.
Styling and features are meant to roughly mimic those of Jet Brains DataGrip at a very
basic level.

## Current Features
* Connects to any PostgreSQL DB given an address (location + port), DB name and credentials.
* Loads and displays a tree of Schemas/Tables from the connected DB.
* Can dynamically add tabs to table display, and navigate between tables.
* Can update cells and persist changes to DB in most instances

## Bugs
I plan to gradually fix these bugs when I have time.
* Duplicate tables can be opened at one time
* May be some bugs in the data persistence logic (more testing needed)
* Tabs are not closeable