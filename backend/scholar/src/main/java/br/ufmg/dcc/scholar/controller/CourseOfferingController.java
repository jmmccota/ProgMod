package br.ufmg.dcc.scholar.controller;

import java.util.Collection;
import java.util.List;

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
    
    @GetMapping("/obterTodos")
	@ResponseStatus(HttpStatus.OK)
    public Collection<CourseOffering> getAll() {
    	return this.courseOfferingService.findAll();
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseOffering salvar(@RequestBody FormCourseOffering form) {
		Semester semesterEntity = this.semesterService.findOne(form.getSemester());
    	Course courseEntity = this.courseService.findOne(form.getCourse());
    	Professor professorEntity = this.professorService.findOne(form.getProfessor());
    	
    	if(semesterEntity == null) {
    		throw new IllegalArgumentException("Semestre não existe");
    	}
    
    	if(courseEntity == null) {
    		throw new IllegalArgumentException("Curso não existe");
    	}
    	
    	if(professorEntity == null) {
    		throw new IllegalArgumentException("Professor não existe");
    	}
    	int tam = this.courseOfferingService.findBySemesterAndProfessor(semesterEntity, professorEntity).size();
    	
    	if(tam >= 2){
    		throw new IllegalArgumentException("Professor ja vinculado a dois cursos nesse semestre");
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
    
    private static class FormCourseOffering {
    	private Long semester;
    	private Long course;
    	private Long professor;
		public Long getSemester() {
			return semester;
		}
		public void setSemester(Long semester) {
			this.semester = semester;
		}
		public Long getCourse() {
			return course;
		}
		public void setCourse(Long course) {
			this.course = course;
		}
		public Long getProfessor() {
			return professor;
		}
		public void setProfessor(Long professor) {
			this.professor = professor;
		}
    	
    }
}
