package com.study.companytracker.service;


import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.repository.data.DepartmentData;
import com.study.companytracker.util.enums.ErrorMessage;
import com.study.companytracker.util.enums.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentData departmentData;

    public GenericRestResponse<?> getAllDepartment() {
        try {
            return GenericRestResponse.builder()
                    .data(this.departmentData.fetchAllDepartments())
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
        } catch (Exception exception) {
            return GenericRestResponse.builder()
                    .data(null)
                    .responseMessage(ResponseMessage.FAIL)
                    .responseCode(ResponseMessage.FAIL.getCode())
                    .errorMessage(ErrorMessage.INVALID_CREDENTIALS.getMessage())
                    .build();
        }
    }

    public GenericRestResponse<?> getDepartmentByName(String name) {
        try {
            return GenericRestResponse.builder()
                    .data(this.departmentData.findDepartmentByName(name))
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
        } catch (Exception exception) {
            return GenericRestResponse.builder()
                    .data(null)
                    .responseMessage(ResponseMessage.FAIL)
                    .responseCode(ResponseMessage.FAIL.getCode())
                    .errorMessage(ErrorMessage.INVALID_CREDENTIALS.getMessage())
                    .build();
        }
    }
}
