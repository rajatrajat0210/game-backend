GAME SERVICE BACKEND
=====================

A Spring Boot-based backend system to manage players, scores, progression, and scheduled game events.  
It uses PostgreSQL as the database and includes APIs to register players, submit scores, track progression, and manage events.

-----------------------------------------------------------
DELIVERABLES
-----------------------------------------------------------
✔ Working JAR file: located at /build/libs/game-service.jar  
✔ SQL file to create and seed the local DB: game_service_full.sql   
✔ GitHub repo: https://github.com/rajatrajat0210/game-backend

-----------------------------------------------------------
TECHNOLOGIES USED
-----------------------------------------------------------
- Java 17  
- Spring Boot  
- PostgreSQL  
- Spring Data JPA  
- Spring Cache with Caffeine
- Gradle  

-----------------------------------------------------------
SETUP INSTRUCTIONS
-----------------------------------------------------------

1. CLONE THE REPOSITORY:
---------------------------------
> git clone  https://github.com/rajatrajat0210/game-backend
> cd game-backend

2. CREATE THE DATABASE:
---------------------------------
Open a PostgreSQL terminal and run:

> CREATE DATABASE game_service;

Then run the SQL dump file:

> psql -U your_db_user -d game_service -f game_service_full.sql

(Replace "your_db_user" with your PostgreSQL username.)

3. CONFIGURE SPRING:
---------------------------------
Open `src/main/resources/application.properties` and configure:

spring.datasource.url=jdbc:postgresql://localhost:5432/game_service  
spring.datasource.username=your_db_user  
spring.datasource.password=your_password  
spring.jpa.hibernate.ddl-auto=none  

4. BUILD AND RUN:
---------------------------------
> ./gradlew build  
> java -jar build/libs/game-backend-0.0.1-SNAPSHOT.jar 

App will run at: http://localhost:8080

-----------------------------------------------------------
API ENDPOINTS
-----------------------------------------------------------

PLAYER ENDPOINTS:
---------------------------------
[POST] /players/register         
→ Register a new player  

[GET]  /players                  
→ Get all registered players  

[GET]  /players/{playerId} 
→ Get a player by ID

SCORE ENDPOINTS:
---------------------------------
[POST] /scores/submit/{playerId}                          
→ Submit score for a player  

[GET]  /scores/top/global?limit=X                         
→ Top X scores globally  

[GET]  /scores/top/game/{gameId}                          
→ Top scores for a specific game  

[GET]  /scores/top/game/{gameId}/country/{country}        
→ Top scores by game and country  

(Note: Country is auto-fetched from the player's profile.)

PROGRESSION ENDPOINTS:
---------------------------------
[POST] /progressions/save/{playerId}                      
→ Save  or Update progression for a player  

[PUT]  /progression/update/{playerId}                     
→ Update a player’s level, XP, or game  

[GET]  /progressions/{playerId}                            
→ Fetch a player’s current progression  

[GET]  /progressions                 
→ View all Progressions related to player id's

GAME EVENT ENDPOINTS:
---------------------------------
[POST] /events/schedule        
→ Schedule a new game event  

[PUT]  /events/update/{id}     
→ Update existing event  

[GET]  /events/available       
→ List of currently active events (based on time window)

(Event is valid if: startTime ≤ NOW ≤ endTime)

-----------------------------------------------------------
NOTES
-----------------------------------------------------------

📌 Country for scores is auto-populated from player profile — do not pass it manually.

📌 Use the included SQL file to quickly recreate the database.

📌 Clear data if Needed !

-----------------------------------------------------------
AUTHOR
-----------------------------------------------------------

Rajat  

