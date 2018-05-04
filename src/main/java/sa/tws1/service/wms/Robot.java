package sa.tws1.service.wms;

import java.util.Random;

public class Robot implements Action
{
    private String name;

    public Robot(String name)
    {
        this.name = name;
    }

    @Override
    public void move()
    {
        Random random = new Random();
        System.out.println(name + " get tool");
        System.out.println(name + " is moving...");
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
