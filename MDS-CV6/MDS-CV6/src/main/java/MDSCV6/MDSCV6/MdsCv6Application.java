package MDSCV6.MDSCV6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan
public class MdsCv6Application {

	public static void main(String[] args) {
		SpringApplication.run(MdsCv6Application.class, args);
	}

}
