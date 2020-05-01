package sr.ice.server;

import SmartHome.OutOfRangeError;
import com.zeroc.Ice.Current;

public class Speakers implements SmartHome.LivingRoom.Speakers {
    private short volume = 10;
    private short minVolume = 1;
    private short maxVolume = 100;

    @Override
    public short getVolume(Current current) {
        return volume;
    }

    @Override
    public void setVolume(short volume, Current current) throws OutOfRangeError {
        if (volume > maxVolume || volume < minVolume){
            OutOfRangeError e = new OutOfRangeError();
            e.min = minVolume;
            e.max = maxVolume;
            e.value = volume;
            throw e;
        }
        this.volume = volume;
    }
}
