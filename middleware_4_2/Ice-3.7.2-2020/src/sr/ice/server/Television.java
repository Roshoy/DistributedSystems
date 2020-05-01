package sr.ice.server;

import SmartHome.OutOfRangeError;
import com.zeroc.Ice.Current;

public class Television extends Speakers implements SmartHome.LivingRoom.Television {
    private short channel = 1;
    private short maxChannels = 200;

    @Override
    public short getChannel(Current current) {
        return channel;
    }

    @Override
    public void setChannel(short channel, Current current) throws OutOfRangeError {
        if (channel > maxChannels || channel < 1){
            OutOfRangeError e = new OutOfRangeError();
            e.min = 0;
            e.max = maxChannels;
            e.value = channel;
            throw e;
        }
        this.channel = channel;
    }
}
