package lvlup.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Battles")
public class Battle extends AbstractPersistable<Long> {

    private String description;

    private int xp;

    @ManyToOne(fetch = FetchType.EAGER)
    private Skill skill;

    public Battle(){}

    public Battle(String description, int xp, Skill skill){
        this.description = description;
        this.xp = xp;
        this.skill = skill;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}
