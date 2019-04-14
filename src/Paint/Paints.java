package src.Paint;

import org.osbot.rs07.api.Mouse;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.api.util.ExperienceTracker;
import org.osbot.rs07.script.API;
import src.Tools;

import java.awt.*;

public final class Paints extends API {

    private static int rootX = 20;
    private static int rootY = 338 + 11 + 15;
    private static int paddingX = 80;
    private static int paddingY = 25;

    public void initializeModule(){

    }

    public static void drawCursor(Graphics2D g, Mouse mouse){
        g.setColor(new Color(255, 255, 255, 50));
        Point mousePos = mouse.getPosition();
        g.drawLine(mousePos.x, 0, mousePos.x, 500); //draws vertical line from mouse position
        g.drawLine(0, mousePos.y, 764, mousePos.y); //draws horizontal line from mouse position
    }

    public static void drawMeleeStats(Graphics2D g, Font font, ExperienceTracker experienceTracker){
        //Draw EXP and Time
        g.setColor(Color.WHITE);
        g.setFont(font);
        if (experienceTracker != null) {
            g.drawString("ATTACK",    rootX + paddingX * 1, rootY);
            g.drawString("STRENGTH",  rootX + paddingX * 2, rootY);
            g.drawString("DEFENCE",   rootX + paddingX * 3, rootY);
            g.drawString("HITPOINTS", rootX + paddingX * 4, rootY);

            g.drawString("EXP GAINED", rootX, rootY + paddingY * 1);
            g.drawString("" + experienceTracker.getGainedXP(Skill.ATTACK),    rootX + paddingX * 1, rootY + paddingY * 1);
            g.drawString("" + experienceTracker.getGainedXP(Skill.STRENGTH),  rootX + paddingX * 2, rootY + paddingY * 1);
            g.drawString("" + experienceTracker.getGainedXP(Skill.DEFENCE),   rootX + paddingX * 3, rootY + paddingY * 1);
            g.drawString("" + experienceTracker.getGainedXP(Skill.HITPOINTS), rootX + paddingX * 4, rootY + paddingY * 1);

            g.drawString("EXP/HR", rootX, rootY + paddingY * 2);
            g.drawString("" + experienceTracker.getGainedXPPerHour(Skill.ATTACK),    rootX + paddingX * 1, rootY + paddingY * 2);
            g.drawString("" + experienceTracker.getGainedXPPerHour(Skill.STRENGTH),  rootX + paddingX * 2, rootY + paddingY * 2);
            g.drawString("" + experienceTracker.getGainedXPPerHour(Skill.DEFENCE),   rootX + paddingX * 3, rootY + paddingY * 2);
            g.drawString("" + experienceTracker.getGainedXPPerHour(Skill.HITPOINTS), rootX + paddingX * 4, rootY + paddingY * 2);

            g.drawString("TTL", rootX, rootY + paddingY * 3);
            g.drawString("" + Tools.convertMillis(experienceTracker.getTimeToLevel(Skill.ATTACK)),    rootX + paddingX * 1, rootY + paddingY * 3);
            g.drawString("" + Tools.convertMillis(experienceTracker.getTimeToLevel(Skill.STRENGTH)),  rootX + paddingX * 2, rootY + paddingY * 3);
            g.drawString("" + Tools.convertMillis(experienceTracker.getTimeToLevel(Skill.DEFENCE)),   rootX + paddingX * 3, rootY + paddingY * 3);
            g.drawString("" + Tools.convertMillis(experienceTracker.getTimeToLevel(Skill.HITPOINTS)), rootX + paddingX * 4, rootY + paddingY * 3);

            g.drawString("LVLS GAINED", rootX, rootY + paddingY * 4);
            g.drawString("" + experienceTracker.getGainedLevels(Skill.ATTACK),    rootX + paddingX * 1, rootY + paddingY * 4);
            g.drawString("" + experienceTracker.getGainedLevels(Skill.STRENGTH),  rootX + paddingX * 2, rootY + paddingY * 4);
            g.drawString("" + experienceTracker.getGainedLevels(Skill.DEFENCE),   rootX + paddingX * 3, rootY + paddingY * 4);
            g.drawString("" + experienceTracker.getGainedLevels(Skill.HITPOINTS), rootX + paddingX * 4, rootY + paddingY * 4);

        }
    }

