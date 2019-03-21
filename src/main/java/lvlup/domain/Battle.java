package lvlup.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Battles")
public class Battle extends AbstractPersistable<Long> {

    private String description;
    private int xp;
    @ManyToOne
    @JoinColumn(name = "fk_skill") // Battles table has column named fk_skill with the foreign key of the skill
    @JsonIgnore
    private Skill skill;

    /* public Battle(){
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
    } */
}
