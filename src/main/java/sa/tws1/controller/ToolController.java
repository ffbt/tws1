package sa.tws1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sa.tws1.bean.AllTool;
import sa.tws1.bean.Tool;
import sa.tws1.bean.ToolMessage;
import sa.tws1.service.etms.ToolService;
import sa.tws1.service.wms.Admin;

import java.util.List;

@Controller
public class ToolController
{
    @Autowired
    private ToolService toolService;

    @Autowired
    private Admin admin;

    @RequestMapping("/queryInexpensiveTools")
    public String queryInexpensiveTools(Model model, @RequestParam(value = "start", defaultValue = "0") int start,
                                        @RequestParam(value = "size", defaultValue = "10") int size)
    {
        Page<AllTool> page = toolService.getInexpensiveTools(start, size);
        model.addAttribute("page", page);
        return "inexpensiveTools";
    }

    @RequestMapping("/queryAllTools")
    public String queryAllTools(Model model, @RequestParam(value = "start", defaultValue = "0") int start,
                                @RequestParam(value = "size", defaultValue = "10") int size)
    {
        Page<AllTool> page = toolService.getAllTools(start, size);
        model.addAttribute("page", page);
        return "allTools";
    }

    @RequestMapping("/addTools")
    public String addTools()
    {
        return "addTools";
    }

    @RequestMapping("/registerInexpensiveTool")
    public String registerInexpensiveTool(int id)
    {
        toolService.registerInexpensiveTool(id);
        return "redirect:queryInexpensiveTools";
    }

    @RequestMapping("/queryBorrowTools")
    public String queryBorrowTools(Model model, @RequestParam(value = "start", defaultValue = "0") int start,
                                   @RequestParam(value = "size", defaultValue = "10") int size)
    {
        Page<Tool> page = toolService.queryBorrowTools(start, size);
        List<ToolMessage> list = toolService.getToolMessage(page);
        model.addAttribute("page", page);
        model.addAttribute("list", list);
        return "queryBorrowTools";
    }

    @RequestMapping("/cancellation")
    public String cancellation(int id)
    {
        toolService.cancellation(id);
        return "redirect:queryBorrowTools";
    }

    @RequestMapping("/lendTools")
    public String lendTools()
    {
        return "lendTools";
    }

    @RequestMapping("/lend")
    @ResponseBody
    public String lend(String toolName)
    {
        int state = admin.lend(toolName);
        return "{\"state\": \"" + state + "\"}";
    }

    @RequestMapping("/lendRemote")
    @ResponseBody
    public String lendMote(String toolName)
    {
        int state = admin.lendRemote(toolName);
        return "{\"state\": \"" + state + "\"}";
    }

    @RequestMapping("/oneKeyAddition")
    @ResponseBody
    public String oneKeyAddition()
    {
        toolService.oneKeyAddition();
        return "{\"state\": \"1\"}";
    }
}
