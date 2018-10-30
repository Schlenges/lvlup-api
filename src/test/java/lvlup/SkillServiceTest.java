package lvlup;

import lvlup.domain.Skill;
import lvlup.repository.SkillRepository;
import lvlup.service.SkillService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkillServiceTest {

    @Autowired
    private SkillService skillService;
    @Autowired
    private SkillRepository skillRepository;

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

    @Test // cascade delete
    public void shouldDeleteSkillFromDb(){
        saveSkill();
        String name = skill.getName();

        skillService.delete(name);
        Skill deletedSkill = skillRepository.findByName(name);

        assertNull("Skill should not exist after deletion", deletedSkill);
    }


    private void saveSkill(){
        skillRepository.save(skill);
    }

    private Skill updateSkill(int xp){
        return skillService.updateXp(skill.getName(), xp);
    }
}
