package lvlup.service;

import lvlup.domain.Battle;
import lvlup.domain.Skill;
import lvlup.repository.BattleRepository;
import lvlup.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BattleService {

    @Autowired
    private BattleRepository battleRepository;
    @Autowired
    private SkillRepository skillRepository;

    @Transactional // doesn't work without transactional
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
