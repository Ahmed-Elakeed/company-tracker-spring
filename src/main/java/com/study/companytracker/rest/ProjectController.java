package com.study.companytracker.rest;


import com.study.companytracker.dto.GenericRestResponse;
import com.study.companytracker.dto.ProjectDTO;
import com.study.companytracker.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<GenericRestResponse<?>> fetchAllProjects(@RequestHeader(value = "authToken") String authToken) {
        return ResponseEntity.ok(this.projectService.getAllProjects());
    }

    @PostMapping
    public ResponseEntity<GenericRestResponse<?>> saveOrUpdateProject(@RequestBody ProjectDTO projectDTO,@RequestHeader(value = "authToken") String authToken) {
        return ResponseEntity.ok(this.projectService.saveOrUpdateProject(projectDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericRestResponse<?>> deleteProjectById(@PathVariable Long id,@RequestHeader(value = "authToken") String authToken){
        return ResponseEntity.ok(this.projectService.deleteProjectById(id));
    }

    @GetMapping(path = "/{projectId}/available-employees")
    public ResponseEntity<GenericRestResponse<?>> fetchAllAvailableEmployeesForProject(@PathVariable(value = "projectId") Long projectId,@RequestHeader(value = "authToken") String authToken){
        return ResponseEntity.ok(this.projectService.fetchAllAvailableEmployeesForProject(projectId));
    }
}
