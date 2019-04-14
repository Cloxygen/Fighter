package src;


import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.api.util.ExperienceTracker;
import org.osbot.rs07.input.mouse.BotMouseListener;
import src.GUI.GUI;
import src.Paint.Button;
import src.Paint.Paints;
import src.Tasks.*;
import org.osbot.rs07.script.Script;


import org.osbot.rs07.script.ScriptManifest;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@ScriptManifest(name = "Fighter", author = "Cloxygen", version = 0.8, info = "", logo = "")


public class Main extends Script {
    private GUI gui;

    private Image chatOverlay;
    private Button paintButton;
    private Button meleeButton;
    private Button rangedButton;
    private Button magicButton;
    private final Font font = new Font("Tunga" , Font.PLAIN, 11);

    private AttackStyle attackStyle = AttackStyle.MELEE;

    private ArrayList<Task> tasks = new ArrayList<>();

    private Area fightArea;
    private String state;
    private long startTime;
    private ExperienceTracker experienceTracker;

    @Override
    public void onStart() {
        try {
            SwingUtilities.invokeAndWait(() -> {
                gui = new GUI();
                gui.open();
            });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
            stop(false);
            return;
        }

        if (!gui.isStarted()) {
            stop(false);
            return;
        }

        addTasks();
        startExperienceTrackers();
        loadImages();
        startTime = System.currentTimeMillis();
        fightArea = myPlayer().getArea(10);
        getBot().addMouseListener(clickListener);
    }

    @Override
    public int onLoop() {
        for (Task task : tasks) {
            if (task.canRun()) {
                state = task.status();
                return task.run();
            }
        }
        return random(500, 1250);
    }


    @Override
    public void onPaint(Graphics2D g) {
        if(gui.isStarted() && paintButton.isPressed()) {
            long timeRan = System.currentTimeMillis() - startTime;
            String timeString = Tools.convertMillis(timeRan);

            g.drawImage(chatOverlay, 1, 338, null);
            Paints.drawCursor(g, getMouse());
            Paints.drawButton(g, meleeButton);
            Paints.drawButton(g, rangedButton);
            Paints.drawButton(g, magicButton);

            Paints.drawTime(g, font, timeString);
            Paints.drawState(g, font, state);

            handleButtons(g);
        }

        Paints.drawButton(g, paintButton);
    }

    private void addTasks(){
        tasks.add(new PickUp(this, fightArea, gui.getPickupItems()));
        tasks.add(new BonesToPeaches(this));
        tasks.add(new Bank(this, gui.getFoodName()));
        tasks.add(new Return(this, fightArea, myPlayer().getArea(3)));
        tasks.add(new Eat(this));
        tasks.add(new Fight(this, gui.getMonsterName(), myPlayer().getArea(15)));
    }

    private void startExperienceTrackers(){
        experienceTracker = getExperienceTracker();
        experienceTracker.start(Skill.STRENGTH);
        experienceTracker.start(Skill.ATTACK);
        experienceTracker.start(Skill.DEFENCE);
        experienceTracker.start(Skill.HITPOINTS);
        experienceTracker.start(Skill.RANGED);
        experienceTracker.start(Skill.MAGIC);
    }

    private void loadImages(){
        chatOverlay = Tools.getImage(getScriptResourceAsStream("resources/ChatOverlay.png"));
        paintButton = new Button(440,480, Tools.getImage(getScriptResourceAsStream("resources/PaintButton.png")));
        paintButton.setPressed(true);
        meleeButton = new Button(1, 480, Tools.getImage(getScriptResourceAsStream("resources/MeleeButton.png")));
        rangedButton = new Button(81, 480, Tools.getImage(getScriptResourceAsStream("resources/RangedButton.png")));
        magicButton = new Button(161, 480, Tools.getImage(getScriptResourceAsStream("resources/MagicButton.png")));
    }

    private void handleButtons(Graphics2D g){
        switch (attackStyle){
            case MELEE:
                meleeButton.setPressed(true);
                rangedButton.setPressed(false);
                magicButton.setPressed(false);
                Paints.drawMeleeStats(g, font, experienceTracker);
                break;
            case RANGED:
                meleeButton.setPressed(false);
                rangedButton.setPressed(true);
                magicButton.setPressed(false);
                Paints.drawRangeStats(g, font, experienceTracker);
                break;
            case MAGIC:
                meleeButton.setPressed(false);
                rangedButton.setPressed(false);
                magicButton.setPressed(true);
                Paints.drawMagicStats(g, font, experienceTracker);
                break;
        }
    }

    private BotMouseListener clickListener = new BotMouseListener() {
        @Override
        public void checkMouseEvent(MouseEvent e) {
            if(paintButton.getRect().contains(e.getPoint())){
                paintButton.setPressed(!paintButton.isPressed());
            }
            if(paintButton.isPressed()) {
                if(meleeButton.getRect().contains(e.getPoint())){
                    attackStyle = AttackStyle.MELEE;
                }
                if(rangedButton.getRect().contains(e.getPoint())){
                    attackStyle = AttackStyle.RANGED;
                }
                if(magicButton.getRect().contains(e.getPoint())){
                    attackStyle = AttackStyle.MAGIC;
                }
            }
        }
        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

    private enum AttackStyle{
        MELEE, RANGED, MAGIC
    }

}
