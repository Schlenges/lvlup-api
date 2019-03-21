package lvlup.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="Skills")
public class Skill extends AbstractPersistable<Long> {

    private String name;
    private int currLvl;
    private int maxLvl;
    private int currXp;
    @JsonIgnore
    @JsonIgnoreProperties("skill")
    @OneToMany(mappedBy = "skill", cascade = CascadeType.REMOVE) //mappedBy name of the association-mapping attribute
    private List<Battle> battles;

    public Skill(){
        this.currLvl = 0;
        this.battles = new ArrayList<>();
    }

    public Skill(String name, int currLvl, int maxLvl){
        super();
        this.name = name;
        this.currLvl = currLvl;
        this. maxLvl = maxLvl;
    }

}
