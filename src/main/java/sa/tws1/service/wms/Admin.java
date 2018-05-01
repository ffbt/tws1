package sa.tws1.service.wms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Admin
{
    @Autowired
    private WMSService wmsService;

    public int lend(String toolName)
    {
        return wmsService.lend(toolName);
    }

    public int lendRemote(String toolName)
    {
        return wmsService.lendRemote(toolName);
    }
}
