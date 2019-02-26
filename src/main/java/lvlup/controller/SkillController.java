package lvlup.controller;

import lvlup.domain.Skill;
import lvlup.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;


    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Skill> getSkills(){
        return skillService.listAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Skill getSkill(@PathVariable Long id){
        return skillService.getSkill(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Skill createSkill(@RequestBody Skill skill){
        return skillService.create(skill);
    }

    @RequestMapping(value = "/{id}/{xp}", method = RequestMethod.PUT, produces = "application/json")
    public Skill updateSkill(@PathVariable Long id, @PathVariable int xp){
        return skillService.updateXp(id, xp);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteSkill(@PathVariable Long id){
        skillService.delete(id);
    }
}
