package com.example.demo.security;

import static com.example.demo.security.PermissionEnum.COURSE_READ;
import static com.example.demo.security.PermissionEnum.COURSE_WRITE;
import static com.example.demo.security.PermissionEnum.STUDENT_READ;
import static com.example.demo.security.PermissionEnum.STUDENT_WRITE;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum RollesEnum {

	STUDENT(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(STUDENT_READ,STUDENT_WRITE,COURSE_READ,COURSE_WRITE)),
	ADMINTREINEE(Sets.newHashSet(STUDENT_READ,COURSE_READ));
	
	
	private final Set<PermissionEnum> permissions;
	
	RollesEnum(Set<PermissionEnum> permissions) {
		this.permissions=permissions;
	}

	public Set<PermissionEnum> getPermissions() {
		return permissions;
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
		Set<SimpleGrantedAuthority> permissions=getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
				.collect(Collectors.toSet());
		
		permissions.add(new SimpleGrantedAuthority("ROLE_" +this.name()));
		return permissions;
	}
	
	
	
}
