package ma.formation.irisi.zynerator.audit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import ma.formation.irisi.zynerator.bean.BusinessObject;

import java.time.LocalDateTime;


public class AuditBusinessObject extends BusinessObject {


    protected LocalDateTime createdOn;
    protected LocalDateTime updatedOn;
    protected String createdBy;
    protected String updatedBy;

    public AuditBusinessObject() {
        super();
    }

    public AuditBusinessObject(Long id) {
        super(id);
    }

    @JsonProperty(access = Access.READ_ONLY)
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }


    public void setCreatedOn(LocalDateTime createOn) {
        this.createdOn = createOn;
    }

    @JsonProperty(access = Access.READ_ONLY)
    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    @JsonProperty(access = Access.READ_ONLY)
    public String getCreatedBy() {
        return createdBy != null ? createdBy : "";
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @JsonProperty(access = Access.READ_ONLY)
    public String getUpdatedBy() {
        return updatedBy != null ? updatedBy : "";
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
