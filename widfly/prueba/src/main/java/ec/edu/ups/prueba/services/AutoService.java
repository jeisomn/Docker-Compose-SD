package ec.edu.ups.prueba.services;

import java.util.List;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ec.edu.ups.prueba.business.Datos;
import ec.edu.ups.prueba.conf.JaegerConfig;
import ec.edu.ups.prueba.model.Auto;

@Path("Autos")
public class AutoService {

	@Inject
	private Datos gDatos;

	private final Tracer tracer = GlobalTracer.get();

	@Inject
	private JaegerConfig jaegerConfig;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Auto auto) {
		Span span = tracer.buildSpan("crear-autos").start();
		try {
			gDatos.guardar(auto);
			return Response.ok(auto).build();
		} catch (Exception e) {
			ErrorMessage error = new ErrorMessage(999, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(error)
					.build();
		} finally {
			span.finish();
		}

	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Auto auto) {
		Span span = tracer.buildSpan("actualizar-autos").start();
		try {
			gDatos.guardar(auto);
			return Response.ok(auto).build();
		} catch (Exception e) {
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		} finally {
			span.finish();
		}
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response remove(@QueryParam("id") int codigo) {
		Span span = tracer.buildSpan("eliminar-autos").start();
		try {
			gDatos.borrarAuto(codigo);
			return Response.ok(codigo).build();
		} catch (Exception e) {
			ErrorMessage error = new ErrorMessage(999999, "Error al eliminar");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		} finally {
			span.finish();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("lista")
	public Response getAutos() {
		Span span = tracer.buildSpan("get-autos").start();
		try {
			List<Auto> au = gDatos.getAutos();
			if (au.size() > 0) {
				return Response.ok(au).build();
			} else {
				ErrorMessage error = new ErrorMessage(999, "No hay autos");
				return Response.status(Response.Status.NOT_FOUND)
						.entity(error)
						.build();
			}
		} catch (Exception e) {
			throw e; // Re-throw the exception after logging it
		} finally {
			span.finish();
		}
	}

}
