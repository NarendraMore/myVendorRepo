package com.anemoi.vendor.templateBuilderController;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anemoi.vendor.templateBuilderModel.TemplateDetails;
import com.anemoi.vendor.templateBuilderService.templateDetailsService;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

@Controller("/vendor/datails")
public class TemplateDetailsController {

	private static final Logger logger = LoggerFactory.getLogger(TemplateDetailsController.class);

	@Inject
	private templateDetailsService templateDetailsService;

	@Get("/")
	public String display() {
		this.templateDetailsService.getMessage();
		return "Success";
	}

	@Post("/")
	public HttpResponse<String> craateTemplate(@Body TemplateDetails templateDetails) throws Exception {

		logger.info("inside the template creation controller");
		String response = templateDetailsService.createTemplatedetails(templateDetails);
		return HttpResponse.status(HttpStatus.CREATED).body(response);
	}

}
