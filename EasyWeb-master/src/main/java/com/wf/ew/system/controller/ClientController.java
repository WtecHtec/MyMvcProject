package com.wf.ew.system.controller;

import com.alibaba.fastjson.JSON;
import com.wf.ew.common.JsonResult;
import com.wf.ew.oauth.client.Client;
import com.wf.ew.oauth.client.ClientService;
import com.wf.ew.oauth.configuration.ResourceServerConfiguration;
import com.wf.ew.system.model.ClientParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by wangfan on 2018-12-13 下午 2:55.
 */
@RequestMapping("${api.version}/oauth/client")
@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    // 查询所有
    @GetMapping()
    public JsonResult list() {
        List<Client> clientList = clientService.findAll();
        return JsonResult.ok("query success").put("data", clientList);
    }

    // 添加
    @PostMapping()
    public JsonResult add(@RequestBody ClientParam param) {
        param.populateDefault();
        Client client = new Client(
                UUID.randomUUID().toString(),
                ResourceServerConfiguration.RESOURCE_NAME,
                param.getScope(),
                param.getRedirectUri(),
                param.getClientName()
        );
        clientService.addClientDetails(client);
        return JsonResult.ok("add success");
    }

    // 根据clientId查询
    @GetMapping("/{clientId}")
    public JsonResult get(@PathVariable String clientId) {
        return JsonResult.ok("query success").put("data", clientService.loadClientByClientId(clientId));
    }

    // 修改
    @PutMapping("/{clientId}")
    public JsonResult update(@PathVariable String clientId, @RequestBody ClientParam param) {
        Client client = clientService.findByClientId(clientId);
        if (client == null) {
            throw new NoSuchClientException("Not Found The Client.");
        }
        param.populateDefault();
        if (!StringUtils.isEmpty(param.getClientName())) {
            client.setClientName(param.getClientName());
        }
        if (param.getRedirectUri() != null) {
            client.setRegisteredRedirectUri(param.getRedirectUri());
        }

        if (param.getScope() != null) {
            client.setScope(param.getScope());
        }
        clientService.updateClientDetails(client);
        return JsonResult.ok("update success");
    }

    // 删除
    @DeleteMapping("/{clientId}")
    public JsonResult delete(@PathVariable String clientId) {
        clientService.removeClientDetails(clientId);
        return JsonResult.ok("delete success");
    }
}
