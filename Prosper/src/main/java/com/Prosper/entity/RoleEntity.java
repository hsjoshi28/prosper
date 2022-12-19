package com.Prosper.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
public class RoleEntity {
	
	@Id
	 @Column(unique = true)
	 public Long roleId;
	
	 @Getter @Setter(AccessLevel.PACKAGE)
	 public String RoleName;
	 
//	 @OneToMany(cascade = CascadeType.ALL)
////	 @JoinTable(inverseJoinColumns= @JoinColumn(name = "roleId"))
//	    private Set<UserEntity> userEntities = new HashSet<>();


}
