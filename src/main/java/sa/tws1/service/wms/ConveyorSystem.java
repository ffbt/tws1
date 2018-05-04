package sa.tws1.service.wms;

import sa.tws1.bean.AllTool;

import java.util.LinkedList;
import java.util.Queue;

public class ConveyorSystem
{
    private Conveyor conveyor;
    private Queue<AllTool> allToolQueue;

    public ConveyorSystem()
    {
        this.conveyor = new Conveyor("conveyor");
        this.allToolQueue = new LinkedList<>();
        Thread thread = new Thread(() -> dealAllTool());
        thread.start();
    }

    public void addAllTool(AllTool allTool)
    {
        synchronized (allToolQueue)
        {
            allToolQueue.offer(allTool);
            allToolQueue.notify();
        }
    }

    private void dealAllTool()
    {
        while (true)
        {
            try
            {
                synchronized (allToolQueue)
                {
                    if (allToolQueue.isEmpty())
                        allToolQueue.wait();
                }
                AllTool allTool = allToolQueue.poll();
                conveyor.setAllTool(allTool);
                conveyor.move();
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
