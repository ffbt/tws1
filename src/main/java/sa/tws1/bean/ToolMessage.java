package sa.tws1.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ToolMessage
{
    private Integer id;
    private String name;
    private String date;
    private Integer num;

    public ToolMessage(Integer id, String name, Date date, Integer num)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.id = id;
        this.name = name;
        this.date = sdf.format(date);
        this.num = num;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public Integer getNum()
    {
        return num;
    }

    public void setNum(Integer num)
    {
        this.num = num;
    }
}
