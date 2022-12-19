package com.Prosper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Prosper.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
	@Query("SELECT u.userId FROM UserEntity u WHERE u.userName = :userName and u.emailId = :emailId")
	String findUserByEmailIdAndUserName(@Param("userName") String userName, @Param("emailId") String emailId);

	@Query("SELECT u.userId FROM UserEntity u WHERE u.userName = :userName")
	String findUserByUserName(@Param("userName") String userName);

	@Query("SELECT u.userId FROM UserEntity u WHERE u.emailId = :emailId")
	String findUserByEmailId(@Param("emailId") String emailId);

	@Query("SELECT u.password FROM UserEntity u WHERE u.userName = :userName")
	String findPasswordByUserName(@Param("userName") String userName);
	
//	@Query("SELECT u.userName FROM UserEntity u WHERE u.resetPasswordToken = :resetPasswordToken")
//	String findByResetPasswordToken(@Param("resetPasswordToken") String token);
	
	@Query("SELECT u.userName FROM UserEntity u WHERE u.roleId = :roleId")
	public List<String> FindByRoleId(int roleId);
	
	public UserEntity findByResetPasswordToken(String token);
	
	public UserEntity findByUserId(long l);
	
	public UserEntity findByUserName(String userName);
	
	public List<UserEntity> findByRoleId(int roleId);

}
