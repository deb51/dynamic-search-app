package in.ashokit.service;

import java.util.List;

import in.ashokit.bindings.InsurancePlanRequestBinding;
import in.ashokit.bindings.InsurancePlanResponseBinding;

public interface InsurancePlanService {

	public boolean saveUser(InsurancePlanResponseBinding user);
	
	public List<InsurancePlanResponseBinding> getData(InsurancePlanRequestBinding request);

	public List<String> getPlans();
	
	public List<String> getPlanStatus();
	
}
