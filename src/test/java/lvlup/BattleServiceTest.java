package lvlup;

import lvlup.domain.Battle;
import lvlup.domain.Skill;
import lvlup.repository.BattleRepository;
import lvlup.repository.SkillRepository;
import lvlup.service.BattleService;
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
public class BattleServiceTest {

    @Autowired
    private BattleService battleService;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private BattleRepository battleRepository;

    private Skill skill ;
    private Battle battle;

    @Before
    public void before(){
        skill = skillRepository.save(new Skill("Skill" + UUID.randomUUID().toString(), 5, 10));
        battle = new Battle("Battle:" + UUID.randomUUID().toString(), 20, skill);
    }

    // shouldGetBattle

    // shouldListAllBattles

    @Test
    public void shouldSaveBattleInDatabase() {

        Battle result = battleService.create(skill.getName(), battle);

        assertNotNull(result);
        assertEquals("Wrong battle ID", battle.getId(), result.getId());

    }

    @Test // other assertion? e.g. find skill, get battles, list contains battle
    public void shouldBeAssignedToSkill() {

        Battle result = battleService.create(skill.getName(), battle);

        assertEquals("Wrong skill ID", battle.getSkill(), result.getSkill());
    }

    @Test
    public void shouldDeleteBattleFromDb() {

        battleService.create(skill.getName(), battle);
        Long battleId = battle.getId();

        battleService.delete(skill.getName(), battleId);
        Optional<Battle> deletedBattle = battleRepository.findById(battleId);  // findById um lazy fetch problem zu beheben


        assertFalse("Battle should not exist after deletion", deletedBattle.isPresent());
    }
}
