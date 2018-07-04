package br.ufmg.dcc.scholar.controller;

import java.util.Collection;

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

import br.ufmg.dcc.scholar.domain.CourseOffering;
import br.ufmg.dcc.scholar.domain.Enrollment;
import br.ufmg.dcc.scholar.domain.Student;
import br.ufmg.dcc.scholar.service.CourseOfferingService;
import br.ufmg.dcc.scholar.service.EnrollmentService;
import br.ufmg.dcc.scholar.service.StudentService;

@RestController
@RequestMapping(value = "/enrollments")
public class EnrollmentController {
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CourseOfferingService courseOfferingService;
	
	@GetMapping("/student/{student}")
	@ResponseStatus(HttpStatus.OK)
	Page<Enrollment> listByStudent(@PathVariable long student, @RequestParam int page, @RequestParam int size) {
		Student studentEntity = studentService.findOne(student);
		
		if(studentEntity == null) {
			throw new IllegalArgumentException("Estudante não existe");
		}
		
		return this.enrollmentService.findByStudent(studentEntity, PageRequest.of(page,  size));
	}
	
	@GetMapping("/offering/{offering}")
	@ResponseStatus(HttpStatus.OK)
	Page<Enrollment> listByOffering(@PathVariable long offering, @RequestParam int page, @RequestParam int size) {
		CourseOffering courseOfferingEntity = courseOfferingService.findOne(offering);
		
		if(courseOfferingEntity == null) {
			throw new IllegalArgumentException("Oferta não existe");
		}
		
		return this.enrollmentService.findByCourseOffering(courseOfferingEntity, PageRequest.of(page,  size));
	}
	
	@GetMapping("/obterTodos")
	@ResponseStatus(HttpStatus.OK)
	public Collection<Enrollment> getAll() {		
		return this.enrollmentService.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Enrollment save(@RequestBody FormEnrollment form) {
		Student studentEntity = studentService.findOne(form.getStudent());
		CourseOffering courseOfferingEntity = courseOfferingService.findOne(form.getCourseOffering());
		
		if(studentEntity == null) {
			throw new IllegalArgumentException("Estudante não existe");
		}
		
		if(courseOfferingEntity == null) {
			throw new IllegalArgumentException("Curso não existe");
		}
		
		Enrollment enrollmentEntity = new Enrollment(studentEntity, courseOfferingEntity);
		return enrollmentService.save(enrollmentEntity);		
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@RequestParam long id) {
		
		Enrollment enrollment = enrollmentService.findOne(id);
		if(enrollment == null) {
			throw new IllegalArgumentException("Matrícula não existe");
		}
		
		enrollmentService.delete(enrollment);
	}
	
	private static class FormEnrollment {
		private Long student;
		private Long courseOffering;
		public Long getStudent() {
			return student;
		}
		public void setStudent(Long student) {
			this.student = student;
		}
		public Long getCourseOffering() {
			return courseOffering;
		}
		public void setCourseOffering(Long courseOffering) {
			this.courseOffering = courseOffering;
		}
	}
}
