package com.yj.maven.user.dao;

import lombok.Data;

@Data
public class UserInfo {
	private String id;
	private String password;
	private String name;
	private String memo;
}
