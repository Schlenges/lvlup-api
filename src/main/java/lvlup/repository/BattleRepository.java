package lvlup.repository;

import lvlup.domain.Battle;
import lvlup.domain.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BattleRepository extends JpaRepository<Battle, Long> {

    Battle findBySkillAndId(Skill skill, Long id);

    List<Battle> findBySkill(Skill skill);

}
