package sa.tws1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sa.tws1.bean.AllTool;
import sa.tws1.dao.AllToolDAO;
import sa.tws1.service.etms.ToolService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Tws1ApplicationTests
{
    @Autowired
    private ToolService toolService;

    @Autowired
    private AllToolDAO allToolDAO;

    @Test
    public void contextLoads()
    {
        System.out.println(allToolDAO.findAll().size());
        AllTool allTool = new AllTool();
        allTool.setName("tool0");
        allTool.setNum(1);
        allTool.setPrice(10.0);
        System.out.println(toolService.addTool(allTool));
        System.out.println(allToolDAO.findAll().size());
    }
}
