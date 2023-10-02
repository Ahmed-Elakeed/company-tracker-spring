package com.study.companytracker.service;


import com.study.companytracker.dto.DepartmentDTO;
import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.exception.NotFoundException;
import com.study.companytracker.model.Department;
import com.study.companytracker.repository.data.DepartmentData;
import com.study.companytracker.util.ModelMapperUtil;
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
                    .data(this.departmentData.fetchAllDepartments().stream().map(department -> ModelMapperUtil.MAPPER().map(department, DepartmentDTO.class)))
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
            Department department = this.departmentData.findDepartmentByName(name).orElseThrow(() -> new NotFoundException("No department found with name : " + name));
            return GenericRestResponse.builder()
                    .data(department)
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
        } catch (NotFoundException notFoundException) {
            return GenericRestResponse.builder()
                    .data(null)
                    .responseMessage(ResponseMessage.FAIL)
                    .responseCode(ResponseMessage.FAIL.getCode())
                    .errorMessage(ErrorMessage.INVALID_CREDENTIALS.getMessage())
                    .build();
        }
    }

    public GenericRestResponse<?> getDepartmentById(Long id) {
        try {
            Department department = this.departmentData.findDepartmentById(id).orElseThrow(() -> new NotFoundException("No department found with id : " + id));
            return GenericRestResponse.builder()
                    .data(department)
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

    public GenericRestResponse<?> addDepartment(Department department) {
        try {
            return GenericRestResponse.builder()
                    .data(departmentData.save(department))
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

    public GenericRestResponse<?> deleteDepartmentById(Long id) {
        try {
            this.departmentData.findDepartmentById(id).orElseThrow(() -> new NotFoundException("No departments found with id : " + id));
            this.departmentData.deleteById(id);
            return GenericRestResponse.builder()
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

}












