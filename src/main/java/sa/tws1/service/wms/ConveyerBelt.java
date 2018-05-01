package sa.tws1.service.wms;

import java.util.Random;

public class ConveyerBelt implements Action
{
    private String name;

    public ConveyerBelt(String name)
    {
        this.name = name;
    }

    public void move()
    {
        Random random = new Random();
        System.out.println(name + " start");
        System.out.println(name + " moving...");
        try
        {
            Thread.sleep(random.nextInt(100));
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(name + " end");
    }
}
