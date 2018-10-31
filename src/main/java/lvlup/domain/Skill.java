package lvlup.domain;

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

    private int curr_lvl;

    private int max_lvl;

    private int curr_xp;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.REMOVE) //orphan removal?
    private List<Battle> battles;

    public Skill(){
        this.curr_xp = 0;
        this.battles = new ArrayList<>();
    }

    public Skill(String name, int curr_lvl, int max_lvl){
        this();
        this.name = name;
        this.curr_lvl = curr_lvl;
        this.max_lvl = max_lvl;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurr_lvl() {
        return curr_lvl;
    }

    public void setCurr_lvl(int curr_lvl) {
        this.curr_lvl = curr_lvl;
    }

    public int getMax_lvl() {
        return max_lvl;
    }

    public void setMax_lvl(int max_lvl) {
        this.max_lvl = max_lvl;
    }

    public int getCurr_xp() {
        return curr_xp;
    }

    public void setCurr_xp(int curr_xp) {
        this.curr_xp = curr_xp;
    }

    public List<Battle> getBattles() {
        return battles;
    }

    public void setBattles(List<Battle> battles) {
        this.battles = battles;
    }
}
