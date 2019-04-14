package src.Tasks;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.script.Script;


public class Return extends Task {

    private Area fightArea;
    private Area returnArea;

    public Return(Script api, Area fightArea, Area returnArea){
        super(api);
        this.fightArea = fightArea;
        this.returnArea = returnArea;
    }

    @Override
    public boolean canRun(){
        if(!fightArea.contains(api.myPlayer())){
            return true;
        }
        else return false;
    }

    @Override
    public int run(){
        api.getWalking().webWalk(returnArea);
        return 500;
    }

    public String status() {
        return "Fighting";
    }

    public Area getFightArea() {
        return fightArea;
    }

    public void setFightArea(Area fightArea) {
        this.fightArea = fightArea;
    }

    public void setReturnArea(Area returnArea) {
        this.returnArea = returnArea;
    }
}

