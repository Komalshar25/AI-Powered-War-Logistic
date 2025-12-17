package com.warlogistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WarLogisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(WarLogisticsApplication.class, args);
    }
}
package com.warlogistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;

import java.awt.Desktop;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

@SpringBootApplication
public class WarLogisticsApplication implements CommandLineRunner {

    private final ApplicationContext context;

    public WarLogisticsApplication(ApplicationContext context) {
        this.context = context;
    }

    public static void main(String[] args) {
        SpringApplication.run(WarLogisticsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Environment env = context.getBean(Environment.class);
        String port = env.getProperty("server.port", "8080");

        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            String localUrl = "http://localhost:" + port;
            String networkUrl = "http://" + hostAddress + ":" + port;

            System.out.println("\n==================================================");
            System.out.println("Routheon.ai is running!");
            System.out.println("Local access: " + localUrl);
            System.out.println("Network access: " + networkUrl);
            System.out.println("H2 Console: http://localhost:" + port + "/h2-console");
            System.out.println("==================================================\n");

            // Automatically open browser
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(new URI(localUrl));
                    System.out.println("Browser opened automatically at: " + localUrl);
                } catch (Exception e) {
                    System.out.println("Could not open browser automatically: " + e.getMessage());
                }
            } else {
                System.out.println("Desktop not supported, please open browser manually at: " + localUrl);
            }

        } catch (UnknownHostException e) {
            System.out.println("Could not determine host address: " + e.getMessage());
        }
    }

}
