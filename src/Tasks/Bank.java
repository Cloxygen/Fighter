package src.Tasks;

import src.Sleep;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.script.Script;

public class Bank extends Task {
    private Area[] banks = {
            Banks.AL_KHARID, Banks.ARCEUUS_HOUSE, Banks.ARDOUGNE_NORTH, Banks.ARDOUGNE_SOUTH, Banks.CAMELOT, Banks.CANIFIS,
            Banks.CASTLE_WARS, Banks.CATHERBY, Banks.DRAYNOR, Banks.DUEL_ARENA, Banks.EDGEVILLE, Banks.FALADOR_EAST, Banks.FALADOR_WEST,
            Banks.GNOME_STRONGHOLD, Banks.GRAND_EXCHANGE, Banks.HOSIDIUS_HOUSE, Banks.LOVAKENGJ_HOUSE, Banks.LOVAKITE_MINE,
            Banks.LUMBRIDGE_UPPER, Banks.PEST_CONTROL, Banks.PISCARILIUS_HOUSE, Banks.SHAYZIEN_HOUSE,
            Banks.TZHAAR, Banks.VARROCK_EAST, Banks.VARROCK_WEST, Banks.YANILLE
    };

    private Entity banker;
    private String foodString;

    public Bank(Script api, String foodString){
        super(api);
        this.foodString = foodString;
    }

    @Override
    public boolean canRun(){
        if(!api.getInventory().contains(e -> e.hasAction("Eat")) && !foodString.contentEquals("")){
            return true;
        }
        else return false;
    }

    @Override
    public int run(){
        banker = null;
        if(api.getBank().isOpen()){
            if(!api.getInventory().isEmptyExcept(foodString)){
                api.log("Depositing");
                api.getBank().depositAllExcept(foodString);
                Sleep.sleepUntil(() -> api.getInventory().isEmptyExcept(foodString), 1500);
            }
            else if(api.getBank().contains(foodString)) {
                //withdraw food
                api.getBank().withdraw(foodString, 20);
                Sleep.sleepUntil(() -> api.getInventory().contains(foodString), 2500);
                api.getBank().close();
            }
            else {
                api.log("No food.");
                api.stop();
            }


        }
        //check for bank
        else if(api.getObjects().closest(e -> e.hasAction("Bank")) != null){
            banker = api.getObjects().closest(e -> e.hasAction("Bank"));
            banker.interact("Bank");
            Sleep.sleepUntil(() -> api.getBank().isOpen(), 2500);
        }
        else if(api.getNpcs().closest(e -> e.hasAction("Bank")) != null){
            banker = api.getObjects().closest(e -> e.hasAction("Bank"));
            banker.interact("Bank");
            Sleep.sleepUntil(() -> api.getBank().isOpen(), 2500);
        }
        //walk to bank
        else {
            api.getWalking().webWalk(banks);
        }
        return 500;
    }

    public String status() {
        return "Banking";
    }

}
