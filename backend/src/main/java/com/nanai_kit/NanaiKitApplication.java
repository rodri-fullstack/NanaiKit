package com.nanai_kit;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NanaiKitApplication {

	public static void main(String[] args) {
		// Carga .env (raíz del proyecto). No falla si falta.
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

		// DB
		setProp(dotenv, "DB_URL", null);
		setProp(dotenv, "DB_USERNAME", null);
		setProp(dotenv, "DB_PASSWORD", null);

		// JWT  🔐  (IMPRESCINDIBLES)
		setProp(dotenv, "JWT_SECRET", null);
		setProp(dotenv, "JWT_EXPIRATION_MS", "3600000");

		SpringApplication.run(NanaiKitApplication.class, args);
	}

	private static void setProp(Dotenv dotenv, String key, String defaultValue) {
		String v = dotenv.get(key);
		if (v != null && !v.isBlank()) {
			System.setProperty(key, v);
		} else if (defaultValue != null) {
			System.setProperty(key, defaultValue);
		}
	}
}
