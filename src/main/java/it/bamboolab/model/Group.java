package it.bamboolab.model;

public class Group {

	private String groupId;
	private String groupDescription;
	private String prefix;
	private String type;
	private String role;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupDescription() {

		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {

		this.groupDescription = groupDescription;
	}

	public String getPrefix() {

		return prefix;
	}

	public void setPrefix(String prefix) {

		this.prefix = prefix;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}


	@Override
	public String toString() {
		return this.getGroupDescription();
	}

	/* 	Enrico Succhielli

	mi serviva che il tostring ritornasse l'id del gruppo
	public String toString() {
		return this.getGroupId();
	}	*/
}
