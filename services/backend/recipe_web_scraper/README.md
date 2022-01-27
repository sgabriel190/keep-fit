# Recipe web scraper script

Recipe web scraper script implements the web scraping logic.

It contains 3 phases:
* First it fetches recipe URLs from a pool of main URLs. Those recipe URLs are saved in an intermediary file. For this operation is used Selenium package.
> NOTE: Selenium is based on the look of the website as this is developed in a specific time, the websites may change its appearance and may brake the script!
* After getting those recipe URLs, the script executes a multiprocess phase, spawning 50 subprocesses that use recipe_scrape package and extract all the information about the recipe. This information is also saved in an intermediary file.
* Filtering the data and modeling it according to the needs of the database. The images are stored locally for a better data management.

All these operations result in ~4000 recipes to be used. The total disk usage for this information is ~1GB.

## Requirements

The requirement packages are listed in requirements.txt. Those using:
```
    pip3 install -r requirements.txt
```
