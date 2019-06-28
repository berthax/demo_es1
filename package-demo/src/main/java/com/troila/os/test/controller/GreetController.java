package com.troila.os.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.flatbuffers.FlatBufferBuilder;
import com.troila.os.test.model.Person;

@RestController
public class GreetController {
	
	@GetMapping("/greet")
	public String greet() {
		return "hello world";
	}
	
	@GetMapping("/test")
	public Person test1() {
		FlatBufferBuilder builder = new FlatBufferBuilder(0);
		int name = builder.createString("张三");
		Person.startPerson(builder);
		Person.addName(builder, name);
		Person.addAge(builder, 22);
		int endOffset = Person.endPerson(builder);
		Person.finishPersonBuffer(builder, endOffset);
		Person p = Person.getRootAsPerson(builder.dataBuffer());
		return p;
	}
	
	@PostMapping("/testRead")
	public Person testRead(@RequestBody Person person) {
		System.out.println(person.name());
		System.out.println(person.age());
		
		
		
		FlatBufferBuilder builder = new FlatBufferBuilder(0);
		int nameOffset = builder.createString("张三");
//		int endOffset = Person.createPerson(builder, 1, 22, nameOffset);
		Person.startPerson(builder);
		Person.addName(builder, nameOffset);
		Person.addAge(builder, 22);
		Person.addCode(builder, 1);
		int endOffset = Person.endPerson(builder);
		Person.finishPersonBuffer(builder, endOffset);
		Person p = Person.getRootAsPerson(builder.dataBuffer());
		return p;
	}
}
