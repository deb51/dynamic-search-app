package in.ashokit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "INSURANCE_PLAN")
@Entity
public class InsurancePlanEntity {
	
	@Id
	//@GeneratedValue
	@Column(name = "PLAN_ID")
	private Integer planId;
	
	@Column(name = "PLAN_NAME")
	private String planName;
	
	@Column(name = "PLAN_HOLDER")
	private String planHolder;
	
	@Column(name = "HOLDER_SSN")
	private String holderSsn;
	
	@Column(name = "PLAN_STATUS")
	private String planStatus;
}
