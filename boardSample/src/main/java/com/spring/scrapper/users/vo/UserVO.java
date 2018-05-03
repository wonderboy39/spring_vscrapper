package com.spring.scrapper.users.vo;

public class UserVO {
	//eclipse 자동 getter/setter 생성 단축키 : Command + option + s
	private int seq;
	private String id;
	private String name;
	private String password;
	private String role;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UserVO [seq=" + seq + ", id=" + id + ", name=" + name + ", password=" + password + ", role=" + role
				+ "]";
	}
}
