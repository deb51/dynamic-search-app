package in.ashokit.rest;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.bindings.InsurancePlanRequestBinding;
import in.ashokit.bindings.InsurancePlanResponseBinding;
import in.ashokit.excelExport.UserExcelExporter;
import in.ashokit.pdfExport.UserPDFExporter;
import in.ashokit.service.InsurancePlanService;

@RestController
public class InsurancePlanRestController {

	@Autowired
	private InsurancePlanService service;

	@PostMapping("/save")
	public String saveUser(@RequestBody InsurancePlanResponseBinding userDetails) {

		boolean saveUser = service.saveUser(userDetails);

		if (saveUser) {
			return "SUCCESS";
		} else {
			return "FAILED";
		}
	}

	@PostMapping("/getData")
	public ResponseEntity<List<InsurancePlanResponseBinding>> getFilteredData(
			@RequestBody InsurancePlanRequestBinding request) {

		List<InsurancePlanResponseBinding> data = service.getData(request);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("/plans")
	public ResponseEntity<List<String>> getPlans() {
		List<String> plans = service.getPlans();
		return new ResponseEntity<>(plans, HttpStatus.OK);
	}

	@GetMapping("/allPlanStaus")
	public ResponseEntity<List<String>> getAllPlanStatus() {
		List<String> planStatus = service.getPlanStatus();
		return new ResponseEntity<>(planStatus, HttpStatus.OK);
	}

	@GetMapping("/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		 List<InsurancePlanResponseBinding> listUsers = service.getData(null);

		 UserPDFExporter exporter = new UserPDFExporter(listUsers);
		 exporter.export(response);

	}

	@GetMapping("/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		 List<InsurancePlanResponseBinding> listUsers = service.getData(null);

		 UserExcelExporter excelExporter = new UserExcelExporter(listUsers);

		 excelExporter.export(response);
	}

}
