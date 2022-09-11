package com.acme.rest;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.acme.rest")
class ArchUnitTest {

	@ArchTest
	public static final ArchRule allControllerImplementationsShouldBeAnnotatedWithRestController = classes()
			.that().resideInAPackage("..controller..")
			.and().haveSimpleNameEndingWith("ControllerImpl")
			.should().beAnnotatedWith(RestController.class);

	@ArchTest
	public static final ArchRule allControllerInterfacesShouldNotBeAnnotatedWithRestController = classes()
			.that().areInterfaces()
			.and().resideInAPackage("..controller..")
			.and().haveSimpleNameEndingWith("Controller")
			.should().notBeAnnotatedWith(RestController.class);

	@ArchTest
	public static final ArchRule allControllerImplementationsShouldNotBeAnnotatedWithRequestMapping = classes()
			.that().areNotInterfaces()
			.and().resideInAPackage("..controller..")
			.and().haveSimpleNameEndingWith("ControllerImpl")
			.should().notBeAnnotatedWith(RequestMapping.class);

	@ArchTest
	public static final ArchRule allEntitiesShouldBePlacedOnEntityPackage = classes()
			.that().areAnnotatedWith(Entity.class)
			.should().resideInAPackage("..entity");

	@ArchTest
	public static final ArchRule allRepositoriesShouldBeJpaRepositoryImplementations = classes()
			.that().areAnnotatedWith(Repository.class)
			.should().beAssignableTo(JpaRepository.class);

	@ArchTest
	public static final ArchRule swaggerDocShouldBePlacedOnlyOnControllerInterfaces = classes()
			.that().areInterfaces()
			.and().resideInAPackage("..controller")
			.should().dependOnClassesThat().resideInAPackage("io.swagger.v3..")
			.andShould().beAnnotatedWith(Tag.class);

	@ArchTest
	public static final ArchRule swaggerDocShouldNotBePlacedOnControllerImplementations = classes()
			.that().areNotInterfaces()
			.and().resideInAPackage("..controller")
			.should().dependOnClassesThat().resideOutsideOfPackage("io.swagger.v3..");

}
