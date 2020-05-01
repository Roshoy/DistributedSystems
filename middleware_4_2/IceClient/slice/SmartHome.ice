#ifndef SMART_HOME
#define SMART_HOME

module SmartHome
{
  exception OutOfRangeError
  {
    string reason = "Value out of range";
    short min;
    short max;
    short value;
  };

  module Garden
  {
    struct Location
    {
      int x;
      int y;
    };
    exception OutOfReachError
    {
      string reason = "Place is out of reach";
      Location currentLocation;
      Location destination;
    };



    interface GrassMower
    {
      idempotent short getCompletion();
      idempotent short getCuttingHeight();
      idempotent void setCuttingHeight(short height) throws OutOfRangeError;
      idempotent Location getLocation();
      idempotent void goToLocation(Location location) throws OutOfReachError;
    };
    
    
    struct Tilt
    {
      short x;
      short y;
    };

    struct CameraBounds
    {
      short maxX;
      short minX;
      short maxY;
      short minY;
    };

    exception TiltOutOfRangeError
    {
      string reason = "Camera can't reach specified tilt";
      Tilt wantedTilt;
      CameraBounds bounds;
    };

    interface Camera
    {
      idempotent Tilt getTilt();
      idempotent void setTilt(Tilt t) throws TiltOutOfRangeError;
      idempotent short getZoom();
      idempotent void setZoom(short z) throws OutOfRangeError;
    };

  };
  module LivingRoom
  {
    interface Speakers
    {
      idempotent short getVolume();
      idempotent void setVolume(short volume) throws OutOfRangeError;
    };

    interface Television extends Speakers
    {
      idempotent short getChannel();
      idempotent void setChannel(short channel) throws OutOfRangeError;
    };
  };
  module Kitchen
  {
    enum CoffeeType
    {
      BLACK,
      WHITE,
      CAPPUCCINO
    };

    exception FullGroundsError
    {
      string reason = "Grounds are full";
    };

    interface CoffeeExpress
    {
      idempotent void makeCoffee(CoffeeType type) throws FullGroundsError;
    };
  };
};

#endif
