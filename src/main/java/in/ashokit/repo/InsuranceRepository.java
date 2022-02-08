package in.ashokit.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.InsurancePlanEntity;

public interface InsuranceRepository extends JpaRepository<InsurancePlanEntity, Serializable> {

	@Query("SELECT distinct planName from InsurancePlanEntity")
	public List<String> getPlans();
	
	@Query("SELECT distinct planStatus from InsurancePlanEntity")
	public List<String> getStatus();
}
