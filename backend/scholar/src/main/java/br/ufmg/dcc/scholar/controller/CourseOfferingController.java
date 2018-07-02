package br.ufmg.dcc.scholar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.ufmg.dcc.scholar.domain.Course;
import br.ufmg.dcc.scholar.domain.CourseOffering;
import br.ufmg.dcc.scholar.domain.Professor;
import br.ufmg.dcc.scholar.domain.Semester;
import br.ufmg.dcc.scholar.service.CourseOfferingService;
import br.ufmg.dcc.scholar.service.CourseService;
import br.ufmg.dcc.scholar.service.ProfessorService;
import br.ufmg.dcc.scholar.service.SemesterService;

@RestController
@RequestMapping(value = "/offerings")
public class CourseOfferingController {

    private final CourseOfferingService courseOfferingService;
    
    private final CourseService courseService;
    
    private final SemesterService semesterService;
    
    private final ProfessorService professorService;

    @Autowired
    public CourseOfferingController(
    		CourseOfferingService courseOfferingService,
    		CourseService courseService, 
    		SemesterService semesterService,
    		ProfessorService professorService) {
        this.courseOfferingService = courseOfferingService;
        this.courseService = courseService;
        this.semesterService = semesterService;
        this.professorService = professorService;
    }


    @GetMapping("/semester/{semester}")
	@ResponseStatus(HttpStatus.OK)
    public Page<CourseOffering> listBySemester(@PathVariable long semester, @RequestParam int page, @RequestParam int size) {
        Semester semesterEntity = semesterService.findOne(semester);
        
        if(semesterEntity == null) {
        	throw new IllegalArgumentException("Semestre não existe");
        }
    	
    	return this.courseOfferingService.findBySemester(semesterEntity, PageRequest.of(page, size));
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseOffering salvar(@RequestParam long semester, @RequestParam long course, @RequestParam long professor) {
		Semester semesterEntity = this.semesterService.findOne(semester);
    	Course courseEntity = this.courseService.findOne(course);
    	Professor professorEntity = this.professorService.findOne(professor);
    	
    	if(semesterEntity == null) {
    		throw new IllegalArgumentException("Semestre não existe");
    	}
    
    	if(courseEntity == null) {
    		throw new IllegalArgumentException("Curso não existe");
    	}
    	
    	if(professorEntity == null) {
    		throw new IllegalArgumentException("Professor não existe");
    	}
    	
    	CourseOffering courseOffering = new CourseOffering(semesterEntity, courseEntity, professorEntity);
    	return this.courseOfferingService.save(courseOffering);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
    	    	
    	CourseOffering courseOfferingEntity =
    			this.courseOfferingService.findOne(id);
    	
    	if(courseOfferingEntity == null) {
    		throw new IllegalArgumentException("Oferta não existe");
    	}
    	
    	this.courseOfferingService.delete(courseOfferingEntity);
    }
}
