package com.example.jdbcproj.repository;

import com.example.jdbcproj.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeRepository {
    String saveEmployee(Employee employee);

    List<Employee> getList();

    Employee getEmp(Integer id);

    List<Map<String,Object>> getEmpDeptList();

    String updateEmp(Integer id, String name);

    String deleteById(Integer id);
}
