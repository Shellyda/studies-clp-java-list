# Smart Home System

## Description
You have been researching smart homes more and more and decided to create a system to manage different devices. To accomplish this, you need to create an interface `SmartDevice` with the following methods:

- `void turnOn()`
- `void turnOff()`
- `String getStatus()`
- `boolean isOn()`

Then, you purchase a smart lamp (`SmartLamp`), an electronic lock (`SmartLock`), and a smart TV (`SmartTV`), all implementing this interface. Each device has the attribute `boolean on`. Additionally:

- The TV allows channel switching via `void changeChannel(int channel)`.
- The lamp allows changing color and intensity via `void changeColor(String color, int intensity)`, modifying the attributes `String color` and `int intensity`.

Finally, a central hub (`SmartHomeHub`) manages multiple devices using a list. The hub provides the following functionalities:

- `void addDevice(SmartDevice device)`: Adds a device to the system.
- `void turnAllOn()`: Turns all devices on.
- `void turnAllOff()`: Turns all devices off.
- `void showStatus()`: Displays the status of all devices.

## Implementation
The system should be implemented in Java with the following components:

### `SmartDevice` Interface
Defines the basic operations for all smart devices.

### `SmartLamp` Class
- Implements `SmartDevice`.
- Has attributes `boolean on`, `String color`, and `int intensity`.
- Implements `changeColor(String color, int intensity)` to modify its attributes.

### `SmartLock` Class
- Implements `SmartDevice`.
- Has the `boolean on` attribute.

### `SmartTV` Class
- Implements `SmartDevice`.
- Has attributes `boolean on` and `int channel`.
- Implements `changeChannel(int channel)` to modify its channel.

### `SmartHomeHub` Class
- Manages multiple devices using a list.
- Implements methods to add devices, turn them on/off, and display their statuses.

## Example Usage
Below is an example of how the system can be tested:

```java
public class SmartHomeSystem {
    public static void main(String[] args) {
        SmartTv tv = new SmartTv();
        SmartLamp lamp = new SmartLamp();
        SmartLock lock = new SmartLock();
        SmartHomeHub homeHub = new SmartHomeHub();

        homeHub.addDevice(tv);
        homeHub.addDevice(lamp);
        homeHub.addDevice(lock);

        System.out.println("Initial status:");
        homeHub.showStatus();

        System.out.println("\nTurning all devices on...");
        homeHub.turnAllOn();
        tv.changeChannel(5);
        lamp.changeColor("blue", 80);
        homeHub.showStatus();

        System.out.println("\nTurning all devices off...");
        homeHub.turnAllOff();
        homeHub.showStatus();
    }
}
```

## Expected Output
```
Initial status:
SmartTV: Off
SmartLamp: Off
SmartLock: Off

Turning all devices on...
SmartTV: On, Channel 5
SmartLamp: On, Color blue, Intensity 80
SmartLock: On

Turning all devices off...
SmartTV: Off
SmartLamp: Off
SmartLock: Off
```

## Conclusion
This system effectively manages multiple smart devices using object-oriented principles, interfaces, and polymorphism. The `SmartHomeHub` provides centralized control over all devices, making it easy to interact with and automate smart home functionalities.

