package sr.ice.server;

import SmartHome.Kitchen.CoffeeType;
import SmartHome.Kitchen.FullGroundsError;
import com.zeroc.Ice.Current;

public class CoffeeExpress implements SmartHome.Kitchen.CoffeeExpress {
    private short groundsContent = 0;

    @Override
    public void makeCoffee(CoffeeType type, Current current) throws FullGroundsError {
        if(groundsContent + (type.value() + 10) > 100) {
            throw new FullGroundsError();
        }
        groundsContent += (type.value() + 10);
        System.out.println("Super kawa " + type.name());
    }
}
