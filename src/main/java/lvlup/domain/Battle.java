package lvlup.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Battles")
public class Battle extends AbstractPersistable<Long> {

    private String description;

    private int xp;

    @ManyToOne
    @JoinColumn(name = "fk_skill") // Battles table has column named fk_skill with the foreign key of the skill
    @JsonIgnore
    private Skill skill;

    public Battle(){
        this.skill = new Skill();
    }

    public Battle(String description, int xp){
        this();
        this.description = description;
        this.xp = xp;
    }

    public Battle(String description, int xp, Skill skill){
        this(description, xp);
        this.setSkill(skill);
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
