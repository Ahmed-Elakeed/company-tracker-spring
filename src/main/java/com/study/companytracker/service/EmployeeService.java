package com.study.companytracker.service;

import com.study.companytracker.converter.EmployeeConverter;
import com.study.companytracker.dto.EmployeeDTO;
import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.exception.NotFoundException;
import com.study.companytracker.model.Employee;
import com.study.companytracker.repository.data.EmployeeData;
import com.study.companytracker.util.ModelMapperUtil;
import com.study.companytracker.util.enums.ErrorMessage;
import com.study.companytracker.util.enums.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * @Author Mo'men Magdy
 * Employee Service
 */
@Service
@RequiredArgsConstructor
public class EmployeeService {

    // Injecting Employee Data
    private final EmployeeData employeeData;

    /**
     * @return GenericRestResponse with all employees and response details
     * @Author Mo'men Magdy
     * Service method to fetch all employees
     */
    public GenericRestResponse<?> getAllEmployees() {
        try {
            return GenericRestResponse.builder()
                    .data(employeeData.findAll().stream().map(employee -> ModelMapperUtil.MAPPER().map(employee, EmployeeDTO.class)))
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
        } catch (NotFoundException exception) {
            return GenericRestResponse.builder()
                    .data(null)
                    .responseMessage(ResponseMessage.FAIL)
                    .responseCode(ResponseMessage.FAIL.getCode())
                    .build();
        }
    }

    /**
     * @return GenericRestResponse with all employees and response details
     * @Author Mo'men Magdy
     * Service method to fetch all employees within a department
     * @Param Department name
     */
    public GenericRestResponse<?> getEmployeesByDepartmentName(String departmentName) {
        try {
            List<Employee> employees = employeeData.findEmployeesByDepartment_Name(departmentName).orElseThrow(() -> new NotFoundException("No Employees Found with this Id: " + departmentName));
            List<EmployeeDTO> employeeDTOS = employees.stream().map(employee -> ModelMapperUtil.MAPPER().map(employee, EmployeeDTO.class)).collect(Collectors.toList());

            return GenericRestResponse.builder()
                    .data(employeeDTOS)
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
        } catch (NotFoundException exception) {
            return GenericRestResponse.builder()
                    .data(null)
                    .responseMessage(ResponseMessage.FAIL)
                    .responseCode(ResponseMessage.FAIL.getCode())
                    .errorMessage(ErrorMessage.INVALID_CREDENTIALS.getMessage())
                    .build();
        }
    }

    public GenericRestResponse<?> getEmployeeById(Long employeeId) {
        try {
            Employee employee = this.employeeData.findEmployeeById(employeeId).orElseThrow(() -> new NotFoundException("No Employees Found with this Id: " + employeeId));
            return GenericRestResponse.builder()
                    .data(EmployeeConverter.toDto(employee))
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
        } catch (NotFoundException exception) {
            return GenericRestResponse.builder()
                    .responseMessage(ResponseMessage.FAIL)
                    .responseCode(ResponseMessage.FAIL.getCode())
                    .errorMessage(ErrorMessage.INVALID_CREDENTIALS.getMessage())
                    .build();
        }
    }

    public GenericRestResponse<?> saveEmployee(EmployeeDTO employeeDTO) {
        try {
            return GenericRestResponse.builder()
                    .data(this.employeeData.save(ModelMapperUtil.MAPPER().map(employeeDTO, Employee.class)))
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
        } catch (NotFoundException exception) {
            return GenericRestResponse.builder()
                    .responseMessage(ResponseMessage.FAIL)
                    .responseCode(ResponseMessage.FAIL.getCode())
                    .errorMessage(ErrorMessage.INVALID_CREDENTIALS.getMessage())
                    .build();
        }
    }

    public GenericRestResponse<?> deleteEmployeeById(Long id){
        try {
            Optional<Employee> employee = this.employeeData.findById(id);
            if (employee.isPresent())
                this.employeeData.deleteById(id);
            else
                throw new NotFoundException("No Employees Found with this Id: " + id);
            return GenericRestResponse.builder()
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
        }catch (NotFoundException exception){
            return GenericRestResponse.builder()
                    .responseMessage(ResponseMessage.FAIL)
                    .responseCode(ResponseMessage.FAIL.getCode())
                    .errorMessage(ErrorMessage.INVALID_CREDENTIALS.getMessage())
                    .build();
        }
    }
}
