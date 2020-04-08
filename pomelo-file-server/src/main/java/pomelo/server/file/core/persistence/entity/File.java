package pomelo.server.file.core.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import io.swagger.annotations.ApiModelProperty;
import pomelo.enums.Status;

@Entity
@Table(name = "sys_file")
public class File implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String originalName;
	private String type;
	private String path;
	private String module; // 所属模块

	@ApiModelProperty(hidden = true)
	protected Status status = Status.Init;
	protected String creator;
	protected String modifier;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	@org.hibernate.annotations.CreationTimestamp
	protected Date createdDate;
	@Temporal(TemporalType.TIMESTAMP)
	@org.hibernate.annotations.UpdateTimestamp
	protected Date modifiedDate;

	@Version
	protected int version;

	public File() {
		super();
		this.id = UUID.randomUUID().toString();
	}

	public File(String originalName, String type, String path, String module) {
		super();
		this.id = UUID.randomUUID().toString();
		this.originalName = originalName;
		this.type = type;
		this.path = path;
		this.module = module;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
