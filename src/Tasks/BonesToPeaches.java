package src.Tasks;

import org.osbot.rs07.script.Script;

public class BonesToPeaches extends Task {

    public BonesToPeaches(Script api){
        super(api);
    }

    @Override
    public boolean canRun(){
        return false;
    }

    @Override
    public int run(){
        return 500;
    }

    public String status() {
        return "Fighting";
    }
}
