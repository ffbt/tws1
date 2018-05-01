package sa.tws1.service.wms;

public class Robot implements Runnable
{
    private String name;
    private Action action;
    private ConveyerBelt conveyerBelt;

    public Robot(String name, Action action, ConveyerBelt conveyerBelt)
    {
        this.name = name;
        this.action = action;
        this.conveyerBelt = conveyerBelt;
    }

    @Override
    public void run()
    {
        System.out.println(name + " start");
        action.move();
        System.out.println(name + " end");
        conveyerBelt.move();
    }
}
