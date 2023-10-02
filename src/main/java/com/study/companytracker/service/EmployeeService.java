package com.study.companytracker.service;

import com.study.companytracker.dto.EmployeeDTO;
import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.exception.NotFoundException;
import com.study.companytracker.repository.data.EmployeeData;
import com.study.companytracker.util.ModelMapperUtil;
import com.study.companytracker.util.enums.ErrorMessage;
import com.study.companytracker.util.enums.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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
     * @Author Mo'men Magdy
     * Service method to fetch all employees
     * @return GenericRestResponse with all employees and response details
     */
    public GenericRestResponse<?> getAllEmployees(){
        try{
            return GenericRestResponse.builder()
                    .data(employeeData.findAll().stream().map(employee -> ModelMapperUtil.MAPPER().map(employee, EmployeeDTO.class)))
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

    /**
     * @Author Mo'men Magdy
     * Service method to fetch all employees within a department
     * @Param Department name
     * @return GenericRestResponse with all employees and response details
     */
    public GenericRestResponse<?> getEmployeesByDepartmentName(String departmentName){
        try {
            return GenericRestResponse.builder()
                    .data(employeeData.findEmployeesByDepartment_Name(departmentName))
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
        }catch (NotFoundException exception){
            return GenericRestResponse.builder()
                    .data(null)
                    .responseMessage(ResponseMessage.FAIL)
                    .responseCode(ResponseMessage.FAIL.getCode())
                    .errorMessage(ErrorMessage.INVALID_CREDENTIALS.getMessage())
                    .build();
        }
    }
}
