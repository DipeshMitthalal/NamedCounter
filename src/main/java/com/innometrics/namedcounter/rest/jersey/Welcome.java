package com.innometrics.namedcounter.rest.jersey;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/NamedCounterAPI/")
public class Welcome {
	int a = SingletonClass.getInstance().getValue();

	// This method is called if TEXT_PLAIN is request
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Innometrics";
	}
	
    /**
     * To get the value of the namedcounter
     * @param namedParameter name of the counter
     * @return the name of counter and its value
     */
	@GET
	@Path("/getValue/{param}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getValue(@PathParam("param") String namedParameter) {
		String output = ""+SingletonClass.getInstance().getValueFromMap(namedParameter);
		return Response.status(200).entity(namedParameter+":"+output).build();
	}
	/**
	 * Increments the value of the named counter and return the name of the named counter and its value. Name of the namedcounter is passed as an argument
	 * @param namedParameter name of counter
	 * @return the namedcounter and its current value
	 */ 
	@GET
	@Path("/incrementValue/{param}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response incrementValue(@PathParam("param") String namedParameter) {
		int output = SingletonClass.getInstance().incrementValueInMap(namedParameter);
		return Response.status(200).entity(namedParameter +":"+output).build();
	}

	// This method is called if XML is request
	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayXMLHello() {
		return "<?xml version=\"1.0\"?>" + "<hello> Hello Innometrics" + "</hello>";
	}
    /**
     * This will create a named counter, initialize it to 0 and put it in Hashmap. In Java one cannot create a variable with dynamic name
     * @param namedParamter name of the namedcounter
     * @return returns the named counter and its value which is usually 0
     */
	@PUT
	@Path("/insert/{param1}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createNamedParamter(@PathParam("param1") String namedParamter) {
		// String result = "Track saved : " + track;
		return Response.status(201).entity(SingletonClass.getInstance().addNamedCounter(namedParamter)+":"+0).build();
	}

	/**
	 * Helper method to display the basic usage info of the API
	 * @return HTML page
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response help() {
		String response = "<html> " +
				"<body>"+
				"<p>Welcome to Named Counter Assignment</p>" +
				"<p>Usage:</p>" +
				"<p >GET /NamedCounterAPI/getValue/[namedCounter]</p>" +
				"<p>GET /NamedCounterAPI/incrementValue/[namedCounter] increment the counter and returns the message with counter and value</p>" +
				"<p>PUT /NamedCounterAPI/insert/[namedCounter] - initializes the namedCounter with value 0</p>" +
				"<p>GET /NamedCounterAPI/getList - returns the list if named counter and their value in JSON</p>" +
				"</body>" +
				"</html> ";
		return Response.status(200).entity(response).build();
	}
	
	/**
	 * Get the list of named counter and its value
	 * @return json 
	 */
	@GET
	@Path("/getList/")
	public Response getList() {
		String output = SingletonClass.getInstance().getAllNamedParamaters();
		return Response.ok(output, MediaType.APPLICATION_JSON).build();
	}
	
	
}
