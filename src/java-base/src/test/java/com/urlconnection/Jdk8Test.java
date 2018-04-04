package com.urlconnection;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class Jdk8Test {

	public static void main(String[] args) {
		// Java 8之后：
//		List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
//		features.forEach(n -> System.out.println(n));
//		 
//		// 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
//		// 看起来像C++的作用域解析运算符
//		features.forEach(System.out::println);
		
//		Stream<String> stream= Stream.of("I", "love", "you", "too");
//		stream.filter(str -> {System.out.println(str); return str.length()==4;})
//		    .sorted().forEach(str -> System.out.println("-"+str));
		Function<String, Integer> function = null ;
//		Stream<String> stream = Stream.of("I", "love", "you", "too");
//		Integer lengthSum = stream.reduce(0, // 初始值　// (1)
//		        (sum, str) -> sum+str.length(), // 累加器 // (2)
//		        (a, b) -> a+b); // 部分和拼接器，并行执行时才会用到 // (3)
//		// int lengthSum = stream.mapToInt(str -> str.length()).sum();
//		System.out.println(lengthSum);
		
		Optional<Function<String, Integer>> optional = Optional.of(function) ;
	}
}
