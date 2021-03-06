package sa.tws1.service.wms;

import sa.tws1.bean.AllTool;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RobotSystem
{
    private ThreadPoolExecutor robots;
    private static final int robotNum = 10;

    public RobotSystem()
    {
        this.robots = new ThreadPoolExecutor(robotNum, robotNum, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    }

    public void addTask(int toolContainerId, AllTool allTool, ConveyorSystem conveyorSystem)
    {
        Robot robot = new Robot("robot", this);
        robots.execute(new Task(toolContainerId, robot, allTool, conveyorSystem));
    }

    public void repair(Robot robot)
    {
        System.out.println("repairing " + robot.getName());
        try
        {
            Thread.sleep(new Random().nextInt(100));
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
