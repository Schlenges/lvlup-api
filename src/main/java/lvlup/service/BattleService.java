package lvlup.service;

import lvlup.domain.Battle;
import lvlup.domain.Skill;
import lvlup.repository.BattleRepository;
import lvlup.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BattleService {

    @Autowired
    private BattleRepository battleRepository;
    @Autowired
    private SkillRepository skillRepository;

    public List<Battle> listAll(String name){
        Skill skill = skillRepository.findByName(name);
        return battleRepository.findBySkill(skill);
    }

    public Battle getBattle(String name, Long id){
        Skill skill = skillRepository.findByName(name);
        return battleRepository.findBySkillAndId(skill, id);
    }

    @Transactional
    public Battle create(String name, Battle battle) {
        Skill skill = skillRepository.findByName(name);
        battle.setSkill(skill);

        Battle newBattle = battleRepository.save(battle);

        skill.getBattles().add(battle);
        skillRepository.save(skill);

        return newBattle;
    }

    @Transactional
    public void delete(String name, Long battleId) {
        Skill skill = skillRepository.findByName(name);
        Battle battle = battleRepository.getOne(battleId);

        skill.getBattles().remove(battle);
        skillRepository.save(skill);

        battleRepository.delete(battle);
    }
}
