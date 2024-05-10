//package com.ssafy.config;
//
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
////import lombok.extern.slf4j.Slf4j;
//
////@Slf4j
//@Configuration
//@MapperScan(basePackages = { "com.ssafy.**.dao" })
//public class WebMvcConfiguration implements WebMvcConfigurer {

//	@Autowired
//	private ConfirmInterceptor confirmInterceptor;
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(confirmInterceptor).addPathPatterns("/board/**");
//	}
	
//	  @Override 
//	  public void addInterceptors(InterceptorRegistry registry) {
//	    registry.addInterceptor(loginInterceptor) // LoginInterceptor를 인터셉터로 등록
//	        .addPathPatterns("/user/**") // "/user"로 시작하는 모든 경로에 인터셉터를 적용
//	        .excludePathPatterns("/user/signup") // "/user/signup" 경로는 인터셉터를 적용하지 않음
//	        .excludePathPatterns("/user/login"); // "/user/login" 경로는 인터셉터를 적용하지 않음
//	  }
	
//	private final List<String> patterns = Arrays.asList("/board/*", "/admin", "/user/list");

//	private final String uploadFilePath;
//	public WebMvcConfiguration(@Value("${file.path.upload-files}") String uploadFilePath) {
//		this.uploadFilePath = uploadFilePath;
//	}

//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**").allowedOrigins("*")
////			.allowedOrigins("http://localhost:8080", "http://localhost:8081")
//				.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
//						HttpMethod.DELETE.name(), HttpMethod.HEAD.name(), HttpMethod.OPTIONS.name(),
//						HttpMethod.PATCH.name())
//				.maxAge(1800); // 1800초 동안 preflight 결과를 캐시에 저장
//	}
	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/upload/file/**").addResourceLocations("file:///" + uploadFilePath + "/")
//				.setCachePeriod(3600).resourceChain(true).addResolver(new PathResourceResolver());
//	}

	
	// "/"로 요청이 들어오면 "index.2"라는 뷰 반환
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("index2");
//	}
//}
