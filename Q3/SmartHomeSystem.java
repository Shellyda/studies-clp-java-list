
import java.util.ArrayList;
import java.util.List;

interface SmartDevice {

    void turnOn();

    void turnOff();

    String getStatus();

    boolean isOn();
}

class SmartLamp implements SmartDevice {

    public boolean on;
    public String color;
    public int intensity;

    public void changeColor(String color, int intensity) {
        this.color = color;
        this.intensity = intensity;
    }

    @Override
    public void turnOn() {
        this.on = true;
    }

    @Override
    public void turnOff() {
        this.on = false;
    }

    @Override
    public String getStatus() {
        String status = on ? "ligada" : "desligada";
        String colorValue = color != null ? color : "padrão";
        return "- Lâmpada " + status + " na cor " + colorValue + " e intensidade " + intensity;
    }

    @Override
    public boolean isOn() {
        return on;
    }
}

class SmartTv implements SmartDevice {

    public boolean on;
    public int channel;

    public void changeChannel(int channel) {
        this.channel = channel;
    }

    @Override
    public void turnOn() {
        this.on = true;
    }

    @Override
    public void turnOff() {
        this.on = false;
    }

    @Override
    public String getStatus() {
        String status = on ? "ligada" : "desligada";
        return "- TV " + status;
    }

    @Override
    public boolean isOn() {
        return on;
    }
}

class SmartLock implements SmartDevice {

    public boolean on;

    @Override
    public void turnOn() {
        this.on = true;
    }

    @Override
    public void turnOff() {
        this.on = false;
    }

    @Override
    public String getStatus() {
        String status = on ? "ligada" : "desligada";
        return "- Fechadura eletrônica " + status;
    }

    @Override
    public boolean isOn() {
        return on;
    }
}

class SmartHomeHub {

    List<SmartDevice> devices = new ArrayList<>();

    public void addDevice(SmartDevice device) {
        this.devices.add(device);
    }

    public void turnAllOn() {
        for (int i = 0; i < this.devices.size(); i++) {
            this.devices.get(i).turnOn();
        }
    }

    public void turnAllOff() {
        for (int i = 0; i < this.devices.size(); i++) {
            this.devices.get(i).turnOff();
        }
    }

    public void showStatus() {
        for (int i = 0; i < this.devices.size(); i++) {
            System.out.println(this.devices.get(i).getStatus());
        }
    }
}

public class SmartHomeSystem {

    public static void main(String[] args) {

        SmartTv tv = new SmartTv();
        SmartLamp lamp = new SmartLamp();
        SmartLock lock = new SmartLock();
        SmartHomeHub homeHub = new SmartHomeHub();
        homeHub.addDevice(tv);
        homeHub.addDevice(lamp);
        homeHub.addDevice(lock);
        System.out.println("Status inicial:");
        homeHub.showStatus();
        System.out.println("\nLigando todos os dispositivos...");
        homeHub.turnAllOn();
        tv.changeChannel(5);
        lamp.changeColor("blue", 100);
        homeHub.showStatus();
        System.out.println("\nDesligando todos os dispositivos...");
        homeHub.turnAllOff();
        homeHub.showStatus();
    }
}
