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

    public List<Battle> listAll(Long skillId){
        Skill skill = skillRepository.findById(skillId).get();
        return battleRepository.findBySkill(skill);
    }

    public Battle getBattle(Long skillId, Long id){
        Skill skill = skillRepository.findById(skillId).get();
        return battleRepository.findBySkillAndId(skill, id);
    }

    @Transactional
    public Battle create(Long skillId, Battle battle) {
        Skill skill = skillRepository.findById(skillId).get();
        battle.setSkill(skill);

        Battle newBattle = battleRepository.save(battle);

        skill.getBattles().add(battle);
        skillRepository.save(skill);

        return newBattle;
    }

    @Transactional
    public void delete(Long skillId, Long battleId) {
        Skill skill = skillRepository.findById(skillId).get();
        Battle battle = battleRepository.findBySkillAndId(skill, battleId);

        skill.getBattles().remove(battle);
        skillRepository.save(skill);

        battleRepository.delete(battle);
    }
}
