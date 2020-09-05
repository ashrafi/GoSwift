# GoSwift

Build API
https://quicktype.io/
Call: https://gbfs.fordgobike.com/gbfs/en/system_information.json

api.bike.data
~~~ Bike Database ~~~ Room (Data Access Object)
data.database
  .db StationDB - @Database Version = xxx
  .model - Station.kt - @Entity
  StationDao - @Query
  
~~~ Breathe DB ~~~
db.* 

~~~ Repo ~~~
Repositories

1. List of tasks
- Get Preview Working
- Get Mapds 
- Get Lat / Long on ToDo List 
- Add libs
- Publish
-Done

Questios:
I use DI (Dagger Hilt) to inject my compostables ... I don't see any other examples doing that ???  I am wondering if anyone can think of a reason to DI a composable. 
Is it best practice to leave it at the top level so you get name clashes?
