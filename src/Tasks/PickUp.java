package src.Tasks;

import org.osbot.rs07.api.filter.AreaFilter;
import org.osbot.rs07.api.filter.NameFilter;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.script.Script;
import src.Sleep;

import java.util.Arrays;
import java.util.List;

public class PickUp extends Task {

    private String[] pickUpItems = null;
    private Area area;
    private GroundItem item;

    public PickUp(Script api, Area area, String input){
        super(api);
        if(!input.isEmpty()) {
            pickUpItems = input.split(",", 25);
        }
        else pickUpItems = null;
        this.area = area;
    }

    public  boolean canRun(){
        if(pickUpItems == null) {
            return false;
        }

        item = api.getGroundItems().closest(
                e -> e != null && area.contains(e) && Arrays.asList(pickUpItems).contains(e.getName()) && api.getMap().canReach(e)
        );

        if(item != null){
            api.log(item.getName());
            if(api.getInventory().isFull() || api.getCombat().isFighting() || api.myPlayer().isAnimating() || api.myPlayer().isMoving()){
                return false;
            }
            else return true;
        }
        return false;
    }

    public  int run(){
        GroundItem pickUp = api.getGroundItems().closest(pickUpItems);
        if(pickUp != null){
            pickUp.interact("Take");
            Sleep.sleepUntil(() -> !pickUp.exists(), 2000);
        }
        return 500;
    }

    public  String status(){
        return "Picking up";
    }


}
