package com.vti.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.DepartmentResponseDTO;
import com.vti.entity.Department;
import com.vti.service.IDepartmentService;

@RestController
@RequestMapping(value = "api/v1/departments")
public class DepartmentController {

	@Autowired
	private IDepartmentService service;

	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public Page<DepartmentResponseDTO> getAllDepartments(Pageable pageable) {
		Page<Department> entityPages = service.getAllDepartments(pageable);

		// convert entities --> dtos
		List<DepartmentResponseDTO> dtos = modelMapper.map(
				entityPages.getContent(), 
				new TypeToken<List<DepartmentResponseDTO>>() {}.getType());

		Page<DepartmentResponseDTO> dtoPages = new PageImpl<>(dtos, pageable, entityPages.getTotalElements());

		return dtoPages;
		
	}

	@GetMapping(value = "/{id}")
	public DepartmentResponseDTO getDepartmentByID(@PathVariable(name = "id") int id) {
		Department entity = service.getDepartmentByID(id);

		// convert entity to dto
		DepartmentResponseDTO dto = modelMapper.map(entity, DepartmentResponseDTO.class);
		
		return dto;
	}

}
