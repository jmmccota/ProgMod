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

import br.ufmg.dcc.scholar.domain.CourseOffering;
import br.ufmg.dcc.scholar.domain.Monitoring;
import br.ufmg.dcc.scholar.domain.Student;
import br.ufmg.dcc.scholar.service.CourseOfferingService;
import br.ufmg.dcc.scholar.service.MonitoringService;
import br.ufmg.dcc.scholar.service.StudentService;

@RestController
@RequestMapping(value = "/monitorings")
public class MonitoringController {

	@Autowired
	private MonitoringService monitoringService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CourseOfferingService courseOfferingService;
	
	@GetMapping("/student/{student}")
	@ResponseStatus(HttpStatus.OK)
    public Page<Monitoring> listByStudent(@PathVariable long student, @RequestParam int page, @RequestParam int size) {
        Student studentEntity = studentService.findOne(student);
        
        if(studentEntity == null) {
        	throw new IllegalArgumentException("Estudante não existe");
        }
    	
    	return this.monitoringService.findByStudent(studentEntity, PageRequest.of(page, size));
    } 
	
	@GetMapping("/offering/{offering}")
	@ResponseStatus(HttpStatus.OK)
    public Page<Monitoring> listByCourseOffering(@PathVariable long courseOffering, @RequestParam int page, @RequestParam int size) {
        CourseOffering courseOfferingEntity = courseOfferingService.findOne(courseOffering);
        
        if(courseOfferingEntity == null) {
        	throw new IllegalArgumentException("Oferta de curso não existe");
        }
    	
    	return this.monitoringService.findByCourseOffering(courseOfferingEntity, PageRequest.of(page, size));
    } 
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Monitoring salvar(@PathVariable long student, @RequestParam long courseOffering, @RequestParam double salary) {
		Student studentEntity = studentService.findOne(student);
        CourseOffering courseOfferingEntity = courseOfferingService.findOne(courseOffering);
        
        if(studentEntity == null) {
        	throw new IllegalArgumentException("Estudante não existe");
        }
        
        if(courseOfferingEntity == null) {
        	throw new IllegalArgumentException("Oferta de curso não existe");
        }
        
        Monitoring monitoringEntity = new Monitoring(studentEntity, courseOfferingEntity, salary);
		return monitoringService.save(monitoringEntity);
	}
	
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
    	    	
    	Monitoring monitoringEntity = this.monitoringService.findOne(id);
    	
    	if(monitoringEntity == null) {
    		throw new IllegalArgumentException("Monitoramento não existe");
    	}
    	
    	this.monitoringService.delete(monitoringEntity);
    }	
}
