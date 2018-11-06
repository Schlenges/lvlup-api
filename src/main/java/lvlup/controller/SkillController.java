package lvlup.controller;

import lvlup.domain.Skill;
import lvlup.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;


    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Skill> getSkills(){
        return skillService.listAll();
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = "application/json")
    public Skill getSkill(@PathVariable String name){
        return skillService.getSkill(name);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Skill createSkill(@RequestBody Skill skill){
        return skillService.create(skill);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.PUT, produces = "application/json")
    public Skill updateSkill(@PathVariable String name, @RequestParam int xp){
        return skillService.updateXp(name, xp);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteSkill(@PathVariable String name){
        skillService.delete(name);
    }
}
