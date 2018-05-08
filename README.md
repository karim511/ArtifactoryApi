# ArtifactoryApi for rundeck

generate war file and put him in /artifactory-oss-5.10.3/tomcat/webapps folder

# url parameters
repo=rep1,rep2,....
fileNames=ear,*.txt  // any type you looking for

# Service example
http://localhost:8081/ArtifactoryApi/api?repo=libs-release-local,libs-snapshot-local&fileNames=*.ear

# Result
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