    public static void drawMagicStats(Graphics2D g, Font font, ExperienceTracker experienceTracker){
        //Draw EXP and Time
        g.setColor(Color.WHITE);
        g.setFont(font);
        if (experienceTracker != null) {
            g.drawString("MAGIC",    rootX + paddingX * 1, rootY);

            g.drawString("DEFENCE",   rootX + paddingX * 3, rootY);
            g.drawString("HITPOINTS", rootX + paddingX * 4, rootY);

            g.drawString("EXP GAINED", rootX, rootY + paddingY * 1);
            g.drawString("" + experienceTracker.getGainedXP(Skill.MAGIC),    rootX + paddingX * 1, rootY + paddingY * 1);
            //blank
            g.drawString("" + experienceTracker.getGainedXP(Skill.DEFENCE),   rootX + paddingX * 3, rootY + paddingY * 1);
            g.drawString("" + experienceTracker.getGainedXP(Skill.HITPOINTS), rootX + paddingX * 4, rootY + paddingY * 1);

            g.drawString("EXP/HR", rootX, rootY + paddingY * 2);
            g.drawString("" + experienceTracker.getGainedXPPerHour(Skill.MAGIC),    rootX + paddingX * 1, rootY + paddingY * 2);
            //blank
            g.drawString("" + experienceTracker.getGainedXPPerHour(Skill.DEFENCE),   rootX + paddingX * 3, rootY + paddingY * 2);
            g.drawString("" + experienceTracker.getGainedXPPerHour(Skill.HITPOINTS), rootX + paddingX * 4, rootY + paddingY * 2);

            g.drawString("TTL", rootX, rootY + paddingY * 3);
            g.drawString("" + Tools.convertMillis(experienceTracker.getTimeToLevel(Skill.MAGIC)),    rootX + paddingX * 1, rootY + paddingY * 3);
            //blank
            g.drawString("" + Tools.convertMillis(experienceTracker.getTimeToLevel(Skill.DEFENCE)),   rootX + paddingX * 3, rootY + paddingY * 3);
            g.drawString("" + Tools.convertMillis(experienceTracker.getTimeToLevel(Skill.HITPOINTS)), rootX + paddingX * 4, rootY + paddingY * 3);

            g.drawString("LVLS GAINED", rootX, rootY + paddingY * 4);
            g.drawString("" + experienceTracker.getGainedLevels(Skill.MAGIC),    rootX + paddingX * 1, rootY + paddingY * 4);
            //blank
            g.drawString("" + experienceTracker.getGainedLevels(Skill.DEFENCE),   rootX + paddingX * 3, rootY + paddingY * 4);
            g.drawString("" + experienceTracker.getGainedLevels(Skill.HITPOINTS), rootX + paddingX * 4, rootY + paddingY * 4);
        }
    }

    public static void drawRangeStats(Graphics2D g, Font font, ExperienceTracker experienceTracker){
        //Draw EXP and Time
        g.setColor(Color.WHITE);
        g.setFont(font);
        if (experienceTracker != null) {
            g.drawString("RANGED",    rootX + paddingX * 1, rootY);

            g.drawString("DEFENCE",   rootX + paddingX * 3, rootY);
            g.drawString("HITPOINTS", rootX + paddingX * 4, rootY);

            g.drawString("EXP GAINED", rootX, rootY + paddingY * 1);
            g.drawString("" + experienceTracker.getGainedXP(Skill.RANGED),    rootX + paddingX * 1, rootY + paddingY * 1);
            //blank
            g.drawString("" + experienceTracker.getGainedXP(Skill.DEFENCE),   rootX + paddingX * 3, rootY + paddingY * 1);
            g.drawString("" + experienceTracker.getGainedXP(Skill.HITPOINTS), rootX + paddingX * 4, rootY + paddingY * 1);

            g.drawString("EXP/HR", rootX, rootY + paddingY * 2);
            g.drawString("" + experienceTracker.getGainedXPPerHour(Skill.RANGED),    rootX + paddingX * 1, rootY + paddingY * 2);
            //blank
            g.drawString("" + experienceTracker.getGainedXPPerHour(Skill.DEFENCE),   rootX + paddingX * 3, rootY + paddingY * 2);
            g.drawString("" + experienceTracker.getGainedXPPerHour(Skill.HITPOINTS), rootX + paddingX * 4, rootY + paddingY * 2);

            g.drawString("TTL", rootX, rootY + paddingY * 3);
            g.drawString("" + Tools.convertMillis(experienceTracker.getTimeToLevel(Skill.RANGED)),    rootX + paddingX * 1, rootY + paddingY * 3);
            //blank
            g.drawString("" + Tools.convertMillis(experienceTracker.getTimeToLevel(Skill.DEFENCE)),   rootX + paddingX * 3, rootY + paddingY * 3);
            g.drawString("" + Tools.convertMillis(experienceTracker.getTimeToLevel(Skill.HITPOINTS)), rootX + paddingX * 4, rootY + paddingY * 3);

            g.drawString("LVLS GAINED", rootX, rootY + paddingY * 4);
            g.drawString("" + experienceTracker.getGainedLevels(Skill.RANGED),    rootX + paddingX * 1, rootY + paddingY * 4);
            //blank
            g.drawString("" + experienceTracker.getGainedLevels(Skill.DEFENCE),   rootX + paddingX * 3, rootY + paddingY * 4);
            g.drawString("" + experienceTracker.getGainedLevels(Skill.HITPOINTS), rootX + paddingX * 4, rootY + paddingY * 4);
        }
    }

    public static void drawTime(Graphics2D g, Font font, String timeString){
        g.setColor(Color.WHITE);
        g.setFont(font);

        g.drawString("RUNNING TIME",  rootX + paddingX * 5,    rootY);
        g.drawString("" + timeString, rootX + paddingX * 5, rootY + paddingY * 1);
    }

    public static void drawState(Graphics2D g, Font font, String state){
        g.setColor(Color.WHITE);
        g.setFont(font);

        g.drawString(state, rootX + paddingX * 5, rootY + paddingY * 4);
    }

    public static void drawButton(Graphics2D g, Button button){
        if(button.isPressed()){
            g.drawImage(button.getImage(), button.getX(), button.getY(), null);
        }
        if(!button.isPressed()){
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .75f));//set draw opacity
            g.drawImage(button.getImage(), button.getX(), button.getY(), null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));//reset opacity
        }
    }



}
