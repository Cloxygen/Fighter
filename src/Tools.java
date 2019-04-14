package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public final class Tools{

    public void initializeModule(){

    }

    public static Image getImage(InputStream input)
    {
        Image img;
        try{
            img = ImageIO.read(input);
            return img;
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static String convertMillis(long duration){
        String result;
        long days = TimeUnit.MILLISECONDS.toDays(duration);
        long hours = TimeUnit.MILLISECONDS.toHours(duration)
                - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                .toHours(duration));
        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                .toMinutes(duration));
        if (days == 0) {
            result = (hours + "." + minutes + "." + seconds);
        } else {
            result = (days + "." + hours + "." + minutes + "." + seconds);
        }
        return result;
    }
}
