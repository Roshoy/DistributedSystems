using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Sharprompt;
using SmartHome;

namespace IceClient
{
    class Program
    {
        static string[] classes = { "camera", "grassMower", "speakers", "television", "coffeeExpress"};
        static Dictionary<String, HashSet<string>> objectsKeys = new Dictionary<String, HashSet<string>>();

        static void Main(string[] args)
        {
            try
            {
                using (Ice.Communicator communicator = Ice.Util.initialize(ref args))
                {

                    Interactive(communicator);
                }
                //var t = Test();
                // t.Wait();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e);
            }
            Console.ReadKey(true);
        }

        static async Task Test()
        {
            await Task.Delay(1000);
            throw new Exception("My exception");            
        }

        static void Interactive(Ice.Communicator communicator)
        {
            while(true)
            {
                var type = Prompt.Select("Choose class to test: ", classes.Append("Exit"));
                if (type.Equals("Exit")) break;
                if (!objectsKeys.ContainsKey(type))
                {
                    objectsKeys.Add(type, new HashSet<string>());
                }
                var name = Prompt.Select("Choose name: ", objectsKeys[type].Append("New one"));

                if (name.Equals("New one"))
                {
                    do
                    {
                        name = Prompt.Input<string>("Enter name");
                    } while (!objectsKeys[type].Add(name));
                }
                var objPrx = communicator.stringToProxy($"{type}/{name}:tcp -h localhost -p 10000");
                try
                {
                    switch (type)
                    {
                        case "camera":
                            var obj = SmartHome.Garden.CameraPrxHelper.checkedCast(objPrx);
                            if (obj == null)
                            {
                                throw new ApplicationException("Invalid proxy");
                            }
                            var method = Prompt.Select("Choose method: ", new[] { "getTilt()", "setTilt(Tilt)", "getZoom()", "setZoom(short)" });
                            switch (method)
                            {
                                case "getTilt()":
                                    Console.WriteLine(obj.getTilt().x + ", " + obj.getTilt().y);
                                    break;
                                case "setTilt(Tilt)":
                                    short x = Prompt.Input<short>("Give me x");
                                    short y = Prompt.Input<short>("Give me y");
                                    try
                                    {
                                        obj.setTilt(new SmartHome.Garden.Tilt(x, y));
                                    }
                                    catch (SmartHome.Garden.TiltOutOfRangeError e)
                                    {
                                        Console.WriteLine(e.reason);
                                    }
                                    break;
                                case "getZoom()":
                                    Console.WriteLine(obj.getZoom());
                                    break;
                                case "setZoom(short)":
                                    short z = Prompt.Input<short>("Give me zoom");
                                    try
                                    {
                                        obj.setZoom(z);
                                    }
                                    catch (global::Ice.UserException e)
                                    {
                                        Console.WriteLine(e);
                                    }
                                    break;
                            }
                            break;
                        case "grassMower":
                            var obj1 = SmartHome.Garden.GrassMowerPrxHelper.checkedCast(objPrx);
                            if (obj1 == null)
                            {
                                throw new ApplicationException("Invalid proxy");
                            }
                            method = Prompt.Select("Choose method: ", new[] { "getCompletion()", "getLocation()", "getCuttingHeight()", "goToLocation(Location)", "setCuttingHeight(short)" });
                            switch (method)
                            {
                                case "getLocation()":
                                    Console.WriteLine(obj1.getLocation().x + ", " + obj1.getLocation().y);
                                    break;
                                case "goToLocation(Location)":
                                    int x = Prompt.Input<int>("Give me x");
                                    int y = Prompt.Input<int>("Give me y");
                                    try
                                    {
                                        obj1.goToLocation(new SmartHome.Garden.Location(x, y));
                                    }
                                    catch (SmartHome.Garden.OutOfReachError e)
                                    {
                                        Console.WriteLine(e.reason);
                                    }
                                    break;
                                case "getCuttingHeight()":
                                    Console.WriteLine(obj1.getCuttingHeight());
                                    break;
                                case "getCompletion()":
                                    Console.WriteLine(obj1.getCompletion());
                                    break;
                                case "setCuttingHeight(short)":
                                    short z = Prompt.Input<short>("Give me height");
                                    try
                                    {
                                        obj1.setCuttingHeight(z);
                                    }
                                    catch (SmartHome.OutOfRangeError e)
                                    {
                                        Console.WriteLine(e.reason);
                                    }
                                    break;
                            }
                            break;
                        case "speakers":
                            var obj2 = SmartHome.LivingRoom.SpeakersPrxHelper.checkedCast(objPrx);
                            if (obj2 == null)
                            {
                                throw new ApplicationException("Invalid proxy");
                            }
                            method = Prompt.Select("Choose method: ", new[] { "getVolume()", "setVolume(short)" });
                            switch (method)
                            {                                
                                case "getVolume()":
                                    Console.WriteLine(obj2.getVolume());
                                    break;
                                case "setVolume(short)":
                                    short z = Prompt.Input<short>("Give me volume");
                                    try
                                    {
                                        obj2.setVolume(z);
                                    }
                                    catch (SmartHome.OutOfRangeError e)
                                    {
                                        Console.WriteLine(e.reason);
                                    }
                                    break;
                            }
                            break;
                        case "television":
                            var obj3 = SmartHome.LivingRoom.TelevisionPrxHelper.checkedCast(objPrx);
                            if (obj3 == null)
                            {
                                throw new ApplicationException("Invalid proxy");
                            }
                            method = Prompt.Select("Choose method: ", new[] { "getVolume()", "setVolume(short)", "getChannel()", "setChannel(short)" });
                            switch (method)
                            {
                                case "getVolume()":
                                    Console.WriteLine(obj3.getVolume());
                                    break;
                                case "setVolume(short)":
                                    short z = Prompt.Input<short>("Give me volume");
                                    try
                                    {
                                        obj3.setVolume(z);
                                    }
                                    catch (SmartHome.OutOfRangeError e)
                                    {
                                        Console.WriteLine(e.reason);
                                    }
                                    break;
                                case "getChannel()":
                                    Console.WriteLine(obj3.getChannel());
                                    break;
                                case "setChannel(short)":
                                    short w = Prompt.Input<short>("Give me volume");
                                    try
                                    {
                                        obj3.setChannel(w);
                                    }
                                    catch (SmartHome.OutOfRangeError e)
                                    {
                                        Console.WriteLine(e.reason);
                                    }
                                    break;
                            }
                            break;
                        case "coffeeExpress":
                            var obj4 = SmartHome.Kitchen.CoffeeExpressPrxHelper.checkedCast(objPrx);
                            if (obj4 == null)
                            {
                                throw new ApplicationException("Invalid proxy");
                            }
                            method = Prompt.Select("Choose coffee: ", new[] { "BLACK","WHITE","CAPPUCCINO" });
                            try
                            {
                                SmartHome.Kitchen.CoffeeType coffee;
                                Enum.TryParse(method, true, out coffee);
                                obj4.makeCoffee(coffee);
                            }
                            catch (SmartHome.Kitchen.FullGroundsError e)
                            {
                                Console.WriteLine(e.reason);
                            }
                            break;
                    }
                }catch(Exception e)
                {
                    Console.Error.WriteLine(e);
                }
            }
        }
    }
}
