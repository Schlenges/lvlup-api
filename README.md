# LVL UP API

## Usage

**GET Routes**  
Get all skills: ```/skills```  
Get one skill: ```/skills/:skillId```  
Get skill battles: ```/skills/:skillId/battles```  
Get one battle: ```/skills/:skillId/battles/:battleId``` 

**POST Routes**  
Add skill: ```/skills```  
Add battle: ```/skills/:skillId/battles``` 

**PUT Routes**  
Update Xp: ```/skills/:skillId/:xp```  

**DELETE Routes**  
Delete skill: ```/skills/:skillId```  
Delete battle: ```/skills/:skillId/battles/:battleId```

## ToDo:  
- [ ] Change routing to include skill and battle edits (put routes)