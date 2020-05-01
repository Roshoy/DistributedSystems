package sr.ice.server;

import SmartHome.Garden.CameraBounds;
import SmartHome.Garden.Tilt;
import SmartHome.Garden.TiltOutOfRangeError;
import SmartHome.OutOfRangeError;
import com.zeroc.Ice.Current;

public class Camera implements SmartHome.Garden.Camera {
    private Tilt tilt;
    private short zoom;
    private short maxZoom = 10;
    private short minZoom = 1;
    final private CameraBounds cameraBounds;

    public Camera(CameraBounds cameraBounds){
        this.cameraBounds = cameraBounds;
        tilt = new Tilt((short)0, (short)0);
        zoom = 1;
    }

    @Override
    public Tilt getTilt(Current __current){
        return tilt;
    }

    @Override
    public void setTilt(Tilt tilt, Current __current) throws TiltOutOfRangeError {
        if (tilt.x > cameraBounds.maxX || tilt.x < cameraBounds.minX ||
            tilt.y > cameraBounds.maxY || tilt.y < cameraBounds.minY){
            TiltOutOfRangeError e = new TiltOutOfRangeError();
            e.bounds = cameraBounds;
            e.wantedTilt = tilt;
            throw e;
        }
        this.tilt = tilt;
    }

    @Override
    public short getZoom(Current __current) {
        return zoom;
    }

    @Override
    public void setZoom(short zoom, Current __current) throws OutOfRangeError {
        if (zoom > maxZoom || zoom < minZoom){
            OutOfRangeError e = new OutOfRangeError();
            e.min = minZoom;
            e.max = maxZoom;
            e.value = zoom;
            throw e;
        }
        this.zoom = zoom;
    }

}
