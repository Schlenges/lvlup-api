package lvlup.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
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
        this.currXp = 0;
        this.battles = new ArrayList<>();
    }

    public Skill(String name, int currLvl, int maxLvl){
        this();
        this.name = name;
        this.currLvl = currLvl;
        this.maxLvl = maxLvl;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrLvl() {
        return currLvl;
    }

    public void setCurrLvl(int currLvl) {
        this.currLvl = currLvl;
    }

    public int getMaxLvl() {
        return maxLvl;
    }

    public void setMaxLvl(int maxLvl) {
        this.maxLvl = maxLvl;
    }

    public int getCurrXp() {
        return currXp;
    }

    public void setCurrXp(int currXp) {
        this.currXp = currXp;
    }

    public List<Battle> getBattles() {
        return battles;
    }

    public void setBattles(List<Battle> battles) {
        this.battles = battles;
    }
}
