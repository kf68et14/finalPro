package com.vti.service;

import com.vti.entity.Account;
import com.vti.form.DepartmentFilterForm;
import com.vti.form.DepartmentRequestFormForCreate;
import com.vti.form.DepartmentRequestFormForUpdate;
import com.vti.specification.AccountSpecificationBuilder;
import com.vti.specification.DepartmentSpecification;
import com.vti.specification.DepartmentSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vti.entity.Department;
import com.vti.repository.IDepartmentRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class DepartmentService implements IDepartmentService {

	@Autowired
	private IDepartmentRepository repository;

	public Page<Department> getAllDepartments(String search, Pageable pageable, DepartmentFilterForm filter) {
		DepartmentSpecificationBuilder specification = new DepartmentSpecificationBuilder(filter, search);

		return repository.findAll(specification.build(), pageable);
	}

	@Override
	public void createDepartment(DepartmentRequestFormForCreate form) {
		repository.save(form.toEntity());
	}

	@Override
	public void updateDepartment(int id, DepartmentRequestFormForUpdate form) {
		Optional<Department> department = repository.findById(id);
		if (department.isPresent()){
			department.get().setName(form.getName());
			department.get().setType(form.getType());
		}
		repository.save(department.get());
	}

	@Override
	public void deleteDepartment(int id) {
		repository.deleteById(id);
	}

	@Transactional
	public Department getDepartmentByID(int id) {
		return repository.findById(id).get();
	}

}
