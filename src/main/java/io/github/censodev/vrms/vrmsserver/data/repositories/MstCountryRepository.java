package io.github.censodev.vrms.vrmsserver.data.repositories;

import io.github.censodev.vrms.vrmsserver.data.domains.MstCountry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MstCountryRepository extends JpaRepository<MstCountry, Integer> {
}