# LVL UP API

## Installation
Simply clone the repo, open the directory in a terminal and run ```gradle build``` to build the project, then  
```java -jar /build/libs/lvlup.jar``` to start the application.

## Usage

An easy way to try out the API is by using [Postman](https://www.getpostman.com/).

By default the server starts on port 8080.  
To access the H2 console, go to **localhost:8080/h2-console** [(more info)](http://www.h2database.com/html/quickstart.html).

All request and response bodies are handled in JSON.

## Routes

<dl>
<dt>GET <code>/skills</code></dt>
	<dd>Returns an array of all skill objects</dd>

<dt>GET <code>/skills/:skillId</code></dt>
	<dd>Returns the object of the skill with the given skill ID</dd>

<dt>GET <code>/skills/:skillId/battles</code></dt>  
	<dd>Returns an array of all battle objects of the specified skill</dd>

<dt>GET <code>/skills/:skillId/battles/:battleId</code></dt> 
	<dd>Returns the object of the battle with the given battle ID<dd>
</dl>

<br>

<dl>
<dt>POST <code>/skills</code></dt>
<br>
<dd>Adds a new skill to the database. The request body has to contain the required properties <b>name</b>, <b>maxLvl</b> and <b>currLvl</b>. Optionally, you can also set the current XP with the <b>currXp</b> property. If it is not specified, it will dafault to 0.<br>
Example:
<pre><code>
{
	"name": "Java",
	"maxLvl": "20",
	"currLvl": "1"
}
</code></pre>
</dd>


<dt>POST <code>/skills/:skillId/battles</code></dt>
<br>
<dd>Adds a battle to the selected skill. The request body has to contain the properties <b>description</b> and <b>xp</b>.<br>
Example:
<pre><code>
{
  "description": "1 hour coding",
  "xp": 10
}
</code></pre>
</dd>
</dl>

<br>

<dl>
<dt>PUT <code>/skills/:skillId?:xp</code></dt>  
<dd>Updates the experience points of the selected skill by the value given as the path parameter <b>xp</b>.<br>
Example:
<code>
localhost:8080/skills/1?xp=10
</code>
</dd>
</dl>

<br>

<dl>
<dt>DELETE <code>/skills/:skillId</code></dt>
	<dd>Deletes the skill with the specified ID, as well as all its associated battles</dd>

<dt>DELETE <code>/skills/:skillId/battles/:battleId</code></dt>
	<dd>Deletes the selected battle</dd>
</dl>