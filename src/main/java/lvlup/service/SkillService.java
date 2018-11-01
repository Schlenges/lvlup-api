package lvlup.service;

import lvlup.domain.Skill;
import lvlup.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    SkillRepository skillRepository;

    public List<Skill> listAll(){
        return skillRepository.findAll();
    }

    public Skill create(Skill skill){
        return skillRepository.save(skill);
    }

    public Skill getSkill(String name){
        return skillRepository.findByName(name);
    }

    public Skill updateXp(String name, int xp){
        Skill skill = skillRepository.findByName(name);
        int updatedXp = skill.getCurr_xp() + xp;

        updatedXp = checkForLevelUp(skill, updatedXp);

        skill.setCurr_xp(updatedXp);

        return skillRepository.save(skill);
    }

    public void delete(String name){
        Skill skill = skillRepository.findByName(name);
        skillRepository.delete(skill);
    }

    private int checkForLevelUp(Skill skill, int updatedXp){
        if(updatedXp >= 100){
            if(skill.getCurr_lvl() < skill.getMax_lvl()){
                skill.setCurr_lvl(skill.getCurr_lvl() + 1);
                updatedXp -= 100;
            } else{
                skill.setCurr_lvl(skill.getMax_lvl());
                updatedXp = 100;
            }
        }

        return updatedXp;
    }

}
