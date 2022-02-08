package in.ashokit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.bindings.InsurancePlanRequestBinding;
import in.ashokit.bindings.InsurancePlanResponseBinding;
import in.ashokit.entity.InsurancePlanEntity;
import in.ashokit.repo.InsuranceRepository;

@Service
public class InsurancePlanServiceImpl implements InsurancePlanService {

	@Autowired
	private InsuranceRepository repo;

	@Override
	public boolean saveUser(InsurancePlanResponseBinding user) {

		InsurancePlanEntity entity = new InsurancePlanEntity();

		BeanUtils.copyProperties(user, entity);

		InsurancePlanEntity planEntity = repo.save(entity);

		if (planEntity.getHolderSsn() != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<InsurancePlanResponseBinding> getData(InsurancePlanRequestBinding request) {

		InsurancePlanEntity insurance = new InsurancePlanEntity();

		if (request!=null && request.getPlanName() != null && !request.getPlanName().equals("")) {
			insurance.setPlanName(request.getPlanName());
		}

		if (request!=null && request.getPlanStatus() != null && !request.getPlanStatus().equals("")) {
			insurance.setPlanStatus(request.getPlanStatus());
		}

		List<InsurancePlanEntity> findAllinsurance = repo.findAll(Example.of(insurance));

		List<InsurancePlanResponseBinding> plans = new ArrayList<>();
		for (InsurancePlanEntity insu : findAllinsurance) {
			InsurancePlanResponseBinding response = new InsurancePlanResponseBinding();
			BeanUtils.copyProperties(insu, response);
			plans.add(response);
		}
		return plans;

	}

	@Override
	public List<String> getPlans() {
		List<String> plans = repo.getPlans();
		return plans;
	}

	@Override
	public List<String> getPlanStatus() {
		List<String> allStatus = repo.getStatus();
		return allStatus;
	}

}
