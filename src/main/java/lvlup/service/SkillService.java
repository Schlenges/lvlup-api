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

    public Skill getSkill(Long id){
        return skillRepository.findById(id).get();
    }

    public Skill updateXp(Long id, int xp){
        Skill skill = skillRepository.findById(id).get();
        int updatedXp = skill.getCurrXp() + xp;

        updatedXp = checkForLevelUp(skill, updatedXp);

        skill.setCurrXp(updatedXp);

        return skillRepository.save(skill);
    }

    public void delete(Long id){
        Skill skill = skillRepository.findById(id).get();
        skillRepository.delete(skill);
    }

    private int checkForLevelUp(Skill skill, int updatedXp){
        if(updatedXp >= 100){
            if(skill.getCurrLvl() < skill.getMaxLvl()){
                skill.setCurrLvl(skill.getCurrLvl() + 1);
                updatedXp -= 100;
            } else{
                skill.setCurrLvl(skill.getMaxLvl());
                updatedXp = 100;
            }
        }

        return updatedXp;
    }

}
