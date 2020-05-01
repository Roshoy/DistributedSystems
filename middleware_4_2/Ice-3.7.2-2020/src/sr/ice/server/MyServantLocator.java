package sr.ice.server;

import SmartHome.Garden.CameraBounds;
import com.zeroc.Ice.Object;
import com.zeroc.Ice.ServantLocator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MyServantLocator implements ServantLocator{
    private HashMap<String, Object> devices = new HashMap<>();

    public void addDevice(String name, Object dev){
        devices.put(name, dev);
    }

    public HashMap<String, Object> getDevices(){
        return devices;
    }

    public ServantLocator.LocateResult locate(com.zeroc.Ice.Current current)
    {
        String category = current.id.category;
        String name = current.id.name;
        String key = category + "/" + name;

        if(devices.containsKey(key)){
            return new ServantLocator.LocateResult(devices.get(key), null);
        }
        Object d = null;
        switch (category){
            case "camera":
                d = new Camera(new CameraBounds((short)90,(short)-90,(short)90,(short)-90));
                break;
            case "grassMower":
                d = new GrassMower();
                break;
            case "speakers":
                d = new Speakers();
                break;
            case "television":
                d = new Television();
                break;
            case "coffeeExpress":
                d = new CoffeeExpress();
                break;
        }
        devices.put(key, d);
        System.out.println("Added " + key + " to service locator's container");
        return new ServantLocator.LocateResult(d, null);
    }

    public void finished(com.zeroc.Ice.Current current, com.zeroc.Ice.Object servant, java.lang.Object cookie)
    {
    }

    public void deactivate(String category)
    {
    }
}
