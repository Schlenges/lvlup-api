package lvlup.controller;

import lvlup.domain.Battle;
import lvlup.service.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("skills/{name}/battles")
public class BattleController {

    @Autowired
    private BattleService battleService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Battle> getBattles(@PathVariable String name){
        return battleService.listAll(name);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Battle getBattle(@PathVariable String name, @PathVariable Long id){
        return battleService.getBattle(name, id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Battle createBattle(@PathVariable String name, @RequestBody Battle battle){
        return battleService.create(name, battle);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteBattle(@PathVariable String name, @PathVariable Long id){
        battleService.delete(name, id);
    }
}
