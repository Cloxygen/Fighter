package src.Tasks;


import src.Sleep;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;


public class Eat extends Task {

    public Eat(Script api){
        super(api);
    }

    @Override
    public boolean canRun(){
        if(api.getInventory().isFull()){
            return true;
        }
        //hp greater than 20%
        if(api.getSkills().getDynamic(Skill.HITPOINTS) > api.getSkills().getStatic(Skill.HITPOINTS)/3){
            return false;
        }
        else return true;
    }

    @Override
    public int run(){
        Item food = null;
        food = api.getInventory().getItem(e -> e.hasAction("Eat"));
        if(food != null){
            food.interact("Eat");
            Sleep.sleepUntil(() -> !api.myPlayer().isAnimating(), 2500);
        }
        return 500;
    }

    public String status() {
        return "Eating";
    }
}
