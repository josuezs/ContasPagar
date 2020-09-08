package br.com.deliverit.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.deliverit.exception.BusinessException;
import br.com.deliverit.facade.ContasFacade;
import br.com.deliverit.model.Contas;
import br.com.deliverit.resource.ContaResource;

@Path("/conta")
public class ContaService {

	private static final Logger log = LogManager.getLogger(ContaService.class);

	@EJB
	private ContasFacade contasFacade;

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response incluir(ContaResource conta) {
		log.info("Request para inclusão de conta a pagar...");
		try {
			Contas entity = new Contas();
			entity.setNome(conta.getNome());
			entity.setDtaPagamento(conta.getDtaPagamento());
			entity.setDtaVencimento(conta.getDtaVencimento());
			entity.setVlrOriginal(conta.getVlrOriginal());
			contasFacade.incluirConta(entity);
		} catch (BusinessException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Erro interno: " + (e != null ? e.getMessage() : "")).build();
		}

		return Response.status(Response.Status.CREATED).entity("Conta incluída.").build();
	}

	@GET
	@Produces("application/json")
	public Response listar() {
		log.info("Request para listar todas as contas.");
		List<Contas> lst = contasFacade.buscarContas();

		return Response.status(Response.Status.OK).entity(lst).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

}
