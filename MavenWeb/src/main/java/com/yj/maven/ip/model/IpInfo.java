package com.yj.maven.ip.model;

import lombok.Data;

@Data
public class IpInfo {
	private String seq;
	private String ip;
	private String regexIp;
	private String ynAllow;
}
