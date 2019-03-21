package lvlup.controller;

import lvlup.domain.Skill;
import lvlup.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
// @RequestMapping("/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping({"/", "/skills"})
    public List<Skill> getSkills(){
        return skillService.listAll();
    }

    @GetMapping("/skills/{id}")
    public Skill getSkill(@PathVariable Long id){
        return skillService.getSkill(id);
    }

    @PostMapping("/skills")
    public Skill createSkill(@RequestBody Skill skill){
        return skillService.create(skill);
    }

    @PutMapping("/skills/{id}")
    public Skill updateSkill(@PathVariable Long id, @RequestParam int xp){
        return skillService.updateXp(id, xp);
    }

    @DeleteMapping("/skills/{id}")
    public void deleteSkill(@PathVariable Long id){
        skillService.delete(id);
    }
}
