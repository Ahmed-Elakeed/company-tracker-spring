package com.study.companytracker.service;

import com.study.companytracker.converter.ProjectConverter;
import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.dto.ProjectDTO;
import com.study.companytracker.exception.NotFoundException;
import com.study.companytracker.model.Project;
import com.study.companytracker.repository.data.ProjectData;
import com.study.companytracker.util.enums.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectData projectData;

    /**
     * @return GenericRestResponse with all projects and response details
     * @Author Ahmed Elakeed
     * Service method to get all projects
     */
    public GenericRestResponse<?> getAllProjects() {
        return GenericRestResponse.builder()
                .data(this.projectData.findAll().stream().map(ProjectConverter::toDto))
                .responseMessage(ResponseMessage.SUCCESS)
                .responseCode(ResponseMessage.SUCCESS.getCode())
                .build();
    }

    public GenericRestResponse<?> addProject(ProjectDTO projectDTO) {
        Project project = this.projectData.save(ProjectConverter.toEntity(projectDTO));
        return GenericRestResponse.builder()
                .data(project)
                .responseMessage(ResponseMessage.SUCCESS)
                .responseCode(ResponseMessage.SUCCESS.getCode())
                .build();
    }

    public GenericRestResponse<?> deleteProjectById(Long id) {
        Optional<Project> project = this.projectData.findById(id);
        if (project.isPresent())
            this.projectData.deleteById(id);
        else
            throw new NotFoundException("No Projects Found with this Id: " + id);
        return GenericRestResponse.builder()
                .responseMessage(ResponseMessage.SUCCESS)
                .responseCode(ResponseMessage.SUCCESS.getCode())
                .build();
    }

    public GenericRestResponse<?> fetchAllAvailableEmployeesForProject(Long projectId) {
        Project project = this.projectData.findById(projectId)
                .orElseThrow(() -> new NotFoundException("No Projects Found with this Id: " + projectId));
        return GenericRestResponse.builder()
                .data(project.getDepartment().getEmployees())
                .responseMessage(ResponseMessage.SUCCESS)
                .responseCode(ResponseMessage.SUCCESS.getCode())
                .errorMessage(null)
                .build();
    }
}
