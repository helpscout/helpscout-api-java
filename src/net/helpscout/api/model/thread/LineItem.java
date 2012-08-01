package net.helpscout.api.model.thread;

public class LineItem {
	private int assignedTo = 0;
	private String status = "Unassigned";
	private int createdBy = 0;
	private int fromMailboxId = 0;
	
	public boolean isAssigned() {
		return this.assignedTo != 0;
	}
	public boolean isActive() {
		return this.status == "active";
	}
	public boolean isPending() {
		return this.status == "pending";
	}
	public boolean isClosed() {
		return this.status == "closed";
	}
	public boolean isSpam() {
		return this.status == "spam";
	}
	public int getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(int assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public int getFromMailboxId() {
		return fromMailboxId;
	}
	public void setFromMailboxId(int fromMailboxId) {
		this.fromMailboxId = fromMailboxId;
	}
}