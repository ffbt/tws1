package sa.tws1.service.wms;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ToolWarehouse
{
    private ThreadPoolExecutor robots;
    private ConveyerBelt conveyerBelt;

    public ToolWarehouse(int robotNum)
    {
        this.robots = new ThreadPoolExecutor(robotNum, robotNum, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        this.conveyerBelt = new ConveyerBelt("conveyerBelt");
    }

    public void getTool()
    {
        robots.execute(new Robot("robot",
                () ->
                {
                    Random random = new Random();
                    System.out.println("get tool");
                    System.out.println("moving...");
                    try
                    {
                        Thread.sleep(random.nextInt(100));
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println("put tool");
                },
                conveyerBelt
        ));
    }
}
