package com.rbb.report.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rbb.report.comp.StatementValidator;
import com.rbb.report.model.Record;
import com.rbb.report.model.Records;
import com.rbb.report.parse.util.Parser;

@Service
public class StatementService {

	@Value("${upload.tempfolder}")
	private String uploadDirName;

	@Autowired
	StatementValidator statementValidator;

	public List<Record> genarateReport(MultipartFile file) throws UnsupportedOperationException {
		System.out.println("*************** genarateReport Called ******************");
		//
		String sDestPath = new StringBuilder(System.getProperty("user.dir")).append(File.separator)
				.append(uploadDirName).toString();
		File destFile = new File(sDestPath);
		if (!destFile.exists()) {
			destFile.mkdirs();
		}
		sDestPath = new StringBuilder(sDestPath).append(File.separator).append(file.getOriginalFilename()).toString();
		String fileType = sDestPath.substring(sDestPath.lastIndexOf(".") + 1);
		System.out.println("sDestPath::" + sDestPath + ", fileType::" + fileType);
		//
		if (!statementValidator.isAllowedFormat(fileType)) {
			throw new UnsupportedOperationException("Not supported unknown file format " + fileType + ".");
		}
		writeFile(file, sDestPath);
		//
		Records records = (Records) Parser.parse(fileType, new File(sDestPath));
		System.out.println("Total records::" + records);
		//
		List<Record> failedRecords = filterFailedRecords(records.getRecords());

		return failedRecords;
	}

	private List<Record> filterFailedRecords(List<Record> totalRecords) {
		List<Record> failedRecords = statementValidator.filterDuplicateRecords(totalRecords);
		System.out.println("dup failedRec::" + failedRecords);
		failedRecords.addAll(statementValidator.filterWrongEndBalanceRecords(
				totalRecords.stream().filter(record -> !failedRecords.contains(record)).collect(Collectors.toList())));
		System.out.println("final failedRec::" + failedRecords);
		return failedRecords;
	}

	private void writeFile(MultipartFile file, String sDestPath) {
		try (InputStream ins = file.getInputStream(); FileOutputStream fos = new FileOutputStream(sDestPath);) {
			byte[] buffer = new byte[1024];
			int length;
			while ((length = ins.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error occured while writing file :" + ex.getMessage());
			throw new UnsupportedOperationException("Error occured while writing file :");
		} finally {

		}
	}

}
