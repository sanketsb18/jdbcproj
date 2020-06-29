package com.example.jdbcproj.dao;

import com.example.jdbcproj.model.Employee;
import com.example.jdbcproj.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

    @Repository
    public class EmployeeDao implements EmployeeRepository {

        @Autowired
        private JdbcTemplate jdbcTemplate;

        @Override
        public String saveEmployee(Employee employee) {
            String sql="insert into employee values(?,?,?,?)";
            jdbcTemplate.update(sql,new Object[]{
                    employee.getId(),employee.getName(),employee.getJobTitle(), employee.getCity()
            });
            return "Employee Saved";
        }

        @Override
        public List<Employee> getList() {
            String sql="select * from employee";
            List<Employee> list=jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper(Employee.class));
            return list;
        }

        @Override
        public Employee getEmp(Integer id) {
            String sql="select * from employee where id=?";
            Employee e= (Employee) jdbcTemplate.queryForObject(sql,new Object[]{id},
                    new BeanPropertyRowMapper(Employee.class));
            return e;
        }

        @Override
        public List<Map<String, Object>> getEmpDeptList() {
            String sql="select employee.id,employee.name,employee.city, employee.jobTitle," +
                    "department.deptName as deptName from employee ," +
                    "department where department.id=employee.id";
            List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
            return list;
        }

        @Override
        public String updateEmp(Integer id, String name) {
            String sql = "update employee set name=? where id=? ";
            jdbcTemplate.update(sql,new Object[]{name,id});
            return "Employee Updated";
        }

        @Override
        public String deleteById(Integer id) {
            String sql = "delete from employee where id=?";
            jdbcTemplate.update(sql, new Object[]{id});
            return "Employee deleted";
        }

    }
