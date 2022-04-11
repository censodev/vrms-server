package io.github.censodev.vrms.vrmsserver.services;

import io.github.censodev.vrms.vrmsserver.data.domains.*;
import io.github.censodev.vrms.vrmsserver.data.repositories.VcnPackageRepository;
import io.github.censodev.vrms.vrmsserver.data.repositories.VcnScreeningTmplRepository;
import io.github.censodev.vrms.vrmsserver.data.repositories.VcnSiteRepository;
import io.github.censodev.vrms.vrmsserver.data.models.PageReq;
import io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.pack.VcnPackageCreateReq;
import io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.pack.VcnPackageRes;
import io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.pack.VcnPackageSearchReq;
import io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.pack.VcnPackageUpdateReq;
import io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.screening.VcnScreeningTmplCreateReq;
import io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.screening.VcnScreeningTmplRes;
import io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.screening.VcnScreeningTmplSearchReq;
import io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.screening.VcnScreeningTmplUpdateReq;
import io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.site.VcnSiteCreateReq;
import io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.site.VcnSiteRes;
import io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.site.VcnSiteSearchReq;
import io.github.censodev.vrms.vrmsserver.data.models.vcn.resource.site.VcnSiteUpdateReq;
import io.github.censodev.vrms.vrmsserver.utils.I18nUtil;
import io.github.censodev.vrms.vrmsserver.utils.mappers.VcnPackageMapper;
import io.github.censodev.vrms.vrmsserver.utils.mappers.VcnScreeningTmplMapper;
import io.github.censodev.vrms.vrmsserver.utils.mappers.VcnSiteMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VcnResourceService {
    private final VcnSiteRepository vcnSiteRepository;
    private final VcnScreeningTmplRepository vcnScreeningTmplRepository;
    private final VcnPackageRepository vcnPackageRepository;

    public VcnResourceService(VcnSiteRepository vcnSiteRepository,
                              VcnScreeningTmplRepository vcnScreeningTmplRepository,
                              VcnPackageRepository vcnPackageRepository) {
        this.vcnSiteRepository = vcnSiteRepository;
        this.vcnScreeningTmplRepository = vcnScreeningTmplRepository;
        this.vcnPackageRepository = vcnPackageRepository;
    }

    public void createSite(VcnSiteCreateReq req) {
        if (vcnSiteRepository.searchByName(req.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, I18nUtil.get("vcn.resource.site.already-exists"));
        }
        var model = VcnSiteMapper.map(req);
        vcnSiteRepository.save(model);
    }

    public void updateSite(VcnSiteUpdateReq req) {
        var model = vcnSiteRepository.findById(req.getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        I18nUtil.get("vcn.resource.site.not-found")));

        model.setName(req.getName());
        model.setAddress(req.getAddress());
        model.setStatus(req.getStatus());
        model.setProvince(MstProvince.builder().id(req.getProvinceId()).build());
        model.setDistrict(MstDistrict.builder().id(req.getDistrictId()).build());
        model.setWard(MstWard.builder().id(req.getWardId()).build());

        vcnSiteRepository.save(model);
    }

    public Page<VcnSiteRes> searchSites(PageReq pageReq, VcnSiteSearchReq searchReq) {
        return vcnSiteRepository
                .search("%" + searchReq.getKeyword() + "%", pageReq.toPageable())
                .map(VcnSiteMapper::map);
    }

    public void createPackage(VcnPackageCreateReq req) {
        if (vcnPackageRepository.findByDiseasesCode(req.getDiseasesCode()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, I18nUtil.get("vcn.resource.package.diseases-code-already-exists"));
        }
        var model = VcnPackageMapper.map(req);
        vcnPackageRepository.save(model);
    }

    @Transactional
    public void updatePackage(VcnPackageUpdateReq req) {
        var model = vcnPackageRepository.findById(req.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, I18nUtil.get("vcn.resource.package.not-found")));

        if (vcnPackageRepository.findByDiseasesCodeAndIdNot(req.getDiseasesCode(), model.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, I18nUtil.get("vcn.resource.package.diseases-code-already-exists"));
        }

        model.setName(req.getName());
        model.setDiseasesCode(req.getDiseasesCode());
        model.setDesc(req.getDesc());
        model.setStatus(req.getStatus());
        model.setPrice(req.getPrice());
        model.setScreeningTemplate(VcnScreeningTmpl.builder().id(req.getScreeningTemplateId()).build());
    }

    public Page<VcnPackageRes> searchPackages(PageReq pageReq, VcnPackageSearchReq searchReq) {
        return vcnPackageRepository
                .search("%" + searchReq.getKeyword() + "%", pageReq.toPageable())
                .map(VcnPackageMapper::map);
    }

    public void createScreeningTmpl(VcnScreeningTmplCreateReq req) {
        vcnScreeningTmplRepository.save(VcnScreeningTmplMapper.map(req));
    }

    public void updateScreeningTmpl(VcnScreeningTmplUpdateReq req) {
        var model = vcnScreeningTmplRepository.findById(req.getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        I18nUtil.get("vcn.resource.screening-tmpl.not-found")));
        model.setTitle(req.getTitle());
        model.setData(req.getData());
        model.setStatus(req.getStatus());
        vcnScreeningTmplRepository.save(model);
    }

    public Page<VcnScreeningTmplRes> searchScreeningTmpl(PageReq pageReq, VcnScreeningTmplSearchReq searchReq) {
        return vcnScreeningTmplRepository
                .search("%" + searchReq.getKeyword() + "%", pageReq.toPageable())
                .map(VcnScreeningTmplMapper::map);
    }

    public VcnSiteRes getOneSite(Long id) {
        return vcnSiteRepository.findById(id)
                .map(VcnSiteMapper::map)
                .orElseThrow();
    }

    public VcnPackageRes getOnePackage(Long id) {
        return vcnPackageRepository.findById(id)
                .map(VcnPackageMapper::map)
                .orElseThrow();
    }

    public VcnScreeningTmplRes getOneScrTmpl(Long id) {
        return vcnScreeningTmplRepository.findById(id)
                .map(VcnScreeningTmplMapper::map)
                .orElseThrow();
    }
}
