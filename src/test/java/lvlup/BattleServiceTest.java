package lvlup;

import lvlup.domain.Battle;
import lvlup.domain.Skill;
import lvlup.repository.BattleRepository;
import lvlup.repository.SkillRepository;
import lvlup.service.BattleService;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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

    @Test
    public void shouldSaveBattleInDatabase() {

        Battle result = battleService.create(skill.getName(), battle);

        assertNotNull(result);
        assertEquals("Wrong battle ID", battle.getId(), result.getId());

    }

    @Test
    public void shouldBeAssignedToSkill() {

        Battle result = battleService.create(skill.getName(), battle);

        assertEquals("Wrong skill ID", battle.getSkill(), result.getSkill());
    }

    @Test
    public void shouldDeleteBattleFromDb() {

        battleService.create(skill.getName(), battle); // muss nicht gefangen werden?
        Long battleId = battle.getId();

        battleService.delete(skill.getName(), battleId);
        // findById um lazy fetch problem zu umgehen
        // Transactional in battle service
        Optional<Battle> deletedBattle = battleRepository.findById(battleId);

        assertFalse("Battle should not exist after deletion", deletedBattle.isPresent());
    }
}
