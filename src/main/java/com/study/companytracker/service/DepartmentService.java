package com.study.companytracker.service;


import com.study.companytracker.dto.DepartmentDTO;
import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.exception.NotFoundException;
import com.study.companytracker.model.Department;
import com.study.companytracker.repository.data.DepartmentData;
import com.study.companytracker.util.ModelMapperUtil;
import com.study.companytracker.util.enums.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                    .data(department)
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
    }

    public GenericRestResponse<?> addDepartment(Department department) {
            return GenericRestResponse.builder()
                    .data(departmentData.save(department))
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
    }

    public GenericRestResponse<?> deleteDepartmentById(Long id) {
            this.departmentData.findDepartmentById(id).orElseThrow(() -> new NotFoundException("No departments found with id : " + id));
            this.departmentData.deleteById(id);
            return GenericRestResponse.builder()
                    .responseMessage(ResponseMessage.SUCCESS)
                    .responseCode(ResponseMessage.SUCCESS.getCode())
                    .build();
    }

}












