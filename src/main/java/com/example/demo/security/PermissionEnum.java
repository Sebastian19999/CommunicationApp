package com.example.demo.security;

public enum PermissionEnum {

	STUDENT_READ("student:read"),
	STUDENT_WRITE("student:write"),
	COURSE_READ("admin:read"),
	COURSE_WRITE("admin:write");
	
	private final String permission;
	
	PermissionEnum(String permission) {
		this.permission=permission;
	}
	
	public String getPermission() {
		return permission;
	}
	
	
	
}
