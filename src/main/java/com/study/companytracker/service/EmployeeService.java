package com.study.companytracker.service;

import com.study.companytracker.converter.EmployeeConverter;
import com.study.companytracker.dto.EmployeeDTO;
import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.exception.NotFoundException;
import com.study.companytracker.model.Employee;
import com.study.companytracker.repository.data.EmployeeData;
import com.study.companytracker.util.enums.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeData employeeData;
    public GenericRestResponse<?> getAllEmployees(){
        try{
            return GenericRestResponse.builder()
                    .data(employeeData.findAll().stream().map(EmployeeConverter::toDto))
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
        }catch (NotFoundException exception){
            return GenericRestResponse.builder()
                    .data(null)
                    .responseMessage(ResponseMessage.FAIL)
                    .responseCode(ResponseMessage.FAIL.getCode())
                    .build();
        }
    }

    public GenericRestResponse<?> deleteEmployeeById(Long id) {
        Optional<Employee> employee = this.employeeData.findById(id);
        if (employee.isPresent())
            this.employeeData.deleteById(id);
        else
            throw new NotFoundException("No employees Found with this Id: " + id);
        return GenericRestResponse.builder()
                .responseMessage(ResponseMessage.SUCCESS)
                .responseCode(ResponseMessage.SUCCESS.getCode())
                .build();
    }

    public GenericRestResponse<?> saveOrUpdateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = this.employeeData.save(EmployeeConverter.toEntity(employeeDTO));
        return GenericRestResponse.builder()
                .data(employee)
                .responseMessage(ResponseMessage.SUCCESS)
                .responseCode(ResponseMessage.SUCCESS.getCode())
                .build();
    }
}
