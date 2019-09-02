# Challenge Credit Suisse

This challenge is a generic Java programming task.

# Execution

### HSQLDB

> **Download** the last version [HSQLDB](http://hsqldb.org/)

> **Run** the command java -cp hsqldb.jar org.hsqldb.server.Server --database.0 file:challengedb --dbname.0 challengedb

After hsqldb configuration you just need to run the program and the Watch Service will be monitoring the folder that is configured in application.properties.

The table will be created. 

Then, you need to update the logfile.txt. The program will read, process and write the information on database.



## application.properties

DATASOURCE_DRIVER_CLASS_NAME= Data source driver class name

DATASOURCE_URL= Data source URL

DATASOURCE_USERNAME= Data source username

DATASOURCE_PASSWORD= Data source password

FILE_WATCHER_PATH= Path of the folder to be monitored by the watch service 

LOG_FILE_PATH= Path of log file to be processed
