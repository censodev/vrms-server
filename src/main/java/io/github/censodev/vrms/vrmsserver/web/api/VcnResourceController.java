package io.github.censodev.vrms.vrmsserver.web.api;

import io.github.censodev.vrms.vrmsserver.data.dto.PageReq;
import io.github.censodev.vrms.vrmsserver.data.dto.Res;
import io.github.censodev.vrms.vrmsserver.data.dto.vcn.resource.pack.VcnPackageCreateReq;
import io.github.censodev.vrms.vrmsserver.data.dto.vcn.resource.pack.VcnPackageRes;
import io.github.censodev.vrms.vrmsserver.data.dto.vcn.resource.pack.VcnPackageSearchReq;
import io.github.censodev.vrms.vrmsserver.data.dto.vcn.resource.pack.VcnPackageUpdateReq;
import io.github.censodev.vrms.vrmsserver.data.dto.vcn.resource.screening.VcnScreeningTmplCreateReq;
import io.github.censodev.vrms.vrmsserver.data.dto.vcn.resource.screening.VcnScreeningTmplRes;
import io.github.censodev.vrms.vrmsserver.data.dto.vcn.resource.screening.VcnScreeningTmplSearchReq;
import io.github.censodev.vrms.vrmsserver.data.dto.vcn.resource.screening.VcnScreeningTmplUpdateReq;
import io.github.censodev.vrms.vrmsserver.data.dto.vcn.resource.site.VcnSiteCreateReq;
import io.github.censodev.vrms.vrmsserver.data.dto.vcn.resource.site.VcnSiteRes;
import io.github.censodev.vrms.vrmsserver.data.dto.vcn.resource.site.VcnSiteSearchReq;
import io.github.censodev.vrms.vrmsserver.data.dto.vcn.resource.site.VcnSiteUpdateReq;
import io.github.censodev.vrms.vrmsserver.services.VcnResourceService;
import io.github.censodev.vrms.vrmsserver.utils.I18nUtil;
import io.github.censodev.vrms.vrmsserver.utils.enums.RoleEnum;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/vcn/resource")
public class VcnResourceController {
    private final VcnResourceService vcnResourceService;

    public VcnResourceController(VcnResourceService vcnResourceService) {
        this.vcnResourceService = vcnResourceService;
    }

    @GetMapping("site")
    public Res<Page<VcnSiteRes>> searchSites(VcnSiteSearchReq searchReq, PageReq pageReq) {
        return new Res<>(vcnResourceService.searchSites(pageReq, searchReq), "");
    }

    @GetMapping("site/{id}")
    public Res<VcnSiteRes> getOneSite(@PathVariable Long id) {
        return new Res<>(vcnResourceService.getOneSite(id), "");
    }

    @PostMapping("site")
    @Secured({RoleEnum.Const.ROLE_ADMIN})
    public Res<Void> createSite(@RequestBody VcnSiteCreateReq req) {
        vcnResourceService.createSite(req);
        return new Res<>(null, I18nUtil.get("vcn.resource.site.create-success"));
    }

    @PutMapping("site")
    @Secured({RoleEnum.Const.ROLE_ADMIN})
    public Res<Void> updateSite(@RequestBody VcnSiteUpdateReq req) {
        vcnResourceService.updateSite(req);
        return new Res<>(null, I18nUtil.get("vcn.resource.site.update-success"));
    }

    @GetMapping("screening-tmpl")
    public Res<Page<VcnScreeningTmplRes>> searchScreeningTmpl(VcnScreeningTmplSearchReq searchReq, PageReq pageReq) {
        return new Res<>(vcnResourceService.searchScreeningTmpl(pageReq, searchReq), "");
    }

    @GetMapping("screening-tmpl/{id}")
    public Res<VcnScreeningTmplRes> getOneScrTmpl(@PathVariable Long id) {
        return new Res<>(vcnResourceService.getOneScrTmpl(id), "");
    }

    @PostMapping("screening-tmpl")
    @Secured({RoleEnum.Const.ROLE_ADMIN})
    public Res<Void> createScreeningTmpl(@RequestBody VcnScreeningTmplCreateReq req){
        vcnResourceService.createScreeningTmpl(req);
        return  new Res<>(null, I18nUtil.get("vcn.resource.screening-tmpl.create-success"));
    }

    @PutMapping("screening-tmpl")
    @Secured({RoleEnum.Const.ROLE_ADMIN})
    public Res<Void> updateScreeningTmpl(@RequestBody VcnScreeningTmplUpdateReq req){
        vcnResourceService.updateScreeningTmpl(req);
        return  new Res<>(null, I18nUtil.get("vcn.resource.screening-tmpl.update-success"));
    }

    @GetMapping("package")
    public Res<Page<VcnPackageRes>> searchPackages(VcnPackageSearchReq searchReq, PageReq pageReq) {
        return new Res<>(vcnResourceService.searchPackages(pageReq, searchReq), "");
    }

    @GetMapping("package/{id}")
    public Res<VcnPackageRes> getOnePackage(@PathVariable Long id) {
        return new Res<>(vcnResourceService.getOnePackage(id), "");
    }

    @PostMapping("package")
    @Secured({RoleEnum.Const.ROLE_ADMIN})
    public Res<Void> createPackage(@RequestBody VcnPackageCreateReq req){
        vcnResourceService.createPackage(req);
        return  new Res<>(null, I18nUtil.get("vcn.resource.package.create-success"));
    }

    @PutMapping("package")
    @Secured({RoleEnum.Const.ROLE_ADMIN})
    public Res<Void> updatePackage(@RequestBody VcnPackageUpdateReq req){
        vcnResourceService.updatePackage(req);
        return  new Res<>(null, I18nUtil.get("vcn.resource.package.update-success"));
    }
}
