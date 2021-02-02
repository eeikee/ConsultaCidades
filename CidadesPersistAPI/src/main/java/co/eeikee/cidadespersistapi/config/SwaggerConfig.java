package co.eeikee.cidadespersistapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("co.eeikee.cidadespersistapi"))
				.paths(PathSelectors.any()).build().apiInfo(ApiInfo()).tags(new Tag("Cidades", "Cidades do Brasil"));
	}
	
	public ApiInfo ApiInfo() {
		return new ApiInfoBuilder()
				.title("Consulta Cidades")
				.description("API para consultar cidades do Brasil")
				.version("1")
				.contact(new Contact("Eike Konuma", "https://github.com/eeikee", "konumaeike5618@hotmail.com"))
				.build();
	}
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("swagger-ui.html")
	      .addResourceLocations("classpath:/META-INF/resources/");

	    registry.addResourceHandler("/webjars/**")
	      .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
