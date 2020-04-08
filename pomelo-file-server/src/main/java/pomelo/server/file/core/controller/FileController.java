package pomelo.server.file.core.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pomelo.server.file.core.persistence.entity.File;
import pomelo.server.file.core.service.interfaces.IFileService;
import pomelo.util.commons.FileUtil;

@RestController
@RequestMapping("/file")
@Api(value = "/file", tags = "FileController")
public class FileController {

	@Autowired
	IFileService fileService;

	@RequestMapping(value = "/{service}/{module}/{id}")
	@ApiOperation(value = "info")
	public void info(@PathVariable(value = "service") String service, @PathVariable(value = "module") String module,
			@PathVariable(value = "id") String id, HttpServletResponse response) throws IOException {
		File file = fileService.findOne(id);
		if (file != null) {
			FileInputStream fis = null;
			OutputStream os = null;
			response.setContentType(file.getType());
			try {
				fis = new FileInputStream(new java.io.File(file.getPath()));
				os = response.getOutputStream();
				IOUtils.copy(fis, os);
			} catch (IOException e) {
				throw new IOException(e);
			} finally {
				IOUtils.closeQuietly(os);
				IOUtils.closeQuietly(fis);
			}
		}
	}

	@PostMapping("/{service}/{module}/upload")
	@ApiOperation(value = "upload")
	public ResponseEntity<List<File>> upload(MultipartFile[] file, HttpServletRequest req,
			@PathVariable(value = "service") String service, @PathVariable(value = "module") String module)
			throws IOException {
		List<File> savedFiles = Lists.newArrayList();
		for (MultipartFile multipartFile : file) {
			String id = UUID.randomUUID().toString();
			java.io.File destFile = FileUtil.getFile(FileUtil.getCurrentProjectDirection(), service, module,
					DateFormatUtils.format(Calendar.getInstance(), "yyyyMMdd"),
					id + "." + StringUtils.substringAfterLast(multipartFile.getOriginalFilename(), "."));
			File sysFile = new File(multipartFile.getOriginalFilename(), multipartFile.getContentType(),
					destFile.getPath(), service + "/" + module);
			sysFile.setId(id);
			savedFiles.add(fileService.saveOne(sysFile));
			multipartFile.transferTo(destFile);
		}
		return new ResponseEntity<List<File>>(savedFiles, HttpStatus.OK);
	}
}
