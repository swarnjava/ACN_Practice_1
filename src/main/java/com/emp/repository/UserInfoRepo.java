package com.emp.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emp.entity.UserInfo;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Integer>{

	//This is JPQL Query
	@Query(value = "SELECT U FROM UserInfo U WHERE U.userDetail.address = :address")
    List<UserInfo> findUsersByAddress(@Param("address") String address);

	
	//This is SQL Native query
	@Query(value = "SELECT * FROM user_info U INNER JOIN userskills SK ON (U.user_id = SK.user_info_id) AND SK.skillname = :skillName", nativeQuery=true)
	List<UserInfo> findUserBySkillName(@Param("skillName")@NotNull String skillName);
	
	
	/*@Query(value = "SELECT * FROM author WHERE first_name = :firstName", nativeQuery = true)
    List<Author> findAuthorsByFirstName(@Param("firstName") String firstName);*/
	
	/*@Query("SELECT c FROM Client c WHERE c.name = :name and c.age = :age")
	List<Client> findByName(@Param("name") String name, @Param("age") int age);*/

}
