package io.github.censodev.vrms.vrmsserver.http.controllers;

import io.github.censodev.vrms.vrmsserver.data.models.*;
import io.github.censodev.vrms.vrmsserver.services.MstResourceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/mst")
public class MstResourceController {
    private final MstResourceService service;

    public MstResourceController(MstResourceService service) {
        this.service = service;
    }

    @GetMapping("country")
    public List<MstCountry> countries() {
        return service.countries();
    }

    @GetMapping("nation")
    public List<MstNation> nations () {
        return service.nations();
    }

    @GetMapping("province")
    public List<MstProvince> provinces() {
        return service.provinces();
    }

    @GetMapping("district")
    public List<MstDistrict> districts(@RequestParam Integer province) {
        return service.districts(province);
    }

    @GetMapping("ward")
    public List<MstWard> wards(@RequestParam Integer district) {
        return service.wards(district);
    }
}
