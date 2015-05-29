package bg.learnit.annotation.processor;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;

public final class VelocityUtils {

	VelocityUtils() {
		
	}

	Template getVelocityTemplate() {
		Properties properties = new Properties();
		URL url = this.getClass().getResource("/velocity.properties");
		try {
			properties.load(url.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		VelocityEngine engine = new VelocityEngine(properties);
		engine.init();
		return engine.getTemplate("metaInfo.vm");
	}

}
