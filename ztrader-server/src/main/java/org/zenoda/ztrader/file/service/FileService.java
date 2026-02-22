package org.zenoda.ztrader.file.service;

import cn.hutool.core.net.url.UrlBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.zenoda.ztrader.file.config.FileProperties;
import org.zenoda.ztrader.file.domain.FileDescription;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);
    private FileProperties fileProperties;

    public FileService(FileProperties fileProperties) {
        this.fileProperties = fileProperties;
    }


    private String createFileName(String fileName, int num) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == 0) {
            return num + fileName.substring(dotIndex);
        }
        return fileName.substring(0, dotIndex) + "-" + num + fileName.substring(dotIndex);
    }

    public FileDescription uploadSingleFile(MultipartFile file, String path) {
        String fileName = Optional.ofNullable(file.getOriginalFilename()).orElse("unknown");
        Path baseDir = Paths.get(fileProperties.getBaseDir()).normalize();
        Path filePath = baseDir.resolve(path).normalize();
        Path localFile = filePath.resolve(fileName).normalize();
        if (!filePath.startsWith(baseDir)) {
            logger.error("无权写入目录：{}", filePath);
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "无权写入目录:" + filePath);
        }
        File fsFilePath = new File(filePath.toUri());
        if (!fsFilePath.exists()) {
            boolean ok = fsFilePath.mkdirs();
            if (!ok) {
                logger.error("创建目录失败: {}", filePath);
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "创建目录失败");
            }
        }
        File fsLocalFile = new File(localFile.toUri());
        int num = 0;
        while (fsLocalFile.exists()) {
            localFile = filePath.resolve(createFileName(fileName, ++num));
            fsLocalFile = new File(localFile.toUri());
        }
        try {
            file.transferTo(fsLocalFile);
        } catch (IOException e) {
            logger.error("写文件失败:{}", localFile, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "写文件失败");
        }
        FileDescription fileDescription = new FileDescription();
        fileDescription.setName(localFile.getFileName().toString());
        fileDescription.setPath(path);
        fileDescription.setSize(file.getSize());
        fileDescription.setLastModifiedAt(fsLocalFile.lastModified());
        return fileDescription;
    }

    public List<FileDescription> uploadMultiFiles(MultipartFile[] files, String path) {
        List<FileDescription> fileDescriptions = new ArrayList<>();
        for (MultipartFile file : files) {
            fileDescriptions.add(uploadSingleFile(file, path));
        }
        return fileDescriptions;
    }


    public ResponseEntity<Resource> getFile(String fileFullName, String disposition) {
        Path baseDir = Paths.get(fileProperties.getBaseDir()).normalize();
        Path filePath = Paths.get(fileProperties.getBaseDir()).resolve(fileFullName).normalize();
        if (!filePath.startsWith(baseDir)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Resource resource;
        try {
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            return ResponseEntity.internalServerError().build();
        }

        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        String contentType;
        try {
            contentType = Files.probeContentType(filePath);
        } catch (IOException e) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, disposition + "; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
