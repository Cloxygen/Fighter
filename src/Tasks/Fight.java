package src.Tasks;
import org.osbot.rs07.api.map.Area;
import src.Sleep;

import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.Script;


public class Fight extends Task {
    private String enemyString;
    private NPC enemy;
    private Area fightArea;

    public Fight(Script api,String enemyString, Area fightArea){
        super(api);
        this.enemyString = enemyString;
        this.fightArea = fightArea;
    }

    @Override
    public boolean canRun(){
        if(!api.getCombat().isFighting() && !api.myPlayer().isMoving()){
            return true;
        }
        else return false;
    }

    @Override
    public int run(){
        enemy = api.getNpcs().closest(
                e -> e.getName().contains(enemyString) && api.getMap().canReach(e) && e.isAttackable() && fightArea.contains(e)
        );
        if(!api.getCombat().isAutoRetaliateOn()){
            api.getCombat().toggleAutoRetaliate(true);
            Sleep.sleepUntil(() -> api.getCombat().isAutoRetaliateOn(), 3000);
        }
        else if(api.myPlayer().isUnderAttack()){
            Sleep.sleepUntil(() -> !api.myPlayer().isUnderAttack(), 2500);
        }
        else if(enemy != null){
            enemy.interact("Attack");
            Sleep.sleepUntil(() -> api.getCombat().isFighting(), 2500);
        }
        return 500;
    }

    public String status() {
        return "Fighting";
    }

    public void setEnemyString(String enemyString) {
        this.enemyString = enemyString;
    }

    public void setFightArea(Area fightArea) {
        this.fightArea = fightArea;
    }
}
