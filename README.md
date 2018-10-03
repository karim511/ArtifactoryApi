# ArtifactoryApi for rundeck

Change the configuration in artifactory.properties <br>
Generate war file and put him in /artifactory-oss-5.10.3/tomcat/webapps folder

<h4>url parameters</h4>
<b>repo</b>=rep1,rep2,....<br />
<b>fileNames</b>=ear,*.txt  // any type you looking for

<h4>In Artifactory</h4>
Security-> Security Configuration
uncheck  Allow Anonymous Access


<h4>Example</h4>
http://localhost:8081/ArtifactoryApi/api?repo=libs-release-local,libs-snapshot-local&fileNames=*.ear



# Result
```javascript

[
  {
    "name": "com.ping.ear-0.0.3-SNAPSHOT.ear - 25/04/2018 [11:10]",
    "value": "libs-snapshot-local/com/ping/back/com.ping.ear/0.0.3-SNAPSHOT/com.ping.ear-0.0.3-SNAPSHOT.ear"
  },
  {
    "name": "com.ping.ear-0.0.2.ear - 25/04/2018 [10:44]",
    "value": "libs-release-local/com/ping/back/com.ping.ear/0.0.2/com.ping.ear-0.0.2.ear"
  },
  {
    "name": "com.ping.ear-0.0.2-SNAPSHOT.ear - 23/04/2018 [21:46]",
    "value": "libs-snapshot-local/com/ping/back/com.ping.ear/0.0.2-SNAPSHOT/com.ping.ear-0.0.2-SNAPSHOT.ear"
  },
  {
    "name": "com.ping.ear-0.0.1.ear - 22/04/2018 [13:26]",
    "value": "libs-release-local/com/ping/back/com.ping.ear/0.0.1/com.ping.ear-0.0.1.ear"
  },
  {
    "name": "com.ping.ear-0.0.1-SNAPSHOT.ear - 21/04/2018 [16:49]",
    "value": "libs-snapshot-local/com/ping/back/com.ping.ear/0.0.1-SNAPSHOT/com.ping.ear-0.0.1-SNAPSHOT.ear"
  }
]
```
