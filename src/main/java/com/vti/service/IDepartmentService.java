package com.vti.service;

import com.vti.entity.Account;
import com.vti.form.DepartmentFilterForm;
import com.vti.form.DepartmentRequestFormForCreate;
import com.vti.form.DepartmentRequestFormForUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.Department;

public interface IDepartmentService {

	public Department getDepartmentByID(int id);

	Page<Department> getAllDepartments(String search, Pageable pageable, DepartmentFilterForm filterForm);

	void createDepartment(DepartmentRequestFormForCreate form);

	void updateDepartment(int id, DepartmentRequestFormForUpdate form);

	void deleteDepartment(int id);
}
