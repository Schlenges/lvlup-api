package lvlup.controller;

import lvlup.domain.Battle;
import lvlup.service.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("skills/{skillId}/battles")
public class BattleController {

    @Autowired
    private BattleService battleService;

    @GetMapping
    public List<Battle> getBattles(@PathVariable Long skillId){
        return battleService.listAll(skillId);
    }

    @GetMapping("/{id}")
    public Battle getBattle(@PathVariable Long skillId, @PathVariable Long id){
        return battleService.getBattle(skillId, id);
    }

    @PostMapping
    public Battle createBattle(@PathVariable Long skillId, @RequestBody Battle battle){
        return battleService.create(skillId, battle);
    }

    @DeleteMapping("/{id}")
    public void deleteBattle(@PathVariable Long skillId, @PathVariable Long id){
        battleService.delete(skillId, id);
    }
}
