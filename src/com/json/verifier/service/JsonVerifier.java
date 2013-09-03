package com.json.verifier.service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonParser;

@Path("/verification")
public class JsonVerifier {

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/verifyJson")
	public String verifyJson(@FormParam("jsonValue") String jsonToVerify) {

		System.err.println("Called the service!" + jsonToVerify);
		JsonParser parser = new JsonParser();
		String returnValue = "Valid JSON!";
		try {
			parser.parse(jsonToVerify);
		} catch (Exception e) {
			String message = e.getMessage();
			System.err.println(message);
			String line = message.substring(message.indexOf("line") + 5,
					message.indexOf("column"));
			String column = message.substring(message.indexOf("column") + 7,
					message.length());
			returnValue = "Invalid JSON! Error in line (" + line.trim()
					+ ") and column (" + column.trim() + ").";
		}
		System.err.println("Finished Call: Result: " + returnValue);
		return returnValue;

	}

	public static void main(String[] args) {
		JsonVerifier jv = new JsonVerifier();
		System.err.println(jv.verifyJson("Saurabh"));
	}

}
