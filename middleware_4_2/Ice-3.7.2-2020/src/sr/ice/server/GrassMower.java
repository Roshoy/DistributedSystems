package sr.ice.server;

import SmartHome.Garden.Location;
import SmartHome.Garden.OutOfReachError;
import SmartHome.OutOfRangeError;
import com.zeroc.Ice.Current;

public class GrassMower implements SmartHome.Garden.GrassMower {
    private Location location = new Location(0,0);
    private short cuttingHeight = 30;
    private short maxHeight = 70;
    private short minHeight = 10;

    @Override
    public Location getLocation(Current __current){
        return location;
    }

    @Override
    public void goToLocation(Location location, Current __current) throws OutOfReachError{
        if(location.x > 100) {
            OutOfReachError e = new OutOfReachError();
            e.currentLocation = this.location;
            e.destination = location;
            throw e;
        }
        this.location = location;
    }

    @Override
    public short getCompletion(Current __current) {
        return 50; // beautiful function calculating completion of grass cutting
    }

    @Override
    public short getCuttingHeight(Current __current) {
        return this.cuttingHeight;
    }

    @Override
    public void setCuttingHeight(short height, Current __current) throws OutOfRangeError {
        if (height > maxHeight || height < minHeight){
            OutOfRangeError e = new OutOfRangeError();
            e.min = minHeight;
            e.max = maxHeight;
            e.value = height;
            throw e;
        }
        this.cuttingHeight = height;
    }
}
