package com.study.companytracker.service;

import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.exception.NotFoundException;
import com.study.companytracker.repository.data.EmployeeData;
import com.study.companytracker.util.enums.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeData employeeData;

    public GenericRestResponse<?> getAllEmployees(){
        try{
            return GenericRestResponse.builder()
                    .data(employeeData.findAll())
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

    public GenericRestResponse<?> getEmployeesByDepartmentName(String name){
        try {
            return GenericRestResponse.builder()
                    .data(employeeData.findEmployeesByDepartment_Name(name))
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
}
