package net.helpscout.api.model;

import java.util.Date;

import lombok.Data;
import net.helpscout.api.cbo.WorkflowStatus;
import net.helpscout.api.cbo.WorkflowType;

@Data
public class Workflow {

    public Long id;
    public Long mailboxId;
    public WorkflowType type;
    public WorkflowStatus status;
    public Integer order;
    public String name;
    public Date createdAt;
    public Date modifiedAt;
}