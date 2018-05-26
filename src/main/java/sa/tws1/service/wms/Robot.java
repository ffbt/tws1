package sa.tws1.service.wms;

import java.util.Random;

public class Robot implements Action
{
    private String name;
    private RobotSystem robotSystem;
    private static final double p = 0.01;

    public Robot(String name, RobotSystem robotSystem)
    {
        this.name = name;
        this.robotSystem = robotSystem;
    }

    public String getName()
    {
        return name;
    }

    private boolean damage()
    {
        if (new Random().nextDouble() < p)
        {
            System.out.println(this.name + " is damaged");
            return true;
        }
        return false;
    }

    @Override
    public void move()
    {
        Random random = new Random();
        System.out.println(name + " get tool");
        System.out.println(name + " is moving...");
        if (damage())
            robotSystem.repair(this);
        try
        {
            Thread.sleep(random.nextInt(100));
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(name + " put tool");
    }
}
