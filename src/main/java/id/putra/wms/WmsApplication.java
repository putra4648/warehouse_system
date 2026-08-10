package id.putra.wms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;

@SpringBootApplication
public class WmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmsApplication.class, args);
    }

    // @RequestMapping(value = "/swagger-ui")
    // public void redirectToSwagger(HttpServletResponse response) throws IOException {
    //     response.sendRedirect(this.servletContextPath + "/swagger-ui/index.html");
    // }

}
