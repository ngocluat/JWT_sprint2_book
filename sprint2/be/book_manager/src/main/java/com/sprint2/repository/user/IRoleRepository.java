package com.sprint2.repository.user;

import com.sprint2.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IRoleRepository extends JpaRepository<Roles,Integer> {
    /**
     *
     * This method get Role from database
     */
    @Query(value="select * from roles where role_name = :roleName",nativeQuery=true)
    Roles findRolesByRoleName(@Param("roleName") String string);
}
