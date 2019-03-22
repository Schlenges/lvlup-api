package lvlup.service;

import lvlup.domain.Battle;
import lvlup.domain.Skill;
import lvlup.repository.BattleRepository;
import lvlup.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BattleService {

    @Autowired
    private BattleRepository battleRepository;
    @Autowired
    private SkillRepository skillRepository;

    public List<Battle> listAll(Long skillId){
        try{
            Skill skill = skillRepository.findById(skillId).get();
            return battleRepository.findBySkill(skill);
        } catch(Exception e){
            if(e instanceof NoSuchElementException){
                System.out.println("No skill with this id exists");
            }
            return null;
        }
    }

    public Battle getBattle(Long skillId, Long id){
        try{
            Skill skill = skillRepository.findById(skillId).get();
            return battleRepository.findBySkillAndId(skill, id);
        } catch(Exception e){
            if(e instanceof NoSuchElementException){
                System.out.println("No battle with this id exists");
            }
            return null;
        }
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
