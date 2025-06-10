package id.putra.wms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MasterDataController {

    @GetMapping("master/{path}")
    public String masterPage(@PathVariable String path) {
        switch (path) {
            case "location":
                return "/pages/master/location";
            case "product":
                return "/pages/master/product";
            case "supplier":
                return "/pages/master/supplier";
            case "configuration":
                return "/pages/master/configuration";
            default:
                return "/pages/not_found";
        }
    }

}
