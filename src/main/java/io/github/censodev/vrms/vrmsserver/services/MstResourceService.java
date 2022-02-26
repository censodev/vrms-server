package io.github.censodev.vrms.vrmsserver.services;

import io.github.censodev.vrms.vrmsserver.data.models.*;
import io.github.censodev.vrms.vrmsserver.data.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MstResourceService {
    private final MstCountryRepository countryRepository;
    private final MstNationRepository nationRepository;
    private final MstProvinceRepository provinceRepository;
    private final MstDistrictRepository districtRepository;
    private final MstWardRepository wardRepository;

    public MstResourceService(MstCountryRepository countryRepository,
                              MstNationRepository nationRepository,
                              MstProvinceRepository provinceRepository,
                              MstDistrictRepository districtRepository,
                              MstWardRepository wardRepository) {
        this.countryRepository = countryRepository;
        this.nationRepository = nationRepository;
        this.provinceRepository = provinceRepository;
        this.districtRepository = districtRepository;
        this.wardRepository = wardRepository;
    }

    public List<MstCountry> countries() {
        return countryRepository.findAll();
    }

    public List<MstNation> nations() {
        return nationRepository.findAll();
    }

    public List<MstProvince> provinces() {
        return provinceRepository.findAll();
    }

    public List<MstDistrict> districts(Integer provinceId) {
        return districtRepository.findByProvinceId(provinceId);
    }

    public List<MstWard> wards(Integer districtId) {
        return wardRepository.findByDistrictId(districtId);
    }
}
