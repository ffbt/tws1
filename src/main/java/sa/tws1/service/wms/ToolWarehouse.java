package sa.tws1.service.wms;

import sa.tws1.bean.AllTool;

import java.util.Random;

public class ToolWarehouse
{
    private RobotSystem robotSystem;
    private ConveyorSystem conveyorSystem;

    public ToolWarehouse()
    {
        this.robotSystem = new RobotSystem();
        this.conveyorSystem = new ConveyorSystem();
    }

    public void getTool(AllTool allTool)
    {
        int toolContainerId = findTool(allTool);
        robotSystem.addTask(toolContainerId, allTool, conveyorSystem);
    }

    private int findTool(AllTool allTool)
    {
        Random random = new Random();
        return random.nextInt(100);
    }
}
