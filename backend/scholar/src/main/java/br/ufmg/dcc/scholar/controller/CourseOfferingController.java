package br.ufmg.dcc.scholar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.ufmg.dcc.scholar.domain.Course;
import br.ufmg.dcc.scholar.domain.CourseOffering;
import br.ufmg.dcc.scholar.domain.Semester;
import br.ufmg.dcc.scholar.service.CourseOfferingService;
import br.ufmg.dcc.scholar.service.CourseService;
import br.ufmg.dcc.scholar.service.SemesterService;

@RestController
@RequestMapping(value = "/semesters/{semester}/offerings")
public class CourseOfferingController {

    private final CourseOfferingService courseOfferingService;
    
    private final CourseService courseService;
    
    private final SemesterService semesterService;

    @Autowired
    public CourseOfferingController(
    		CourseOfferingService courseOfferingService,
    		CourseService courseService, 
    		SemesterService semesterService) {
        this.courseOfferingService = courseOfferingService;
        this.courseService = courseService;
        this.semesterService = semesterService;
    }


    @GetMapping
	@ResponseStatus(HttpStatus.OK)
    public Page<CourseOffering> list(@PathVariable long semester, @RequestParam int page, @RequestParam int size) {
        return this.courseOfferingService.findAll(PageRequest.of(page, size));
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseOffering salvar(@PathVariable long semester, @RequestParam long course) {
		Semester semesterEntity = this.semesterService.findOne(semester);
    	Course courseEntity = this.courseService.findOne(course);
    	
    	if(semesterEntity == null) {
    		throw new IllegalArgumentException("Semestre não existe");
    	}
    
    	if(courseEntity == null) {
    		throw new IllegalArgumentException("Curso não existe");
    	}
    	
    	CourseOffering courseOffering = new CourseOffering(semesterEntity, courseEntity);
    	return this.courseOfferingService.save(courseOffering);
    }
    
    @DeleteMapping("/{course}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long semester, @PathVariable long course) {
    	Semester semesterEntity = this.semesterService.findOne(semester);
    	Course courseEntity = this.courseService.findOne(course);
    	
    	if(semesterEntity == null) {
    		throw new IllegalArgumentException("Semestre não existe");
    	}
    
    	if(courseEntity == null) {
    		throw new IllegalArgumentException("Curso não existe");
    	}
    	
    	CourseOffering courseOfferingEntity = this.courseOfferingService.findByCourseAndSemester(courseEntity, semesterEntity);
    	
    	if(courseOfferingEntity == null) {
    		throw new IllegalArgumentException("Oferta não existe");
    	}
    	
    	this.courseOfferingService.delete(courseOfferingEntity);
    }
}
