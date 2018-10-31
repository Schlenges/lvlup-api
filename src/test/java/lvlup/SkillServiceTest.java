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

        assertEquals("Wrong number of experience points", xp, updatedSkill.getCurr_xp());
    }

    @Test
    public void shouldLevelUp(){
        saveSkill();
        int xp = 150;
        
        Skill updatedSkill = updateSkill(xp);

        assertEquals("Wrong level after level up", 5, updatedSkill.getCurr_lvl());
    }

    @Test
    public void xpShouldNotBeGreaterThanHundred(){
        saveSkill();
        int xp = 150;

        Skill updatedSkill = updateSkill(xp);

        assertEquals("Wrong number of xp after level up", 50, updatedSkill.getCurr_xp());
    }

    @Test
    public void shouldDeleteSkillFromDb(){
        saveSkill();
        String name = skill.getName();

        skillService.delete(name);
        Skill deletedSkill = skillRepository.findByName(name);

        assertNull("Skill should not exist after deletion", deletedSkill);
    }

    @Test // use of battleService bad practice?
    public void shouldCascadeDeleteBattles(){
        saveSkill();
        String name = skill.getName();
        Battle battle = new Battle();
        battleService.create(name, battle);

        skillService.delete(name);
        Optional<Battle> deletedBattle = battleRepository.findById(battle.getId());

        assertFalse("Battle should not exist after skill deletion", deletedBattle.isPresent());
    }

    private void saveSkill(){
        skillRepository.save(skill);
    }

    private Skill updateSkill(int xp){
        return skillService.updateXp(skill.getName(), xp);
    }
}
