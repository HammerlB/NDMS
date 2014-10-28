package at.bmlvs.NDMS.persistence.velocityTesting;

import java.io.StringWriter;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class VelocTest {
	public static void main(String[] args) throws Exception {
		/* first, get and initialize an engine */
		VelocityEngine ve = new VelocityEngine();
		ve.init();
		/* next, get the Template */
		Template t = ve.getTemplate("hello.xml");
		/* create a context and add data */
		VelocityContext context = new VelocityContext();
		context.put("name", "Interface");
		context.put("value", "fa0/1");
		/* now render the template into a StringWriter */
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		/* show the World */
		System.out.println(writer.toString());
	}
}
