package com.ping.spring.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
public class ArtifactoryRestController {

	@Autowired
	private Environment env;

	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public ResponseEntity<List<RundeckModel>> listAllUsers(@RequestParam("repo") String reposParam,
			@RequestParam("fileNames") String nameParam) throws UnirestException, ParseException {

		String artifactProtocol = env.getProperty("artifactory.protocol");
		String artifactUrl = env.getProperty("artifactory.url");
		String artifacname = env.getProperty("artifactory.username");
		String artifacpwd = env.getProperty("artifactory.password");

		String postUrl = artifactProtocol + "://" + artifacname + ":" + artifacpwd + "@" + artifactUrl;

		List<String> repos = Arrays.asList(reposParam.split(","));
		List<String> names = Arrays.asList(nameParam.split(","));

		StringBuilder sb = new StringBuilder();
		sb.append("items.find({");
		sb.append("\"$or\":");
		sb.append("[{");
		for (String repoElemn : repos) {
			sb.append("\"repo\" : {\"$eq\" : \"" + repoElemn + "\"},");
		}
		sb.setLength(sb.length() - 1);
		sb.append("}]");

		if (!"".equals(nameParam)) {
			sb.append(",");
			sb.append("\"$or\":");
			sb.append("[{");
			for (String nameElemn : names) {
				sb.append("\"name\" : {\"$match\" : \"" + nameElemn + "\"},");
			}
			sb.setLength(sb.length() - 1);
			sb.append("}]");
		}

		sb.append("})");

		HttpResponse<String> ss = Unirest.post(postUrl).header("content-type", "text/plain")
				.header("cache-control", "no-cache").body(sb.toString()).asString();

		List<RundeckModel> models = new ArrayList<RundeckModel>();
		JSONObject jsonObject = new JSONObject(ss.getBody().toString());
		JSONArray results = (JSONArray) jsonObject.get("results");
		// Iterator<Object> iterator = results.iterator();
		for (int i = 0; i < results.length(); i++) {

			JSONObject elem = (JSONObject) results.get(i);
			String path = (String) elem.get("path");
			String repo = (String) elem.get("repo");
			String name = (String) elem.get("name");
			String updated = (String) elem.get("updated");

			Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(updated);
			SimpleDateFormat formater = null;
			formater = new SimpleDateFormat("dd/MM/yyyy [HH:mm]");
			String finalUpdate = formater.format(date);

			String key = repo + "/" + path + "/" + name;
			String val = name + " - " + finalUpdate;
			models.add(new RundeckModel(val, key, date));
		}

		Collections.sort(models, new Comparator<RundeckModel>() {
			public int compare(RundeckModel o1, RundeckModel o2) {
				if (o1.getDate() == null || o2.getDate() == null)
					return 0;
				return o2.getDate().compareTo(o1.getDate());
			}
		});

		// return response.toString();
		return new ResponseEntity<List<RundeckModel>>(models, HttpStatus.OK);
	}

}