package kr.zchat.schedule;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan	// 해당위치부터 컴포넌트 스캔 진행
@ComponentScan(basePackages = {"kr.zchat.batch"})
public class Application {
	
	public static void main(String[] args)  {
		SpringApplication application = new SpringApplication(Application.class);
		application.run(args);
    }
    
}
