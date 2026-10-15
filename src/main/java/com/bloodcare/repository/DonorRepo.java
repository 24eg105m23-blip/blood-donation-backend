package com.bloodcare.repository;
import com.bloodcare.model.*;
import com.bloodcare.model.Models.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.*;
public interface DonorRepo extends JpaRepository<Donor,Long> {
 Optional<Donor> findByUserId(Long id); long countByAvailableTrue();
 @Query("select d from Donor d where d.available=true and (:group is null or d.bloodGroup=:group) and (:city is null or lower(d.city) like lower(concat('%',:city,'%'))) and (:state is null or lower(d.state) like lower(concat('%',:state,'%')))") List<Donor> search(@Param("group") BloodGroup group,@Param("city") String city,@Param("state") String state);
 @Query("select d.bloodGroup, count(d) from Donor d group by d.bloodGroup") List<Object[]> countByGroup();
}
