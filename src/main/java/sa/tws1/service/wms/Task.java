package sa.tws1.service.wms;

import sa.tws1.bean.AllTool;

public class Task implements Runnable
{
    private int toolContainerId;
    private Robot robot;
    private AllTool allTool;
    private ConveyorSystem conveyorSystem;

    public Task(int toolContainerId, Robot robot, AllTool allTool, ConveyorSystem conveyorSystem)
    {
        this.toolContainerId = toolContainerId;
        this.robot = robot;
        this.allTool = allTool;
        this.conveyorSystem = conveyorSystem;
    }

    @Override
    public void run()
    {
        System.out.println("find allTool" + allTool.getId() + " in " + toolContainerId + " tool container");
        robot.move();
        conveyorSystem.addAllTool(allTool);
    }
}
