package com.vti.controller;

import java.util.List;

import com.vti.entity.Account;
import com.vti.form.DepartmentFilterForm;
import com.vti.form.DepartmentRequestFormForCreate;
import com.vti.form.DepartmentRequestFormForUpdate;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vti.dto.DepartmentResponseDTO;
import com.vti.entity.Department;
import com.vti.service.IDepartmentService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/v1/departments")
public class DepartmentController {

	@Autowired
	private IDepartmentService service;

	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public ResponseEntity<?> getAllDepartments(
			@RequestParam (value = "search", required = false) String search,
			Pageable pageable,
			DepartmentFilterForm filterForm) {
			Page<Department> departments = service.getAllDepartments(search, pageable, filterForm);
			List<DepartmentResponseDTO> dtos = modelMapper.map(departments, new TypeToken<List<DepartmentResponseDTO>>() {}.getType());

			return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> createDepartment(@RequestBody @Valid DepartmentRequestFormForCreate form){
		service.createDepartment(form);
		return new ResponseEntity<String>("Create successfully", HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	// update department
	public ResponseEntity<?> updateDepartment(@PathVariable int id,@RequestBody @Valid DepartmentRequestFormForUpdate form){
		service.updateDepartment(id, form);
		return new ResponseEntity<String>("update successfully", HttpStatus.ACCEPTED);
	}

	// delete
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteDepartment(@PathVariable int id){
		service.deleteDepartment(id);
		return new ResponseEntity<String>("delete successfully", HttpStatus.ACCEPTED);
	}

}
