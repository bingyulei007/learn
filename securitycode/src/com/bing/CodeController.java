package com.bing;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CodeController {
	@RequestMapping(value="getcode",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]>  getcode() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		CodeCreater cc=new CodeCreater();
		Map<String, Object> map=cc.createCode(CodeType.RANDOM_NUM);
		byte[] byteArr=(byte[]) map.get("img");
		System.out.println(map.get("code"));
		headers.setContentDispositionFormData("attachment", "code.jpeg");
		return new ResponseEntity<byte[]>(byteArr, headers, HttpStatus.CREATED);
	}
}
