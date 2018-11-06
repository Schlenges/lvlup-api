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

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Battle> getBattles(@PathVariable String name){
        return battleService.listAll(name);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Battle getBattle(@PathVariable String name, @PathVariable Long id){
        return battleService.getBattle(name, id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Battle createBattle(@PathVariable String name, @RequestBody Battle battle){
        return battleService.create(name, battle);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteBattle(@PathVariable String name, @PathVariable Long id){
        battleService.delete(name, id);
    }
}
