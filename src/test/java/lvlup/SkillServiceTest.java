package lvlup;

import lvlup.domain.Battle;
import lvlup.domain.Skill;
import lvlup.repository.BattleRepository;
import lvlup.repository.SkillRepository;
import lvlup.service.BattleService;
import lvlup.service.SkillService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkillServiceTest {

    @Autowired
    private SkillService skillService;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private BattleService battleService;

    @Autowired
    private BattleRepository battleRepository;

    private Skill skill;

    @Before
    public void before(){
        skill = new Skill("skill:" + UUID.randomUUID().toString(), 4, 20);
    }

    @Test
    public void shouldSaveSkillInDatabase(){
        skillService.create(skill);

        Skill result = skillRepository.findByName(skill.getName());

        assertNotNull(result);
        assertEquals("Wrong skill ID", skill.getId(), result.getId());
    }

    // update xp
    @Test
    public void shouldUpdateSkillXp(){
        saveSkill();
        int xp = 20;

        Skill updatedSkill = updateSkill(xp);

        assertEquals("Wrong number of experience points", xp, updatedSkill.getCurrXp());
    }

    @Test
    public void shouldLevelUp(){
        saveSkill();
        int xp = 150;
        
        Skill updatedSkill = updateSkill(xp);

        assertEquals("Wrong level after level up", 5, updatedSkill.getCurrLvl());
    }

    @Test
    public void xpShouldNotBeGreaterThanHundred(){
        saveSkill();
        int xp = 150;

        Skill updatedSkill = updateSkill(xp);

        assertEquals("Wrong number of xp after level up", 50, updatedSkill.getCurrXp());
    }

    @Test
    public void shouldDeleteSkillFromDb(){
        saveSkill();
        Long id = skill.getId();

        skillService.delete(id);
        Optional<Skill> deletedSkill = skillRepository.findById(id);

        assertFalse("Skill should not exist after deletion", deletedSkill.isPresent());
    }

    @Test // use of battleService bad practice?
    public void shouldCascadeDeleteBattles(){
        saveSkill();
        Long skillId = skill.getId();
        Battle battle = new Battle();
        battleService.create(skillId, battle);

        skillService.delete(skillId);
        Optional<Battle> deletedBattle = battleRepository.findById(battle.getId());

        assertFalse("Battle should not exist after skill deletion", deletedBattle.isPresent());
    }

    private void saveSkill(){
        skillRepository.save(skill);
    }

    private Skill updateSkill(int xp){
        return skillService.updateXp(skill.getId(), xp);
    }
}
