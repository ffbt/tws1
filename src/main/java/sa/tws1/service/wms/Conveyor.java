package sa.tws1.service.wms;

import sa.tws1.bean.AllTool;

public class Conveyor implements Action
{
    private String name;
    private AllTool allTool;

    public Conveyor(String name)
    {
        this.name = name;
    }

    public void setAllTool(AllTool allTool)
    {
        this.allTool = allTool;
    }

    public void move()
    {
        System.out.println(name + " is moving allTool" + allTool.getId());
    }
}
