## Getting started

I highly recommend you use IntelliJ. You can get the ultimate edition for free with your student email. It supports Spring and Maven infinitely better than Eclispe does.

Requires Java 21. I recommend Amazon Corretto's distribution found [here](https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html), however most distributions should work.

Requires MySQL 8. Easiest way to setup local database is to install MySQL Workbench [here](https://dev.mysql.com/downloads/workbench/). If you installed MySQL for CS 308 then you're probably good. If not, this guide [here](https://docs.appspace.com/latest/how-to/setup-mysql-with-mysql-workbench/#:~:text=Launch%20the%20MySQL%20Workbench%20from,password%20in%20vault%20check%20box.) seems to cover everything you'll need.

Once you get this far, add the application-dev.properties file posted in Discord to `src/main/resources` and fill in the DB url, username, and password to match your local setup.

## Running locally

If using IntelliJ, you can use it's built in Maven plugin to install dependencies.

To start locally, run the main method within `ServerApplication.java`
