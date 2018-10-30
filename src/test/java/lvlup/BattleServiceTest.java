package lvlup;

import lvlup.domain.Battle;
import lvlup.domain.Skill;
import lvlup.repository.BattleRepository;
import lvlup.repository.SkillRepository;
import lvlup.service.BattleService;
import org.junit.Before;
import org.junit.BeforeClass;
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
public class BattleServiceTest {

    @Autowired
    private BattleService battleService;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private BattleRepository battleRepository;

    // create battle in db
    @Test
    public void shouldSaveBattleInDatabase(){
        Skill skill = skillRepository.save(new Skill("Skill", 5, 10));
        Battle battle = new Battle("Battle:" + UUID.randomUUID().toString(), 20, skill);

        Battle result = battleService.create(skill.getName(), battle);

        assertNotNull(result);
        assertEquals("Wrong battle ID", battle.getId(), result.getId());

    }

    // assign it to skill
    @Test
    public void shouldBeAssignedToSkill(){
        Skill skill = skillRepository.save(new Skill("Skill", 5, 10));
        Battle battle = new Battle("Battle:" + UUID.randomUUID().toString(), 20, skill);

        Battle result = battleService.create(skill.getName(), battle);

        assertEquals("Wrong skill ID", battle.getSkill(), result.getSkill());
    }

    // delete battle
    @Test
    public void shouldDeleteBattleFromDb(){
        Skill skill = skillRepository.save(new Skill("Skill", 5, 10));
        Battle battle = new Battle("Battle:" + UUID.randomUUID().toString(), 20, skill);
        battleService.create(skill.getName(), battle);
        Long battleId = battle.getId();

        battleService.delete(skill.getName(), battleId);
        Battle deletedBattle = battleRepository.getOne(battleId);

        assertNull("Battle should not exist after deletion", deletedBattle);
    }

}
