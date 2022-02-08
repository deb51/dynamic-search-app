package in.ashokit.bindings;

import lombok.Data;

@Data
public class InsurancePlanResponseBinding {
	
	private Integer planId;
	private String planName;
	private String planHolder;
	private String holderSsn;
	private String planStatus;

}
