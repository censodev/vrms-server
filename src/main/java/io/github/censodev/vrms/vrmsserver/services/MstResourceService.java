package io.github.censodev.vrms.vrmsserver.services;

import io.github.censodev.vrms.vrmsserver.data.models.*;
import io.github.censodev.vrms.vrmsserver.data.repositories.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public MstCountry country(Integer id) {
        return countryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY));
    }

    public List<MstNation> nations() {
        return nationRepository.findAll();
    }

    public MstNation nation(Integer id) {
        return nationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY));
    }

    public List<MstProvince> provinces() {
        return provinceRepository.findAll();
    }

    public MstProvince province(Integer id) {
        return provinceRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY));
    }

    public List<MstDistrict> districts(Integer provinceId) {
        return districtRepository.findByProvinceId(provinceId);
    }

    public MstDistrict district(Integer id) {
        return districtRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY));
    }

    public List<MstWard> wards(Integer districtId) {
        return wardRepository.findByDistrictId(districtId);
    }

    public MstWard ward(Integer id) {
        return wardRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY));
    }
}
