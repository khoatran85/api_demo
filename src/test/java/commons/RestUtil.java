package commons;

import java.util.Random;

public abstract class RestUtil {

    public static int randomNum(){
        Random random = new Random();
        return random.nextInt(999);
    }
}
