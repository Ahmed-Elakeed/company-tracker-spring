package com.study.companytracker.service;


import com.study.companytracker.converter.DepartmentConverter;
import com.study.companytracker.dto.DepartmentDTO;
import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.exception.NotFoundException;
import com.study.companytracker.model.Department;
import com.study.companytracker.repository.data.DepartmentData;
import com.study.companytracker.util.ModelMapperUtil;
import com.study.companytracker.util.enums.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentData departmentData;

    /**
     * @Author Ahmed Elakeed
     * Service method to get all departments
     * @return GenericRestResponse with all departments and response details
     */
    public GenericRestResponse<?> getAllDepartment() {
            return GenericRestResponse.builder()
                    .data(this.departmentData.fetchAllDepartments().stream().map(department -> ModelMapperUtil.MAPPER().map(department, DepartmentDTO.class)))
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
    }

    public GenericRestResponse<?> getDepartmentByName(String name) {
            Department department = this.departmentData.findDepartmentByName(name).orElseThrow(() -> new NotFoundException("No department found with name : " + name));
            return GenericRestResponse.builder()
                    .data(department)
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
    }


    /**
     * @Author Mo'men Magdy
     * @param departmentId (Long value)
     * @return GenericRestResponse with a department object and response details
     */
    public GenericRestResponse<?> getDepartmentById(Long departmentId) {
            // finding department by id and throw no found exception if not exist
            Department department = this.departmentData.findDepartmentById(departmentId).orElseThrow(() -> new NotFoundException("No department found with id : " + departmentId));
            return GenericRestResponse.builder()
                    .data(DepartmentConverter.toDto(department))
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
    }

    public GenericRestResponse<?> addDepartment(DepartmentDTO departmentDTO) {
        Department department = this.departmentData.save(DepartmentConverter.toEntity(departmentDTO));
            return GenericRestResponse.builder()
                    .data(department)
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
    }

    public GenericRestResponse<?> deleteDepartmentById(Long id) {
            Optional<Department> department = this.departmentData.findDepartmentById(id);
            if (department.isPresent())
                this.departmentData.deleteById(id);
            else
                throw new NotFoundException("No Employees Found with this Id: " + id);
            return GenericRestResponse.builder()
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
    }

}












