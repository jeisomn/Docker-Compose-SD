package ec.edu.ups.prueba.conf;


import io.jaegertracing.Configuration;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JaegerConfig {

	public JaegerConfig() {
    	init();
    }

    public void init() {
        Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv()
                .withType("const")
                .withParam(1);

        Configuration.ReporterConfiguration reporterConfig = Configuration.ReporterConfiguration.fromEnv()
                .withLogSpans(true);

        Configuration config = new Configuration("autos")
                .withSampler(samplerConfig)
                .withReporter(reporterConfig);

        var tracer = config.getTracer();
        io.opentracing.util.GlobalTracer.register(tracer);
    }
}
