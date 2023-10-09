package com.study.companytracker.service;


import com.study.companytracker.converter.DepartmentConverter;
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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentData departmentData;

    /**
     * @return GenericRestResponse with all departments and response details
     * @Author Ahmed Elakeed
     * Service method to get all departments
     */
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
                    .data(DepartmentConverter.toDto(department))
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


    /**
     * @param departmentId
     * @return GenericRestResponse with a department object and response details
     * @Author Mo'men Magdy
     */
    public GenericRestResponse<?> getDepartmentById(Long departmentId) {
        try {
            // finding department by id and throw no found exception if not exist
            Department department = this.departmentData.findDepartmentById(departmentId).orElseThrow(() -> new NotFoundException("No departments found with id : " + departmentId));
            return GenericRestResponse.builder()
                    .data(DepartmentConverter.toDto(department))
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

    public GenericRestResponse<?> saveDepartment(DepartmentDTO departmentDTO) {
        Department department = ModelMapperUtil.MAPPER().map(departmentDTO, Department.class);
        try {
            return GenericRestResponse.builder()
                    .data(this.departmentData.save(department))
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

    public GenericRestResponse<?> deleteDepartmentById(Long id) {
        try {
            Optional<Department> department = this.departmentData.findById(id);
            if (department.isPresent())
                this.departmentData.deleteById(id);
            else
                throw new NotFoundException("No Employees Found with this Id: " + id);
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












