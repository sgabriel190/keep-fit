# Database scripts

Database scripts contains scripts that need to be run in order to create the necessary databases.

Logging the execution is done by a Singleton class implemented in _Logger.py_.

The resulting database files are _nutrition.db_, _plan.db_ and _user.db_. Those are populated with data from hardcoded source or JSON data sources. The recipes' information are represented in a JSON file which was ignored in the .gitingore file. It can be obtained from executing Web scraping scripts.

##Requirements

For this script execution you need to have installed:
* __Python3__

> Consider a virtual environment 

Necessary Python3 packages:
* __sqlite__