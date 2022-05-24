package io.github.censodev.vrms.vrmsserver.web.api;

import io.github.censodev.vrms.vrmsserver.data.domains.*;
import io.github.censodev.vrms.vrmsserver.services.MstResourceService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("country/{id}")
    public MstCountry country(@PathVariable Integer id) {
        return service.country(id);
    }

    @GetMapping("nation")
    public List<MstNation> nations () {
        return service.nations();
    }

    @GetMapping("nation/{id}")
    public MstNation nation(@PathVariable Integer id) {
        return service.nation(id);
    }

    @GetMapping("province")
    public List<MstProvince> provinces() {
        return service.provinces();
    }

    @GetMapping("province/{id}")
    public MstProvince province(@PathVariable Integer id) {
        return service.province(id);
    }

    @GetMapping("district")
    public List<MstDistrict> districts(@RequestParam Integer province) {
        return service.districts(province);
    }

    @GetMapping("district/{id}")
    public MstDistrict district(@PathVariable Integer id) {
        return service.district(id);
    }

    @GetMapping("ward")
    public List<MstWard> wards(@RequestParam Integer district) {
        return service.wards(district);
    }

    @GetMapping("ward/{id}")
    public MstWard ward(@PathVariable Integer id) {
        return service.ward(id);
    }
}
