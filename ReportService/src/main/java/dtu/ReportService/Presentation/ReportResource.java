package dtu.ReportService.Presentation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dtu.ReportService.Application.ReportService;
import dtu.ReportService.Infrastructure.ReportRepository;

@Path("/tokens")
public class ReportResource  {

	private static ReportService service = new ReportService(new ReportRepository());

//	@POST
//	@Path("{customerId}/{numberOfTokens}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response create(@PathParam("customerId") String customerId, @PathParam("numberOfTokens") Integer numberOfTokens) {
//		return Response.status(Response.Status.CREATED).entity(service.createTokens(numberOfTokens,customerId)).build();
//	}
//
	@GET
	@Path("{customerId}")
	public Response getCustomerReport(@PathParam("customerId") String customerId) {
		return Response.status(Response.Status.OK).entity(service.getCustomerReport(customerId)).build();
	}
	
	@GET
	@Path("{merchantId}")
	public Response getMerchantReport(@PathParam("merchantId") String merchantId) {
		return Response.status(Response.Status.OK).entity(service.getMerchantReport(merchantId)).build();
	}
	
	@GET
	public Response getManagerReport() {
		return Response.status(Response.Status.OK).entity(service.getManagerReport()).build();
	}
//
//	@DELETE
//	@Path("{customerId}")
//	public Response delete(@PathParam("customerId") String customerId) {
//		return Response.status(Response.Status.OK).entity(service.deleteTokensForCustomer(customerId)).build();
//	}

	//    @GET
	//    public Response get(@QueryParam("id") String id) {
	//        System.out.println(id);
	//        try {
	//            System.out.println(service.getAccount(id));
	//            return Response.status(Response.Status.OK).entity(service.getAccount(id)).build();
	//        } catch (ArgumentNullException e) {
	//            return Response.status(Response.Status.OK).entity(service.getAllAccounts()).build();
	//        } catch (EntityNotFoundException e) {
	//            System.out.println(id);
	//            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
	//        } catch (BankServiceException_Exception e) {
	//            e.printStackTrace();
	//            return null;
	//        }
	//    }
	//
	//
	//    @DELETE
	//    public Response delete(@QueryParam("id")String id) {
	//        return Response.status(Response.Status.OK).entity(service.deleteAccount(id)).build();
	//    }
	//
	//    @POST
	//    @Produces(MediaType.APPLICATION_JSON)
	//    @Consumes(MediaType.APPLICATION_JSON)
	//    public Response create(Account entity) {
	//        return Response.status(Response.Status.CREATED).entity(service.createAccount(entity)).build();
	//    }
}
